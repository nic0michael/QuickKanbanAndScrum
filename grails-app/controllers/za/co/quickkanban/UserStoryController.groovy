package za.co.quickkanban

class UserStoryController {
    // Export service provided by Export plugin
    def exportService
    def grailsApplication  //inject GrailsApplication

    def scaffold = true
//    def index() { }
    def manage(){}

    def exportUserStory(){


        List<UserStory> userStories = UserStory.findAll([sort: "userStoryId", order: "asc"]) ;
        List<UserStoryPojo> userStoryPojos = new ArrayList<UserStoryPojo>()

        for (UserStory userStory : userStories ){
            userStoryPojos.add(new UserStoryPojo(userStory))
        }

        if(!params.max) params.max = 10

        if(params?.format && params.format != "html"){
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename=UserStories.${params.extension}")

            Map labels = [
                    "USERSTORYID"      :"userStoryId"
                    ,"PRIORITY"         :"priority"
                    ,"DESCRIPTION"      :"description"
                    ,"STARTDATE"        :"startDate"
                    ,"COMPLETIONDATE"   :"completionDate"
                    ,"USERREQUESTING"   :"userRequesting"
                    ,"STATE"            :"state"
                    ,"DATESTATECHANGED" :"dateStateChanged"
                    ,"CARDTYPE"         :"cardType"]

            List fields= [ "userStoryId" ,"priority" ,"description" ,"startDate"
                    ,"completionDate" ,"userRequesting" ,"state" ,"dateStateChanged" ,"cardType"]

            // Formatter closure
            def upperCase = { domain, value ->
                return value.toUpperCase()
            }

            Map formatters = [schedule: upperCase]
            Map parameters = [title: "UserStory Subtask", "column.widths": [0.2, 0.3, 0.5]]

            exportService.export(params.format, response.outputStream, userStoryPojos, fields, labels, formatters, parameters)
        }

        [ userStoryPojos: userStoryPojos ]
    }
    def prePopulate(){
        try {
            UserLogin userRequesting=UserLogin.findByUserId("UNASSIGNED")
            UserStorySubtaskState state=UserStorySubtaskState.findByDescription("UNASSIGNED")
            UserStory story=new UserStory()
            story.userStoryId="Unassigned"
            story.description="Blank Story"
            story.userRequesting=userRequesting
            story.dateStateChanged=new Date()
            story.startDate=new Date()
            story.completionDate=new Date()
            story.state=state
            story.cardType='User Story'
            story.priority=-1
            story.save(flush: true)
            println("Done")
        } catch (Exception e) {
            e.printStackTrace()
        }
//        chain( controller:"userLogin" , action: "landingPage")
    }
}
