package za.co.quickkanban

class Setup {
    String setupId
    String organizationName
    String projectOwner
    String managerFullName
    String managerTitle
    String projectType
    int sprintDuration
    String dateFormat
    UserStory theDefaultUserStory
    boolean showCPMfunctions
    boolean showScrumfunctions

    static final String DATE_Y2K ='yyyy-MM-dd'
    static final String DATE_BRITISH ='dd/MMM/yyyy'
    static final String DATE_USA ='MMM/dd/yyyy'

    static final String PROJECT_MANAGER ='Project Manager'
    static final String SCRUM_MASTER='Scrum Master'
    static final String A_KANBAN_MASTER='Kanban Master'


    static final String CPM_SCHEDULE_PROJECT ='CPM Scheduled Project'
    static final String SCRUM_PROJECT='Scrum Project'
    static final String A_KANBAN_PROJECT='Kanban Project'

    static constraints = {
        setupId(blank:false, nullable:false,unique: true)
        showCPMfunctions(blank:false, nullable:false)
        showScrumfunctions(blank:false, nullable:false)
        organizationName(blank:false, nullable:false)
        sprintDuration(blank:false, nullable:false)
        projectOwner(blank:true, nullable:true)
        managerFullName(blank:true, nullable:true)
        dateFormat  (blank: false, inList: [
                DATE_Y2K,DATE_BRITISH,DATE_USA
        ]    )
        projectType (blank: false, inList: [
                A_KANBAN_PROJECT,SCRUM_PROJECT,CPM_SCHEDULE_PROJECT
        ]    )
        managerTitle(blank: false, inList: [
                A_KANBAN_MASTER,SCRUM_MASTER,PROJECT_MANAGER
        ]    )
        theDefaultUserStory(blank:true, nullable:true)
    }
}
