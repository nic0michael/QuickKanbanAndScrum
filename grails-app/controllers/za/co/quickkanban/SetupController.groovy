package za.co.quickkanban

class SetupController {

    def scaffold = true
//    def index() { }
    def setup(){}

    def uploadUserStoryCsv(){}

    def importUserStories(){
        String errorMessage=""

        int lines=0;
        def file = request.getFile('csvfile')

        if (file.empty) {
            errorMessage= 'file cannot be empty you must select a file'
            session.setAttribute("errorMessage", errorMessage)
            chain(controller: "userStorySubtask", action: "uploadCsv")
        }

//            file.inputStream.splitEachLine(',') { fields ->
        file.inputStream.toCsvReader().eachLine { fields ->
            println("\nfields: " +fields)
            if(lines>0){

                String userStoryId 		= fields[0].trim()
                String prioritySt 		= fields[1].trim()
                String description 		= fields[2].trim()
                String startDate 		= fields[3].trim()
                String completionDate 	= fields[4].trim()
                String userRequesting 	= fields[5].trim()
                String state 			= fields[6].trim()
                String dateStateChangedSt = fields[7].trim()
                String cardType 		= fields[8].trim()
                if(prioritySt==null|| prioritySt.length()<1){
                    prioritySt="0"
                }
                int priority = prioritySt.toInteger()

                UserStoryPojo userStoryPojo = new UserStoryPojo(
                         userStoryId
                        ,  priority
                        , description
                        ,  startDate
                        ,  completionDate
                        ,  userRequesting
                        ,  state
                        ,  cardType
                )
                println("Adding UserStory "+userStoryId)
                try {
                    UserStory userStory= userStoryPojo.toUserStory()
                    userStory.save(flush: true)
                } catch (Exception e) {
                    e.printStackTrace()
                }

            } else{
                println("Skiping first line")
                println(fields[0])
            }
            lines++
        }
        chain(controller: "userLogin", action: "landingPage")
    }

    def saveSetup(){
        println(" SAVE_SETUP")

        try {
            Setup setup =Setup.findBySetupId("MASTER_RECORD")
            String dateFormat=params.dateFormat
            String projectOwner=params.projectOwner
            String managerFullName=params.managerFullName
            String managerTitle=params.managerTitle
            String organizationName=params.organizationName
            String projectType=params.projectType
            String theDefaultUserStoryId=params.theDefaultUserStory
            String showCPMfunctions=params.showCPMfunctions
            String showScrumfunctions=params.showScrumfunctions

            println(" showCPMfunctions "+showCPMfunctions)
            println(" showScrumfunctions "+showScrumfunctions)
            println(" theDefaultUserStoryId "+theDefaultUserStoryId)
            println(" projectType "+projectType)
//            println(" managerFullName "+managerFullName)
            println(" managerTitle "+managerTitle)
//            println(" organizationName "+organizationName)

            setup.managerFullName=managerFullName
            setup.managerTitle=managerTitle
            setup.organizationName=organizationName
            setup.projectType=projectType
            setup.projectOwner=projectOwner
            setup.dateFormat=dateFormat

            if (theDefaultUserStoryId!=null){
                UserStory theDefaultUserStory =  UserStory.findByUserStoryId(theDefaultUserStoryId)
                if (theDefaultUserStory!=null){
                    setup.theDefaultUserStory=theDefaultUserStory
                }
            }

            if (showCPMfunctions!=null) {
                if(showCPMfunctions.equalsIgnoreCase("ON")) {
                    setup.showCPMfunctions=true
                    println(" showCPMfunctions now true")
                }
            }  else {
                setup.showCPMfunctions=false
                println(" showCPMfunctions now false")
            }

            if (showScrumfunctions!=null) {
                if (showScrumfunctions.equalsIgnoreCase("ON")) {
                    setup.showScrumfunctions=true
                    println(" showScrumfunctions now true")
                }
            } else{
                setup.showScrumfunctions=false
                println(" showScrumfunctions now false")
            }
//            setup.setupId="MASTER_RECORD"
        setup.save(flush: true)
        } catch (Exception e) {
            e.printStackTrace()
        }
        chain(controller: "userLogin", action: "landingPage")

    }

