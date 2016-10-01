package za.co.quickkanban

class CPMnodesController {
    // Export service provided by Export plugin
    def exportService
    def grailsApplication  //inject GrailsApplication

    int HIGHEST_NODE=99999

    def scaffold = true
    def nodesList() {}

    def linkNode() {}

    def listNodes() {}
    def listCriticalPath(){}
    def exportCPMnodes(){
        List<CPMnodes> nodes = CPMnodes.findAll([sort: "nodeId", order: "asc"]) ;



        if(!params.max) params.max = 10

        if(params?.format && params.format != "html"){
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename=ScheduleCPMNodes.${params.extension}")

            Map labels = ["NODE_ID":"nodeId",
                    "NODE_NAME":"nodeName",
                    "NEXT NODE":"nextNodeName",
                    "DURATION":"duration",
                    "EARLIEST START":"earliestStart",
                    "EARLIEST FINISH":"earliestFinish",
                    "LATEST START":"latestStart",
                    "LATEST FINISH":"latestFinish",
                    "SLACK":"slack"]

            List fields= ["nodeId","nodeName","nextNodeName","duration","earliestStart","earliestFinish","latestStart","latestFinish","slack"]

            // Formatter closure
            def upperCase = { domain, value ->
                return value.toUpperCase()
            }

            Map formatters = [schedule: upperCase]
            Map parameters = [title: "Project Schedule", "column.widths": [0.2, 0.3, 0.5]]

            exportService.export(params.format, response.outputStream, nodes, fields, labels, formatters, parameters)
        }

        ["nodes":nodes]
    }

    int getDuration(String nodeId) {
        try {
            Schedule schedule = Schedule.findByNodeId(new Integer(nodeId))
            return schedule.duration
        } catch (Exception e) {
            return 0
        }
    }

    def deleteAllNodes(){
        List<CPMnodes> nodes = CPMnodes.findAll([sort: "nodeId", order: "asc"]) ;
        for (CPMnodes node:nodes){
            node.delete(flush: true)
        }
        chain(controller: "CPMnodes", action: "linkNode")
    }

    def saveNode() {

        try {
            String nodeIdParam = params.nodeId
            String nextNodeIdParam = params.nextNodeId
            String startIsImmediatePredecessorYN=params.startIsImmediatePredecessorYN
            int duration=getDuration( nodeIdParam)
            int nodeId = Integer.parseInt(nodeIdParam)
            int nextNodeId = Integer.parseInt(nextNodeIdParam)

            println("nodeId : ${nodeId} nextNodeId : ${nextNodeId} ")

            CPMnodes node =new CPMnodes( nodeId, nextNodeId, duration)
            if(nextNodeId==HIGHEST_NODE){
                node.nextNodeName="END"
            } else{
                node.nextNodeName="${nextNodeId}"
            }

            if (nodeId==0){
                node.startIsImmediatePredecessor=true
                node.earliestFinish=0
                node.earliestStart=0
                node.latestFinish=0
                node.latestStart=0
                node.nodeName="START"
            }else{
                node.nodeName="${nodeId}"
            }
            if (startIsImmediatePredecessorYN!=null){
                if (startIsImmediatePredecessorYN.equalsIgnoreCase("YES")){
                    node.startIsImmediatePredecessor=true
                } else if (startIsImmediatePredecessorYN.equalsIgnoreCase("NO")){
                    node.startIsImmediatePredecessor=false
                }
            }
            node.save(flush: true)
            println("Saved node ${nodeId}")
        } catch (Exception e) {
            e.printStackTrace()
        }
        println("done")
//        chain(controller: "CPMnodes", action: "linkNode")
        CPMnodes lastNode =  CPMnodes.findByNodeId(HIGHEST_NODE)
        if(lastNode!=null && lastNode.nodeId==HIGHEST_NODE ){
            lastNode.delete(flush: true)
        }
        CPMnodes theLastNode =new CPMnodes( HIGHEST_NODE, 0, 0)
        theLastNode.nextNodeId= HIGHEST_NODE
        theLastNode.nodeName="END"
        theLastNode.nextNodeName="END"
        theLastNode.save(flush: true)

        setNodeStartAndFinishTimes()
    }

    def clearNodeStartAndFinishTimes() {
        List<CPMnodes> nodes = CPMnodes.findAll([sort: "nodeId", order: "asc"]);
        for (CPMnodes node1 : nodes) {
            node1.earliestStart = HIGHEST_NODE
            node1.latestStart=0
            node1.earliestFinish = 0
            node1.latestFinish = 0
            node1.save(flush: true)
        }

    }


