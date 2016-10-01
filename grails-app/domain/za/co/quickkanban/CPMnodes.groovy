package za.co.quickkanban

class CPMnodes {
    int nodeId
    String nodeName
    int nextNodeId
    String nextNodeName
    int duration
    int earliestStart
    int earliestFinish
    int latestStart
    int latestFinish
    int slack
    Date latestStartDate
    Date latestFinishDate
    boolean isInCriticalPath
    boolean startIsImmediatePredecessor

    public CPMnodes(int nodeId,int nextNodeId,int duration){
        this.nodeId=nodeId
        this.nextNodeId=nextNodeId
        this.duration=duration
        this.nodeName=" "
    }

    static constraints = {
        nodeId(blank:false, nullable:false, unique:false)
        nextNodeId(blank:false, nullable:false)
        duration(blank:false, nullable:false)

        nodeName(blank:true, nullable:true)
        nextNodeName(blank:true, nullable:true)
        earliestStart(blank:true, nullable:true)
        earliestFinish(blank:true, nullable:true)
        latestStart(blank:true, nullable:true)
        latestFinish(blank:true, nullable:true)
        slack(blank:true, nullable:true)
        latestStartDate(blank:true, nullable:true)
        latestFinishDate(blank:true, nullable:true)
        isInCriticalPath(blank:true, nullable:true)
    }

    String toString(){
        slack=latestFinish-earliestFinish
        return " ${nodeId}-${nextNodeId} [${duration}] : ${slack}"
    }
}
