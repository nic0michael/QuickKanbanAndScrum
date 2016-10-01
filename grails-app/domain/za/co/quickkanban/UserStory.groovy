package za.co.quickkanban

class UserStory {
    String userStoryId
    int priority // if > -1 then this has been prioritised
    String description
    Date startDate
    Date completionDate
    UserLogin userRequesting
    UserStorySubtaskState state
    Date dateStateChanged
    String cardType


    static final String USERSTORY='User Story'
    static final String DEFECT='Defect'
    static final String TASK='Task'
    static final String FEATURE='Feature'

    static constraints = {
        userStoryId(blank:false, nullable:false, unique:true, maxSize:50)
        description(blank:false, nullable:false, maxSize:260)
        userRequesting(blank:false, nullable:false)
        dateStateChanged(blank:false, nullable:false)
        state(blank:false,  nullable:false)

        cardType (blank: false, inList: [
//                USERSTORY, DEFECT, TASK, FEATURE
                USERSTORY, DEFECT,  FEATURE
        ]    )
    }

    String toString(){return "${userStoryId}: ${description}"}
}
