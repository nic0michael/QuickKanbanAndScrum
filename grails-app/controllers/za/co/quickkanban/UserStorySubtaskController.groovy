package za.co.quickkanban

class UserStorySubtaskController {
    // Export service provided by Export plugin
    def exportService
    def grailsApplication  //inject GrailsApplication

    def scaffold = true
//    def index() { }
    def manage(){}
    def uploadSubtasksCsv(){}
    def subtask(){}
    def durationCalculator(){}

//    int makeInt(String nr){
//        if(nr==null || nr.length()<1){
//            return 0
//        }  else{
//            return Integer(nr)
//        }
//    }

    def upload(){
        def errorMessage=" "

        try {
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


                    String userStorySubtaskId = fields[0].trim()
                    String description        = fields[1].trim()
                    String userStory          = fields[2].trim()
                    String userAssignedTo     = fields[3].trim()
                    String userPriority       = fields[4].trim()
                    String comment            = fields[5].trim()
                    String impediments        = fields[6].trim()
                    String state              = fields[7].trim()
                    String duration           = fields[8].trim()
                    String percentageCompletionStr  = fields[9].trim()
                    String dateStateChanged   = new Date()
                    String milestoneDate      = fields[10].trim()
                    double percentageCompletion=percentageCompletionStr.toDouble()

                    UserStorySubtaskPojo userStorySubtaskPojo =new UserStorySubtaskPojo(
                            userStorySubtaskId,
                            description       ,
                            userStory         ,
                            userAssignedTo    ,
                            userPriority    ,
                            comment           ,
                            impediments       ,
                            state             ,
                            duration        ,
                            dateStateChanged  ,
                            milestoneDate ,
                            percentageCompletion)

                    UserStorySubtask userStorySubtask =userStorySubtaskPojo.getUserStorySubtask()
                    println("\nImporting domainObject  ${userStorySubtask.toString()}")
                    log.debug("Importing domainObject  ${userStorySubtask.toString()}")
                userStorySubtask.save(flush: true)
                }
                lines ++
            }
        } catch (Exception e) {
            errorMessage="Failed to process Uploaded File with error "+e.message+ " "+e.cause
            e.printStackTrace()
        }

        session.setAttribute("errorMessage", errorMessage)
        chain(controller: "userStorySubtask", action: "uploadSubtasksCsv")
    }
    def exportSubTasks(){

        List<UserStorySubtask> userStorySubtasks = UserStorySubtask.findAll([sort: "userStorySubtaskId", order: "asc"]) ;
        List<UserStorySubtaskPojo> userStorySubtaskPojos = new ArrayList<UserStorySubtaskPojo>()

        for (UserStorySubtask subtasks : userStorySubtasks ){
            userStorySubtaskPojos.add(new UserStorySubtaskPojo(subtasks))
        }

        if(!params.max) params.max = 10

        if(params?.format && params.format != "html"){
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename=UserStorySubTasks.${params.extension}")

            Map labels = ["USERSTORYSUBTASKID"   :"userStorySubtaskId"
                    ,"DESCRIPTION"          :"description"
                    ,"USERSTORY"            :"userStory"
                    ,"USERASSIGNEDTO"       :"userAssignedTo"
                    ,"USERPRIORITY"         :"userPriority"
                    ,"COMMENT"              :"comment"
                    ,"IMPEDIMENTS"          :"impediments"
                    ,"STATE"                :"state"
                    ,"DURATION"             :"duration"
                    ,"PERCENTAGECOMPLETION"     :"percentageCompletion"
                    ,"MILESTONEDATE"        :"milestoneDate"]

            List fields= ["userStorySubtaskId" ,"description" ,"userStory" ,"userAssignedTo"
                    ,"userPriority" ,"comment" ,"impediments" ,"state" ,"duration" ,"percentageCompletion" ,"milestoneDate"]

            // Formatter closure
            def upperCase = { domain, value ->
                return value.toUpperCase()
            }

            Map formatters = [schedule: upperCase]
            Map parameters = [title: "UserStory Subtask", "column.widths": [0.2, 0.3, 0.5]]

            exportService.export(params.format, response.outputStream, userStorySubtaskPojos, fields, labels, formatters, parameters)
        }

        [ userStorySubtasks: userStorySubtaskPojos ]
    }
    def saveSubTask(){
    try {
        String pessimisticParam = params.pessimistic
        String optimisticParam = params.optimistic
        String mostLikelyParam = params.mostLikely
        String userStorySubtaskId = params.subtask

        println("pessimistic : ${pessimisticParam} optimistic : ${optimisticParam} mostLikely : ${mostLikelyParam}")

        int pessimistic = Integer.parseInt(pessimisticParam)
        int optimistic = Integer.parseInt(optimisticParam)
        int mostLikely = Integer.parseInt(mostLikelyParam)
        int duration  =(pessimistic+mostLikely+(4*optimistic))/6
        UserStorySubtask subtask =UserStorySubtask.findByUserStorySubtaskId(userStorySubtaskId)
        subtask.duration=duration
        subtask.save(flush: true)


    } catch (Exception e) {
        e.printStackTrace()
        println("Failed"+e.message)
    }

    println("done")
    chain(controller: "userStorySubtask", action: "durationCalculator")
    }
    def subtaskAssignSetState(){

        String userStorySubtaskId=params.userStorySubtaskId
        String stateId=params.stateId

//        String assignUser=params.assignUser
//        String userId=params.userId
        String assignUserStory=params.assignUserStory
//        String impediments=params.impediments
//        String comment=params.comment
//
//        UserLogin userLogin = UserLogin.findByUserId(userId)
        UserStorySubtaskState userState = UserStorySubtaskState.findByStateId(stateId)
        UserStorySubtask userStorySubtask = UserStorySubtask.findByUserStorySubtaskId(userStorySubtaskId);
        UserStory userStory=userStorySubtask.userStory

        userStorySubtask.dateStateChanged=new Date()
        userStorySubtask.state= userState
        try {
//            if (assignUser!=null && assignUser.length()>0 && assignUser.equalsIgnoreCase("YES")){
//                userStorySubtask.userAssignedTo=userLogin
//
//                if (comment!=null && comment.length()>2){
//                    userStorySubtask.comment=comment
//                }
//
//                if (impediments!=null && impediments.length()>2){
//                    userStorySubtask.impediments=impediments
//                }
//            }
            userStorySubtask.save()

            if (assignUserStory!=null && assignUserStory.length()>0 && assignUserStory.equalsIgnoreCase("YES")){
                userStory.dateStateChanged=new Date()
                userStory.state= userState
                userStory.save()
            }
            session.setAttribute("errorMessage"," ")
        } catch (Exception e) {
            def errorMessage = "Failed to assign state and user to userStorySubtask: " + e.getMessage()
            println(errorMessage)
            println(e.stackTrace)
            session.setAttribute("errorMessage", errorMessage)
            chain( controller: "userStorySubtask", action: "subtask")
        }

        chain( controller: "userLogin", action: "landingPage")

    }


}
