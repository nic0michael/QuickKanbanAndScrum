package za.co.quickkanban

class UserStorySubtask {
    String  userStorySubtaskId
    String description
    UserStory userStory
    UserLogin userAssignedTo
    int userPriority
    String comment
    String impediments
    String supportTicketNumber
    String supportTicketComment
    UserStorySubtaskState state
    int duration
    int daysLeft
    double percentageCompletion
    Date dateStarted
    Date dateCompleted
    Date dateStateChanged
    Date milestoneDate


    static constraints = {
        userStorySubtaskId(blank:false, nullable:false, unique:true, maxSize:50)
        description(blank:false, nullable:false, maxSize:260)
        userStory(blank:false, nullable:false)
        state(blank:false, nullable:false)
        dateStateChanged(blank:false, nullable:false)
        dateStarted(blank:true, nullable:true)
        dateCompleted(blank:true, nullable:true)
        supportTicketNumber(blank:true, nullable:true, maxSize:50)
        supportTicketComment(blank:true, nullable:true, maxSize:260)
        percentageCompletion(blank:false)
    }

    String toString(){return "${userStorySubtaskId} : ${description}"}
}
