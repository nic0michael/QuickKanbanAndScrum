package za.co.quickkanban

/**
 * Created with IntelliJ IDEA.
 * User: NMichael
 * Date: 2014/05/04
 * Time: 7:08 PM
 * To change this template use File | Settings | File Templates.
 */
class UserStorySubtaskPojo {
    String  userStorySubtaskId
    String description
    String userStory
    String userAssignedTo
    int userPriority
    String comment
    String impediments
    String state
    int duration
    String dateStateChanged
    String milestoneDate
    double percentageCompletion

    private UserStorySubtask userStorySubtask= new UserStorySubtask()

    public UserStorySubtaskPojo(
            String userStorySubtaskId,
            String description       ,
            String userStory         ,
            String userAssignedTo    ,
            String userPriority      ,
            String comment           ,
            String impediments       ,
            String state             ,
            String duration          ,
            String dateStateChanged  ,
            String milestoneDate,
            double percentageCompletion    ){

        if(userPriority==null|| userPriority.length()<1){
            userPriority="0"
        }
        if(duration==null|| duration.length()<1){
            duration="0"
        }

        this.userStorySubtaskId=userStorySubtaskId
        this.description       =description
        this.userStory         =userStory
        this.userAssignedTo    =userAssignedTo
        this.userPriority      =userPriority.toInteger()
        this.comment           =comment
        this.impediments       =impediments
        this.state             =state
        this.duration          =duration.toInteger()
        this.dateStateChanged  =dateStateChanged
        this.milestoneDate     =milestoneDate
        this.percentageCompletion=percentageCompletion
    }
    public UserStorySubtaskPojo(UserStorySubtask subtask){
        Setup setup =Setup.findBySetupId("MASTER_RECORD")

        userStorySubtaskId =subtask?.userStorySubtaskId
        description =subtask?.description
        userStory =subtask?.userStory?.userStoryId
        userAssignedTo =subtask?.userAssignedTo?.userId
        userPriority =subtask?.userPriority
        comment =subtask?.comment
        impediments =subtask?.impediments
        state =subtask?.state?.description
        duration =subtask?.duration
        dateStateChanged=subtask?.dateStateChanged?.format(setup?.dateFormat)
        milestoneDate=subtask?.milestoneDate?.format(setup?.dateFormat)
        percentageCompletion=subtask?.percentageCompletion
    }

    private void setUserStorySubtask(){
        Setup setup =Setup.findBySetupId("MASTER_RECORD")
        try {
            this.userStorySubtask.userStorySubtaskId=this.userStorySubtaskId
            this.userStorySubtask.description=this.description
            this.userStorySubtask.userStory=UserStory?.findByUserStoryId(this.userStory)
            this.userStorySubtask.userAssignedTo=UserLogin?.findByUserId(this.userAssignedTo)
            this.userStorySubtask.userPriority=this.userPriority
            this.userStorySubtask.comment=this.comment
            this.userStorySubtask.impediments=this.impediments
            this.userStorySubtask.state=UserStorySubtaskState?.findByDescription(this.state)
            this.userStorySubtask.duration=this.duration
            this.userStorySubtask.dateStateChanged=new Date()
            this.userStorySubtask.milestoneDate=new Date().parse(setup?.dateFormat, this.milestoneDate)
            this.userStorySubtask.percentageCompletion=this.percentageCompletion
        } catch (Exception e) {
            e.printStackTrace()
        }
    }

    public UserStorySubtask getUserStorySubtask(){
        setUserStorySubtask()
        return userStorySubtask;
    }
}
