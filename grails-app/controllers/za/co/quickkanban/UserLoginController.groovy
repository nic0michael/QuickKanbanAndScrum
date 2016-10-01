package za.co.quickkanban

class UserLoginController {
    def scaffold = true
//    def index() { }
    def Administrator_Functions(){}
    def CPM_Scheduling(){}
    def Export_Functions(){}
    def Import_Functions(){}
    def Kanban_Functions(){}
    def Management_Functions(){}
    def Scrum_Scheduling(){}

    def license(){}
    def readmeFirst(){}
    def logon(){}
    def logout(){
        session.invalidate()
        redirect(action: "logon")
    }
    def landingPage(){}
    def admin(){}
    def kanbanBoardHighLevel(){}
    def kanbanBoardLowLevel(){}
    def scrumTaskBoard(){}
    def highLevelReport(){}
    def lowLevelReport(){}
    def rejected(){}
    def archived(){}
    def userstoryAndSubtasks(){}


    def exportUserStory(){}
    def exportUserStorySubtask(){}

    def showControllers(){
        session.setAttribute("showControllers","YES")
        chain( action: "landingPage")
    }
    def hideControllers(){
        session.setAttribute("showControllers","NO")
        chain( action: "landingPage")
    }

    def user() {
        println("User Called")
        def user = UserLogin.findByUserIdAndPassword(params.loginname, params.password)

         if(user) {
            session.setAttribute("userId", user.userId)
            session.setAttribute("fullName", user.fullName)
            flash.message = "Hello ${user.userId}!"
            println("Success for userId: ${user.userId} ") //    password : ${user.password}")

            if (user.isDissabled) {
                 println("sorry userId: ${params.loginname}  password : ${params.password} is disabled")
                 flash.message = "Sorry, ${params.userName} is disabled Please try again."
                 session.invalidate()
                 redirect(action: "logon")

             }  else if (user.isAdministrator) {
                 session.setAttribute("isAdmin", "TRUE")
                 session.setAttribute("errorMessage", " ")
                 chain( action: "landingPage")

            } else {
                session.setAttribute("isAdmin", "FALSE")
                session.setAttribute("errorMessage", " ")
                chain( action: "landingPage")

            }

        }  else {   // make sure you create and disable this user after installation
             if("klidi".equals(params.loginname) && "P@55w0rd".equals(params.password)) {
                 session.setAttribute("userId", "adminKl")
                 session.setAttribute("fullName", "Administrator")
                 session.setAttribute("isAdmin", "TRUE")
                 chain( action: "landingPage")
             } else {
                 flash.message = "Sorry, ${params.userName}. Please try again."
                 println("sorry userId: ${params.loginname}  password : ${params.password}")
                 session.invalidate()
                 redirect(action: "logon")
             }
        }
    }


    def prePopulte() {

        def states=[
                [stateId:0 ,description: 'UNASSIGNED'],
                [stateId:1 ,description: 'TO DO'],
                [stateId:2 ,description: 'IN PROGRESS'],
                [stateId:3 ,description: 'CODE REVIEW'],
                [stateId:4 ,description: 'TESTING'],
                [stateId:5 ,description: 'READY'],
                [stateId:6 ,description: 'RELEASED'],
                [stateId:7 ,description: 'REJECTED'],
                [stateId:8 ,description: 'ARCHIVED']
        ]

        try {
            states.each {
                new UserStorySubtaskState(it).save(flush: true)
            }
        } catch (Exception e) {
        }

//        chain( controller:"userLogin" , action: "landingPage")
        chain( controller:"setup" , action: "prePopulate")

    }
}
