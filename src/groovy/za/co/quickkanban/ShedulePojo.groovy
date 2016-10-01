package za.co.quickkanban

/**
 * Created with IntelliJ IDEA.
 * User: NMichael
 * Date: 2014/05/04
 * Time: 5:41 PM
 * To change this template use File | Settings | File Templates.
 */
class ShedulePojo {

    int nodeId
    String userStorySubtaskId
    int duration
    String startDate
    String endDate
    int earliestStart
    boolean criticalPath

    public ShedulePojo( Schedule sched){

        Setup setup =Setup.findBySetupId("MASTER_RECORD")
        UserStory story = setup?.theDefaultUserStory
//        Date projectStartDate = story?.startDate

        nodeId=sched?.nodeId
        userStorySubtaskId=sched?.subtask?.userStorySubtaskId
        duration=sched?.duration
        startDate=sched?.startDate?.format(setup?.dateFormat)
        endDate=sched?.endDate?.format(setup?.dateFormat)
        earliestStart=sched?.earliestStart
        criticalPath=sched?.criticalPath
    }
}
