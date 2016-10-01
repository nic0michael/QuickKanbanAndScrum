package za.co.quickkanban

class Schedule {
    int nodeId
    UserStorySubtask subtask
    int duration
    Date startDate
    Date endDate
    int earliestStart
    boolean criticalPath

    public Schedule(int nodeId,
                    int optimisticDuration,
                    int pessimisticDuration,
                    int mostLikelyDuration
    ){
        this.nodeId=nodeId
        this.duration=(optimisticDuration+pessimisticDuration+(4*mostLikelyDuration))/6
    }


    public Schedule(int nodeId,
                    UserStorySubtask subtask,
                    int optimisticDuration,
                    int pessimisticDuration,
                    int mostLikelyDuration
    ){
        this.nodeId=nodeId
        this.subtask=subtask
        this.duration=(optimisticDuration+pessimisticDuration+(4*mostLikelyDuration))/6
    }

    static constraints = {
        nodeId(blank:false, nullable:false, unique:true)
        subtask(blank:true, nullable:true, unique:true)
        duration(blank:false, nullable:false)
        earliestStart(blank:true, nullable:true)
    }

    String toString(){"${nodeId} : ${subtask?.userStorySubtaskId} "}
}