    def prePopulate(){
        UserStorySubtaskState na=new UserStorySubtaskState(stateId:0 ,description: 'Not approved and prioritized').save(flush: true)
        UserStorySubtaskState todo=new UserStorySubtaskState(stateId:1 ,description: 'TO DO').save(flush: true)
        UserStorySubtaskState IN_PROGRESS=new UserStorySubtaskState(stateId:2 ,description: 'IN PROGRESS').save(flush: true)
        new UserStorySubtaskState(stateId:3 ,description: 'CODE REVIEW').save(flush: true)
        new UserStorySubtaskState(stateId:4 ,description: 'PREPROD TEST').save(flush: true)
        new UserStorySubtaskState(stateId:5 ,description: 'CODE REVIEW').save(flush: true)
        new UserStorySubtaskState(stateId:6 ,description: 'READY FOR GOLIVE').save(flush: true)
        new UserStorySubtaskState(stateId:7 ,description: 'COMPLETED').save(flush: true)
        new UserStorySubtaskState(stateId:8 ,description: 'RE-OPENED').save(flush: true)

        UserLogin admin=new UserLogin(userId: 'admin', password: 'P@55w0rd', fullName: 'The boss', emailAddress: 'boss@boss.co.za', cell: '0841234567', isDissabled: false, isAdministrator: true,  lastChanged: new Date()).save(flush: true)
        UserLogin user=new UserLogin(userId: 'user' , password: 'qwerty'  , fullName: 'The user', emailAddress: 'user@user.co.za', cell: '0845678901', isDissabled: false, isAdministrator: false, lastChanged: new Date()).save(flush: true)
        UserLogin userRequesting= new UserLogin(userId: 'admin', password: 'P@55w0rd', fullName: 'The boss', emailAddress: 'boss@boss.co.za', cell: '0841234567', isDissabled: false, isAdministrator: true,  lastChanged: new Date()).save(flush: true)
        UserLogin unassigned=new UserLogin(userId: 'UNASSIGNED' , password: '*******'  , fullName: 'The user', emailAddress: 'user@user.co.za', cell: '0845678901', isDissabled: true, isAdministrator: false, lastChanged: new Date()).save(flush: true)

        userRequesting=UserLogin.findByUserId("UNASSIGNED")
        UserStorySubtaskState state=UserStorySubtaskState.findByDescription("UNASSIGNED")
        IN_PROGRESS=UserStorySubtaskState.findByDescription('IN PROGRESS')
        UserStory story=new UserStory(userStoryId:"UNASSIGNED",description:"Blank Story",userRequesting:userRequesting,dateStateChanged:new Date(),startDate:new Date(),completionDate:new Date(),state:state,cardType:'User Story',priority:-1).save(flush: true)
         story=UserStory.findByUserStoryId("UNASSIGNED")

        UserStorySubtask st001=new UserStorySubtask(userStorySubtaskId:'ST_001' , description:'A Subtask 1'
                , userStory:story , userAssignedTo:user
                , userPriority:0 , comment:'Test User Story Subtask 001'
                , impediments:'None' ,state:IN_PROGRESS , duration:10
                , dateStateChanged:new Date()
                , milestoneDate:new Date()).save(flush: true)

        UserStorySubtask st002=new UserStorySubtask(userStorySubtaskId:'ST_002' , description:'A Subtask 2'
                , userStory:story , userAssignedTo:user
                , userPriority:0 , comment:'Test User Story Subtask 002'
                , impediments:'None' ,state:state , duration:10
                , dateStateChanged:new Date()
                , milestoneDate:new Date()).save(flush: true)

        Setup setup=new Setup(setupId:"MASTER_RECORD" , organizationName:'XYZ ACME CO' , projectOwner:'Mr BigBoss' , managerFullName:'Mr T' , managerTitle:'Kanban Master' , projectType:'Kanban Project' ,    sprintDuration:14 , dateFormat:'yyyy-MM-dd' , theDefaultUserStory:story , showCPMfunctions:false , showScrumfunctions:false).save(flush: true)


        chain( controller:"userLogin" , action: "landingPage")
    }
}
