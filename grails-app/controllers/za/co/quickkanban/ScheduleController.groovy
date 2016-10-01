package za.co.quickkanban

class ScheduleController {

    // Export service provided by Export plugin
    def exportService
    def grailsApplication  //inject GrailsApplication

    def scaffold = true

    def schedule() { }

    def linkSubTasks() {}

    def timeTable() {}
    def exportSchedule(){

        List<Schedule> scheduleItems = Schedule.findAll([sort: "nodeId", order: "asc"]) ;
        List<ShedulePojo> shedulePojos = new ArrayList<ShedulePojo>()

        for (Schedule sched : scheduleItems ){
            shedulePojos.add(new ShedulePojo(sched))
        }




        if(!params.max) params.max = 10

        if(params?.format && params.format != "html"){
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename=Schedule.${params.extension}")

            Map labels = ["NODE_ID" :"nodeId",
                    "SUBTASK_ID":"subtaskId",
                    "DURATION":"duration",
                    "EARLIEST_START_DATE":"earliestStartDate",
                    "LATEST_END_DATE":"latestEndDate",
                    "EARLIEST_START_DAY":"earliestStart",
                    "CRITICAL_PATH":"criticalPath"]

            List fields= ["nodeId", "userStorySubtaskId", "duration", "startDate", "endDate", "earliestStart", "criticalPath"]

            // Formatter closure
            def upperCase = { domain, value ->
                return value.toUpperCase()
            }

            Map formatters = [schedule: upperCase]
            Map parameters = [title: "Project Schedule", "column.widths": [0.2, 0.3, 0.5]]

            exportService.export(params.format, response.outputStream, shedulePojos, fields, labels, formatters, parameters)
        }

        [ scheduleList: shedulePojos ]
    }

    def setTimeTable() {
        try {
            Setup setup = Setup.findBySetupId("MASTER_RECORD")
            UserStory theDefaultUserStory=setup.theDefaultUserStory
            Date startDate=theDefaultUserStory.startDate
            List<Schedule> scheduleItems = Schedule.findAll([sort: "nodeId", order: "asc"]) ;
            for (Schedule scheduleItem : scheduleItems) {
                def nodeId = scheduleItem.nodeId
                CPMnodes node = CPMnodes.findByNodeId(nodeId)
                scheduleItem.endDate=startDate+node.latestFinish
                scheduleItem.startDate=startDate+node.earliestStart
                scheduleItem.save(flush: true)
            }
        } catch (Exception e) {
            e.printStackTrace()
        }
        chain(controller: "schedule", action: "timeTable")
    }

    def saveSubTasks() {

        try {
            String nodeIdParam = params.nodeId
            String userStorySubtaskId = params.subtask

            int nodeId = Integer.parseInt(nodeIdParam)
            UserStorySubtask subtask = UserStorySubtask.findByUserStorySubtaskId(userStorySubtaskId)
            int duration = subtask.duration

            println("nodeId : ${nodeId} subtask : ${userStorySubtaskId} duration : ${duration}")
            Schedule schedule = new Schedule()
            schedule.nodeId = nodeId
            schedule.subtask = subtask
            schedule.duration = duration
            schedule.startDate = new Date()
            schedule.endDate = new Date()
            schedule.criticalPath = false;
            schedule.save(flush: true)
        } catch (Exception e) {
            e.printStackTrace()
            println("Failed" + e.message)
        }

        println("done")
        chain(controller: "schedule", action: "linkSubTasks")

    }

    def ganttChart() {

    }


    def list = {
    }
}
