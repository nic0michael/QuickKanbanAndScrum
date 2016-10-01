package za.co.quickkanban

class UserStorySubtaskStateController {

    def scaffold = true
//    def index() { }
    def manage(){}
    def prePopulte() {

        session.setAttribute("showControllers","YES")
        def users = [
                [userId: 'UNASSIGNED', password: '78safd7587', fullName: 'unasigned', emailAddress: 'un@un.co.za', cell: '0841234567', isDissabled: true, isAdministrator: false,  lastChanged: new Date()]
        ]
        try {
            users.each {
                new UserLogin(it).save(flush: true)
            }
        } catch (Exception e) {
        }

//        chain( controller:"userLogin" , action: "landingPage")

        chain(controller:"userLogin", action: "prePopulte")
    }
}