    def setNodeStartAndFinishTimes() {
        clearNodeStartAndFinishTimes()
        setEarliestStartEarliestFinishTimes()
        setLatestStartLatestFinishTimes()
        setSlack()
        setSchedule()
        chain(controller: "CPMnodes", action: "linkNode")
    }

    def setLatestStartLatestFinishTimes(){
        List<CPMnodes> nodesAsc = CPMnodes.findAll([sort: "nodeId", order: "asc"]);
        List<CPMnodes> nodesDesc = CPMnodes.findAll([sort: "nodeId", order: "desc"]);
        int latestFinish

        for (CPMnodes node : nodesDesc) {
            latestFinish=0
            node.latestStart=node.earliestStart
            node.latestFinish=node.earliestFinish
            for (CPMnodes nextNode : nodesDesc) {
                if (node.nextNodeId==nextNode.nodeId){
                    if (latestFinish<nextNode.earliestStart){
                        latestFinish=nextNode.earliestStart
                    }
                }
            }
            int latestStart=latestFinish-node.duration
            node.latestFinish=latestFinish
            if (latestStart>=0){
                node.latestStart=latestStart
            }

        }

        for (CPMnodes node:nodesAsc){
            for (CPMnodes sameNode:nodesAsc){
                if(node.nodeId==sameNode.nodeId && node.latestFinish<sameNode.latestFinish) {
                    node.latestFinish=sameNode.latestFinish
                    node.latestStart=node.latestFinish-node.duration
                    node.save(flush: true)
                }
            }
        }
    }

    def setEarliestStartEarliestFinishTimes(){
        List<CPMnodes> nodesAsc = CPMnodes.findAll([sort: "nodeId", order: "asc"]);
        int earliestStart
        int latestEarliestFinish=0

        for (CPMnodes node:nodesAsc){
            if(node.nodeName.equalsIgnoreCase("START")){
                node.earliestStart=0
                node.earliestFinish=0
                node.latestStart=0
                node.latestFinish=0
                node.slack=0
                node.save(flush: true)
                continue
            } else
            if(node.nodeName.equalsIgnoreCase("END")){
                node.earliestStart=latestEarliestFinish
                node.earliestFinish=latestEarliestFinish
                node.latestStart=latestEarliestFinish
                node.latestFinish=latestEarliestFinish
                node.slack=0
                node.save(flush: true)
                continue
            }
            earliestStart=0

            for (CPMnodes previousNode:nodesAsc){
                if (previousNode.nextNodeId==node.nodeId){
                    if (earliestStart<previousNode.earliestFinish){
                        earliestStart=previousNode.earliestFinish
                    }
                }
            }
            node.slack=0
            node.earliestStart=earliestStart
            node.earliestFinish=node.duration + earliestStart
            if (latestEarliestFinish<node.earliestFinish) {
                latestEarliestFinish=node.earliestFinish
            }
            node.save(flush: true)
        }
        for (CPMnodes node:nodesAsc){
            for (CPMnodes sameNode:nodesAsc){
                if(node.nodeId==sameNode.nodeId && node.earliestStart<sameNode.earliestStart) {
                    node.earliestStart=sameNode.earliestStart
                    node.earliestFinish=node.duration+node.earliestStart
                    node.save(flush: true)
                }
            }
        }

    }


    def setSlack() {
        List<CPMnodes> nodes = CPMnodes.findAll([sort: "nodeId", order: "asc"]);
        for (CPMnodes node : nodes) {
            node.slack = node.latestFinish - node.earliestFinish
            if(node.slack==0){
                node?.isInCriticalPath =true
                Schedule schedule=Schedule.findByNodeId(node.nodeId)
                if (schedule!=null){
                    schedule?.criticalPath =true
                    schedule.save(flush: true)
                }
            }else{
                node?.isInCriticalPath =false
                Schedule schedule=Schedule.findByNodeId(node.nodeId)
                if (schedule!=null){
                    schedule?.criticalPath =false
                    schedule.save(flush: true)
                }

            }
            node.save(flush: true)
        }
    }


    def setSchedule(){
        List<Schedule> schedules = Schedule.findAllByCriticalPath(true,[sort: "nodeId", order: "asc"]) ;
        for (Schedule schedule: schedules) {
            CPMnodes node = CPMnodes.findByNodeId(schedule?.nodeId)
            if (node!=null){
                schedule.earliestStart=node.earliestStart
                schedule.criticalPath=node.isInCriticalPath
            }
        }
    }
}
