package za.co.quickkanban

class UserStorySubtaskState {
    int stateId
    String description

    static constraints = {
        stateId(blank:false, nullable:false, unique:true)
        description(blank:false, nullable:false, maxSize:50)
    }


    String toString(){return "${description}"}
}
