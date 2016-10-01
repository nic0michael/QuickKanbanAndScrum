package za.co.quickkanban

/**
 * Created with IntelliJ IDEA.
 * User: NMichael
 * Date: 2014/05/04
 * Time: 8:29 PM
 * To change this template use File | Settings | File Templates.
 */
class UserStoryPojo {
    String userStoryId
    int priority
    String description
    String startDate
    String completionDate
    String userRequesting
    String state
    String  dateStateChanged
    String cardType

    private UserStory story=new UserStory()

    public UserStoryPojo(
            String userStoryId
            , int priority
            , String description
            , String startDate
            , String completionDate
            , String userRequesting
            , String state
            , String cardType ) {

        this.userStoryId       = userStoryId
        this.priority          = priority
        this.description       = description
        this.startDate	       = startDate
        this.completionDate    = completionDate
        this.userRequesting    = userRequesting
        this.state		       = state
        this.dateStateChanged  = new Date()
        this.cardType          = cardType
    }

    public UserStoryPojo(UserStory story) {
    Setup setup =Setup.findBySetupId("MASTER_RECORD")

     userStoryId =story.userStoryId
     priority =story.priority
     description =story.description
     startDate=story?.startDate?.format(setup?.dateFormat)
     completionDate=story?.completionDate?.format(setup?.dateFormat)
     userRequesting=story.userRequesting.userId
     state =story?.state?.description
     dateStateChanged=story?.dateStateChanged?.format(setup?.dateFormat)
     cardType =story.cardType
    }

    public toUserStory(){
        Setup setup =Setup.findBySetupId("MASTER_RECORD")

        story.userStoryId      = userStoryId
        story.priority         = priority
        story.description      = description
        story.startDate	    = new Date().parse(setup?.dateFormat, startDate)
        story.completionDate   = new Date().parse(setup?.dateFormat, completionDate)
        story.userRequesting   = UserLogin?.findByUserId(userRequesting)
        story.state		    = UserStorySubtaskState?.findByDescription(state)
        story.dateStateChanged = new Date()
        story.cardType         = cardType

        return story
    }


}
