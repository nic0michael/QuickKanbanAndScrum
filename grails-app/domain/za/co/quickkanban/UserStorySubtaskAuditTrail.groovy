package za.co.quickkanban

class UserStorySubtaskAuditTrail {
    Date lastChanged
    String notes
    UserStorySubtask userStorySubtask
    static constraints = {
        lastChanged(blank:false, nullable:false)
        userStorySubtask(blank:false, nullable:false)
    }
}
