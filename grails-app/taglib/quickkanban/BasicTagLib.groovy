package quickkanban

import za.co.quickkanban.UserLogin
import za.co.quickkanban.UserStory
import za.co.quickkanban.UserStorySubtaskState
import za.co.quickkanban.UserStorySubtask
import za.co.quickkanban.Schedule

import za.co.quickkanban.Setup

import za.co.quickkanban.CPMnodes

class BasicTagLib {
    static namespace = "tag"

    int HIGHEST_NODE=99999

    def showScrum  = { attrs, body ->
        try {
            Setup setup =Setup.findBySetupId("MASTER_RECORD")
            if (  setup?.showScrumfunctions) {
                out << body()
            }
        } catch (Exception e) {
            e.printStackTrace()
        }
    }

    def showCPM = { attrs, body ->
        try {
            Setup setup =Setup?.findBySetupId("MASTER_RECORD")
            if ( setup?.showCPMfunctions) {
                out << body()
            }
        } catch (Exception e) {
            e.printStackTrace()
        }
    }

    def notInitialized = { attrs, body ->
        Setup setup =Setup?.findBySetupId("MASTER_RECORD")
        if (setup==null) {
            out << body()
        }
    }

    def isInitialized = { attrs, body ->
        Setup setup =Setup?.findBySetupId("MASTER_RECORD")
        if (setup!=null) {
            out << body()
        }
    }


    def showControllers = {   attrs, body ->
        String showControllers=attrs?.showControllers
        if (showControllers!=null && showControllers.equals("YES")) {
            out << body()
        }
    }

    def isAdmin = { attrs, body ->
        try {
            String userId=attrs.userId
            def user = UserLogin?.findByUserId(attrs.userId)
            println("isAdmin : attrs.loginname "+attrs.userId)
            if (userId!=null && userId?.equals("adminKl")) {
                out << body()
            } else if (user && user?.isAdministrator && ! user?.isDissabled ) {
                out << body()
            }
        } catch (Exception e) {
        }
    }




    def isAuthenticated = { attrs, body ->
        try {
            String userId=attrs.userId
            def user = UserLogin.findByUserId(attrs.userId)
            println("isAuthenticated : attrs.loginname "+attrs.userId)
            if (userId!=null && userId?.equals("adminKl")) {
                out << body()
            } else if (user && ! user?.isDissabled ) {
                out << body()
            }
        } catch (Exception e) {
        }
    }

    def isNotAuthenticated = { attrs, body ->
        try {
            String userId=attrs.userId
            def user = UserLogin.findByUserId(attrs.userId)
            println("isAuthenticated : attrs.loginname "+attrs.userId)
            if (userId!=null && userId?.equals("adminKl")) {
            } else if (!user || ! user?.isDissabled ) {
                out << body()
            }
        } catch (Exception e) {
            out << body()
        }
    }

    def isAuthenticatedAndNotAdmin = { attrs, body ->

        def user = UserLogin.findByUserId(attrs.userId)
        println("isAuthenticated : attrs.loginname "+attrs.userId)
        if (user && !user?.isAdministrator && ! user?.isDissabled ) {
            out << body()
        }
    }


    def getKanbanBoardHighLevelTags = { attrs, body ->
        out << getKanbanBoardHighLevelTd(attrs.stateId)
    }

    def getKanbanBoardHighLevelTd(String stateId){
        StringBuilder tags=new StringBuilder()
        Long id=getLong( stateId)
        def state=UserStorySubtaskState.findByStateId(id)
//        def values  = UserStory.findAllByState(state,[sort: "priority", order: "asc"])
        def values  = UserStory.findAllByState(state,[sort: "priority", order: "desc", sort : "userStoryId"])

        tags.append("\n<table>")
        values.each {
            tags.append("\n <tr>")      // "../../userStory/show/1"
            tags.append("\n <td><a href=\"../userStory/show/${it?.id}\"> ${it?.userStoryId}<a></td>")
//            tags.append("<td style=\"border: 1px solid black;\">${it.description} </td>")
            tags.append("\n </tr>")
        }
        tags.append("\n</table>")

        return tags.toString()
    }

    def getScrumUserStoryTags = { attrs, body ->
        out << getScrumUserStoryTd(attrs.stateId)
    }
    def getScrumUserStoryTd(String stateId){
        StringBuilder tags=new StringBuilder()

        Long id=getLong( stateId)
        def state=UserStorySubtaskState.findByStateId(id)
        List <UserStory> stories = UserStory.findAllByState(state);

        tags.append("\n<table>")
        stories.each {
            tags.append("\n <tr>   ")
            tags.append("\n <td><a href=\"../userStory/show/${it?.id}\"> ${it?.userStoryId.toUpperCase()} </a>")
            tags.append("\n </td>")
            tags.append("\n </tr>")
        }
        tags.append("\n</table>")

        return tags.toString()
    }

    def getKanbanBoardLowLevelDualTags = { attrs, body ->
        out << getKanbanBoardLowLevelDualTd(attrs.stateId,attrs.stateId2)
    }


    def getKanbanBoardLowLevelDualTd(String stateId,String stateId2 ){
        StringBuilder tags=new StringBuilder()
        Long id=getLong( stateId)
        Long id2=getLong( stateId2)
        def state=UserStorySubtaskState.findByStateId(id)
        def state2=UserStorySubtaskState.findByStateId(id2)
        List<UserStorySubtask> values  = UserStorySubtask.findAllByState(state);
        List<UserStorySubtask>  values2  = UserStorySubtask.findAllByState(state2);
        values.addAll(values2)

        tags.append("\n<table>")
        values.each {
            UserStory story= it.userStory;
            tags.append("\n <tr>")      // "../../userStory/show/1"
            tags.append("\n <td><a href=\"../userStorySubtask/show/${it?.id}\">${it.userStorySubtaskId}<a><br>(<a href=\"../userStory/show/${story?.id}\">${story?.userStoryId}<a> )<br>[${it?.userAssignedTo}]</td>")
//            tags.append("<td style=\"border: 1px solid black;\">${it.description} </td>")
            tags.append("\n </tr>")
        }
        tags.append("\n</table>")

        return tags.toString()
    }

    def getKanbanBoardLowLevelTags = { attrs, body ->
        out << getKanbanBoardLowLevelTd(attrs.stateId)
    }

    def getKanbanBoardLowLevelTd(String stateId){
        StringBuilder tags=new StringBuilder()
        int id= stateId.toInteger()
        def state=UserStorySubtaskState.findByStateId(id)
        def values  = UserStorySubtask.findAllByState(state,[sort: "userPriority", order: "desc"]);

        tags.append("\n<table>")
        values.each {
            UserStory story= it?.userStory;
            tags.append("\n <tr>")      // "../../userStory/show/1"
            tags.append("\n <td><a href=\"../userStorySubtask/show/${it?.id}\">${it?.userStorySubtaskId}<a><br>(<a href=\"../userStory/show/${story?.id}\">${story?.userStoryId}<a> )<br>[${it?.userAssignedTo}]</td>")
//            tags.append("<td style=\"border: 1px solid black;\">${it.description} </td>")
            tags.append("\n </tr>")
        }
        tags.append("\n</table>")

        return tags.toString()
    }

    def getHighLevelTags = { attrs, body ->
        out << getHighLevelTd(attrs.stateId)
    }

    def getHighLevelTd(String stateId){
        StringBuilder tags=new StringBuilder()
        Long id=getLong( stateId)
        def state=UserStorySubtaskState.findByStateId(id)
        def values  = UserStory.findAllByState(state);

        tags.append("\n<table>")


        tags.append("\n <tr>")
        tags.append("\n <td>USERSTORY ID</td>")
        tags.append("\n <td>DESCRIPTION</td>")
        tags.append("\n <td>DATE STATE CHANGED</td>")
        tags.append("\n </tr>")

        values.each {
            tags.append("\n <tr>")
            tags.append("\n <td><a href=\"../userStory/show/${it?.id}\"> ${it?.userStoryId}<a></td>")
            tags.append("\n <td>${it?.description}</td>")
            tags.append("\n <td>${it?.dateStateChanged}</td>")
//            tags.append("<td style=\"border: 1px solid black;\">${it.description} </td>")
            tags.append("\n </tr>")
        }
        tags.append("\n</table>")

        return tags.toString()
    }



    def currentDateFormat =     { attrs, body ->
        out << getCurrentDateFormat(attrs.dateFormat)
    }
    def getCurrentDateFormat(String dateFormat){
        Setup setup =Setup.findBySetupId("MASTER_RECORD")
        if (setup!=null){
            return "The date format is"+setup.dateFormat
        }   else{
            return "Please me sure you have imported and set up the Master record"
        }
    }

    def getUserStoryAndSubtasksTags = { attrs, body ->
        out << getUserStoryAndSubtasksTagsTd(attrs.stateId)
    }

    def getUserStoryAndSubtasksTagsTd(){
        StringBuilder tags=new StringBuilder()
        def values  = UserStory.findAll([sort: "userStoryId", order: "asc"]);

        tags.append("\n<table>")


        tags.append("\n <tr>")
        tags.append("\n <td>USERSTORY ID</td>")
        tags.append("\n <td>DESCRIPTION</td>")
        tags.append("\n <td>SUBTASK</td>")
        tags.append("\n </tr>")

        values.each {
            def  tasks=UserStorySubtask.findAllByUserStory(it)
            tags.append("\n <tr>")
            tags.append("\n <td><a href=\"../userStory/show/${it?.id}\"> ${it?.userStoryId}<a></td>")
            tags.append("\n <td><b>${it?.description}</b></td>")
            tags.append("\n <td>")
            tags.append("\n<table>")
            tasks.each {
                tags.append("\n <tr>")
                tags.append("\n <td><a href=\"../userStorySubtask/show/${it?.id}\">${it.userStorySubtaskId}<a></td>")
                tags.append("\n <td>${it?.description}</td>")
                tags.append("\n </tr>")
            }
            tags.append("\n</table>")
            tags.append("\n </td>")
            tags.append("\n </tr>")
        }
        tags.append("\n</table>")

        return tags.toString()
    }

    def yesNoDropDown  = { attrs ->
        out << getYesNoDropDown(attrs.id,attrs.name,attrs.selectedValue,attrs.selectedLabel )
    }

    def String getYesNoDropDown(String id,String name,String selectedValue,String selectedLabel ){
        StringBuilder tags = new StringBuilder()

        tags.append("\n<select ")

        if (name != null && name.length() >0) {
            tags.append(" name=")
            tags.append('"')
            tags.append(name)
            tags.append('"')
            tags.append(" ")
        }
        if (id != null && id.length() >0) {
            tags.append(" id=")
            tags.append('"')
            tags.append(id)
            tags.append('"')
            tags.append(" ")
        }
        tags.append(" >")

        if (selectedValue != null && selectedValue.length() >0 && selectedLabel!=null && selectedLabel.length() >0) {
            tags.append("\n<option selected=")
            tags.append('"')
            tags.append(selectedValue)
            tags.append('"')
            tags.append(">default : ${selectedLabel} </option>");
        }

        tags.append("\n<option value='YES' >Yes</option>");
        tags.append("\n<option value='NO' >No</option>");
        tags.append("\n</select>")

        return tags.toString()

    }

    def subtasksDropDown = { attrs ->
        out << getSubtasksDropDown(attrs.id,attrs.name,attrs.selectedValue,attrs.selectedLabel )
    }


    def String getSubtasksDropDown(String id,String name,String selectedValue,String selectedLabel ){
        List<UserStorySubtask> subtasks = UserStorySubtask.findAll([sort: "userStorySubtaskId", order: "asc"]) ;
        StringBuilder tags = new StringBuilder()

        tags.append("\n<select ")

        if (name != null && name.length() >0) {
            tags.append(" name=")
            tags.append('"')
            tags.append(name)
            tags.append('"')
            tags.append(" ")
        }
        if (id != null && id.length() >0) {
            tags.append(" id=")
            tags.append('"')
            tags.append(id)
            tags.append('"')
            tags.append(" ")
        }
        tags.append(" >")

        if (selectedValue != null && selectedValue.length() >0 && selectedLabel!=null && selectedLabel.length() >0) {
            tags.append("\n<option selected=")
            tags.append('"')
            tags.append(selectedValue)
            tags.append('"')
            tags.append(">default : ${selectedLabel} </option>");
        }

        subtasks.each {
            tags.append("\n<option value=")
            tags.append('"')
            tags.append("${it.userStorySubtaskId}")
            tags.append('"')
            tags.append(" >")
            tags.append("${it.userStorySubtaskId} | ${it.description}")
            tags.append("</option>");
        }
        tags.append("\n</select>")

        return tags.toString()
    }

    //////////

    def statesDropDown = { attrs ->
        out << getStatesDropDown(attrs.id,attrs.name,attrs.selectedValue,attrs.selectedLabel )
    }


    def String getStatesDropDown(String id,String name,String selectedValue,String selectedLabel ){
        List<UserStorySubtaskState> states = UserStorySubtaskState.findAll([sort: "stateId", order: "asc"]) ;
        StringBuilder tags = new StringBuilder()

        tags.append("\n<select ")

        if (name != null && name.length() >0) {
            tags.append(" name=")
            tags.append('"')
            tags.append(name)
            tags.append('"')
            tags.append(" ")
        }
        if (id != null && id.length() >0) {
            tags.append(" id=")
            tags.append('"')
            tags.append(id)
            tags.append('"')
            tags.append(" ")
        }
        tags.append(" >")

        if (selectedValue != null && selectedValue.length() >0 && selectedLabel!=null && selectedLabel.length() >0) {
            tags.append("\n<option selected=")
            tags.append('"')
            tags.append(selectedValue)
            tags.append('"')
            tags.append(">default : ${selectedLabel} </option>");
        }

        states.each {
            tags.append("\n<option value=")
            tags.append('"')
            tags.append("${it.stateId}")
            tags.append('"')
            tags.append(" >")
            tags.append(it.description)
            tags.append("</option>");
        }
        tags.append("\n</select>")

        return tags.toString()
    }


    def selectUsers = { attrs ->
        out << getUsersDropDown(attrs.id,attrs.name,attrs.selectedValue,attrs.selectedLabel )
    }

    def String getUsersDropDown(String id,String name,String selectedValue,String selectedLabel ){
        List<UserLogin> users = UserLogin.findAll([sort: "fullName", order: "asc"]) ;
        StringBuilder tags = new StringBuilder()

        tags.append("\n<select ")

        if (name != null && name.length() >0) {
            tags.append(" name=")
            tags.append('"')
            tags.append(name)
            tags.append('"')
            tags.append(" ")
        }
        if (id != null && id.length() >0) {
            tags.append(" id=")
            tags.append('"')
            tags.append(id)
            tags.append('"')
            tags.append(" ")
        }
        tags.append(" >")

        if (selectedValue != null && selectedValue.length() >0 && selectedLabel!=null && selectedLabel.length() >0) {
            tags.append("\n<option selected=")
            tags.append('"')
            tags.append(selectedValue)
            tags.append('"')
            tags.append(">default : ${selectedLabel} </option>");
        }

        users.each {
            tags.append("\n<option value=")
            tags.append('"')
            tags.append("${it.userId}")
            tags.append('"')
            tags.append(" >")
            tags.append(it.fullName)
            tags.append("</option>");
        }
        tags.append("\n</select>")

        return tags.toString()
    }

    Long getLong(String str){
        Long value=new Long(str)
        return value
    }

    ////////////

    def nodeDropDown = { attrs ->
        out << getNodesDropDown(attrs.id,attrs.name )
    }


    def String getNodesDropDown(String id,String name ){
        List<Schedule> schedules = Schedule.findAll([sort: "nodeId", order: "asc"]) ;
        StringBuilder tags = new StringBuilder()

        tags.append("\n<select ")

        if (name != null && name.length() >0) {
            tags.append(" name=")
            tags.append('"')
            tags.append(name)
            tags.append('"')
            tags.append(" ")
        }
        if (id != null && id.length() >0) {
            tags.append(" id=")
            tags.append('"')
            tags.append(id)
            tags.append('"')
            tags.append(" ")
        }
        tags.append(" >")


        tags.append("\n<option value=")
        tags.append('"')
        tags.append("0")
        tags.append('"')
        tags.append(" >")
        tags.append("FIRST NODE")
        tags.append("</option>");

        schedules.each {
            tags.append("\n<option value=")
            tags.append('"')
            tags.append("${it.nodeId}")
            tags.append('"')
            tags.append(" >")
            tags.append(it?.nodeId + " | "+it?.subtask?.userStorySubtaskId)
            tags.append("</option>");
        }

        tags.append("\n<option value=")
        tags.append('"')
        tags.append(HIGHEST_NODE)
        tags.append('"')
        tags.append(" >")
        tags.append("LAST NODE")
        tags.append("</option>");
        tags.append("\n</select>")

        return tags.toString()
    }

    def userStoryDropDown = { attrs ->
        out << getUserStoryDropDown(attrs.id,attrs.name,attrs.selectedValue, attrs.selectedLabel )
    }

    def String getUserStoryDropDown(String id,String name ,String selectedValue, String selectedLabel){
        List<UserStory> userStories = UserStory.findAll([sort: "userStoryId", order: "asc"]) ;
        StringBuilder tags = new StringBuilder()

        tags.append("\n<select ")

        if (name != null && name.length() >0) {
            tags.append(" name=")
            tags.append('"')
            tags.append(name)
            tags.append('"')
            tags.append(" ")
        }
        if (id != null && id.length() >0) {
            tags.append(" id=")
            tags.append('"')
            tags.append(id)
            tags.append('"')
            tags.append(" ")
        }
        tags.append(" >")

        if (selectedValue != null && selectedValue.length() >0 && selectedLabel!=null && selectedLabel.length() >0) {
            tags.append("\n<option selected=")
            tags.append('"')
            tags.append(selectedValue)
            tags.append('"')
            tags.append(">default : ${selectedLabel} </option>");
        }

        userStories.each {
            tags.append("\n<option value=")
            tags.append('"')
            tags.append("${it.userStoryId}")
            tags.append('"')
            tags.append(" >")
            tags.append(it?.userStoryId + " | "+it?.description)
            tags.append("</option>");
        }


        tags.append("\n</select>")

        return tags.toString()
    }

    def listSubTasks ={
        out <<  getSubTaskTable()
    }
    /*
      int nodeId
    UserStorySubtask subtask
    int duration
    */
    def String  getSubTaskTable(){
        List<Schedule> schedules = Schedule.findAll([sort: "nodeId", order: "asc"]) ;
        StringBuilder tags=new StringBuilder()

        tags.append("\n<table class=\"blingTable\" cellspacing=\"0\" >")
        tags.append("\n<tr>")
        tags.append("\n <td>&nbsp;&nbsp;</td>")
        tags.append("\n<td class=\"green1Head\"><b>&nbsp;NODE ID&nbsp;</b></td>")
        tags.append("\n<td class=\"green1Head\"><b>&nbsp;SUBTASK&nbsp;</b></td>")
        tags.append("\n<td class=\"green1Head\"><b>&nbsp;DURATION&nbsp;</b></td>")
        tags.append("\n <td>&nbsp;&nbsp;</td>")
        tags.append("\n </tr>")
        schedules.each {
            tags.append("\n <tr>")
            tags.append("\n <td>&nbsp;&nbsp;</td>")
            tags.append("\n<td style=\"border: 1px solid black;\"> <a href=\"../schedule/show/${it?.id}\" >${it?.nodeId}</a></td>")
            tags.append("\n<td style=\"border: 1px solid black;\">${it?.subtask}</td>")
            tags.append("\n<td style=\"border: 1px solid black;\">${it?.duration}</td>")
            tags.append("\n <td>&nbsp;&nbsp;</td>")
            tags.append("\n </tr>")
        }
        tags.append("\n</table>")
        return tags.toString()

    }

    def listNodes ={
        out <<  listNodes()
    }

    def String  listNodes(){
        List<CPMnodes> nodes = CPMnodes.findAll([sort: "nodeId", order: "asc"]) ;
        StringBuilder tags=new StringBuilder()

        tags.append("\n<table class=\"blingTable\" cellspacing=\"0\" >")
        tags.append("\n<tr>")
        tags.append("\n <td>&nbsp;&nbsp;</td>")
        tags.append("\n<td class=\"green1Head\"><b>&nbsp;NODE &nbsp;</b></td>")
        tags.append("\n<td class=\"green1Head\"><b>&nbsp;NEXT NODE &nbsp;</b></td>")
        tags.append("\n<td class=\"green1Head\"><b>&nbsp;DURATION&nbsp;</b></td>")
        tags.append("\n<td class=\"green1Head\"><b>&nbsp;EARLIEST START&nbsp;</b></td>")
        tags.append("\n<td class=\"green1Head\"><b>&nbsp;EARLIEST FINISH&nbsp;</b></td>")
        tags.append("\n<td class=\"green1Head\"><b>&nbsp;LATEST START&nbsp;</b></td>")
        tags.append("\n<td class=\"green1Head\"><b>&nbsp;LATEST FINISH&nbsp;</b></td>")
        tags.append("\n<td class=\"green1Head\"><b>&nbsp;SLACK&nbsp;</b></td>")
        tags.append("\n <td>&nbsp;&nbsp;</td>")
        tags.append("\n </tr>")
        nodes.each {
            tags.append("\n <tr>")
            tags.append("\n <td>&nbsp;&nbsp;</td>")
            tags.append("\n<td style=\"border: 1px solid black;\"> <a href=\"../CPMnodes/show/${it?.id}\" >${it?.nodeName}</a></td>")
            tags.append("\n<td style=\"border: 1px solid black;\">${it?.nextNodeName}</td>")
            tags.append("\n<td style=\"border: 1px solid black;\">${it?.duration}</td>")
            tags.append("\n<td style=\"border: 1px solid black;\">${it?.earliestStart}</td>")
            tags.append("\n<td style=\"border: 1px solid black;\">${it?.earliestFinish}</td>")
            tags.append("\n<td style=\"border: 1px solid black;\">${it?.latestStart}</td>")
            tags.append("\n<td style=\"border: 1px solid black;\">${it?.latestFinish}</td>")
            tags.append("\n<td style=\"border: 1px solid black;\">${it?.slack}</td>")
            tags.append("\n <td>&nbsp;&nbsp;</td>")
            tags.append("\n </tr>")
        }
        tags.append("\n</table>")
        return tags.toString()
    }


    def listCriticalPathNodes ={
        out <<  listCpNodes()
    }


    def String  listCpNodes(){
        List<Schedule> schedules = Schedule.findAllByCriticalPath(true,[sort: "nodeId", order: "asc"]) ;
        StringBuilder tags=new StringBuilder()

        tags.append("\n<table class=\"blingTable\" cellspacing=\"0\" >")
        tags.append("\n<tr>")
        tags.append("\n <td>&nbsp;&nbsp;</td>")
        tags.append("\n<td class=\"green1Head\"><b>&nbsp;NODE ID&nbsp;</b></td>")
        tags.append("\n<td class=\"green1Head\"><b>&nbsp;SUBTASK ID&nbsp;</b></td>")
        tags.append("\n<td class=\"green1Head\"><b>&nbsp;DURATION&nbsp;</b></td>")
        tags.append("\n<td class=\"green1Head\"><b>&nbsp;EARLIEST FINISH&nbsp;</b></td>")
        tags.append("\n<td class=\"green1Head\"><b>&nbsp;LATEST FINISH&nbsp;</b></td>")
        tags.append("\n <td>&nbsp;&nbsp;</td>")
        tags.append("\n </tr>")
        schedules.each {
            try {
                CPMnodes node = CPMnodes?.findByNodeId(it?.nodeId)
                if (node.isInCriticalPath){
                    tags.append("\n <tr>")
                    tags.append("\n <td>&nbsp;&nbsp;</td>")
                    tags.append("\n<td style=\"border: 1px solid black;\">${it?.nodeId}</a></td>")
                    tags.append("\n<td style=\"border: 1px solid black;\">${it?.subtask?.userStorySubtaskId}</td>")
                    tags.append("\n<td style=\"border: 1px solid black;\">${it?.duration}</td>")
                    tags.append("\n<td style=\"border: 1px solid black;\">${node?.earliestFinish}</td>")
                    tags.append("\n<td style=\"border: 1px solid black;\">${node?.latestFinish}</td>")
                    tags.append("\n <td>&nbsp;&nbsp;</td>")
                    tags.append("\n </tr>")
                }
            } catch (Exception e) {
                e.printStackTrace()
            }
        }
        tags.append("\n</table>")
        return tags.toString()
    }

    def getHighLevelReportTags = { attrs, body ->
        out << getHighLevelReportTd(attrs.stateId)
    }

    def getHighLevelReportTd(String stateId){
        StringBuilder tags=new StringBuilder()
        def values  = UserStory.findAll([sort: "userStoryId", order: "asc"]);


        tags.append("\n<table class=\"blingTable\" cellspacing=\"0\" >")
        tags.append("\n<tr>")
        tags.append("\n<td class=\"green1Head\"><b>&nbsp;USERSTORY ID&nbsp;</b></td>")
        tags.append("\n<td class=\"green1Head\"><b>&nbsp;PRIORITY&nbsp;</b></td>")
        tags.append("\n<td class=\"green1Head\"><b>&nbsp;DESCRIPTION ID&nbsp;</b></td>")
        tags.append("\n<td class=\"green1Head\"><b>&nbsp;USER REQUESTING I&nbsp;D</b></td>")
        tags.append("\n<td class=\"green1Head\"><b>&nbsp;STATE&nbsp;</b></td>")
        tags.append("\n<td class=\"green1Head\"><b>&nbsp;DATE STATE CHANGED ID&nbsp;</b></td>")
        tags.append("\n </tr>")
        values.each {
            tags.append("\n <tr>")
            tags.append("\n <td ><a href=\"../userStory/show/${it?.id}\">${it?.userStoryId}<a></td>")
            tags.append("\n<td style=\"border: 1px solid black;\">${it?.priority}</td>")
            tags.append("\n<td style=\"border: 1px solid black;\">${it?.description}</td>")
            tags.append("\n<td style=\"border: 1px solid black;\">${it?.userRequesting}</td>")
            tags.append("\n<td style=\"border: 1px solid black;\">${it?.state.description}</td>")
            tags.append("\n<td style=\"border: 1px solid black;\">${it?.dateStateChanged}</td>")
            tags.append("\n </tr>")
        }
        tags.append("\n</table>")

        return tags.toString()
    }



    def getLowLevelReportTags = { attrs, body ->
        out << getLowLevelReportTd(attrs.stateId)
    }

    def getLowLevelReportTd(String stateId){
        StringBuilder tags=new StringBuilder()
        def values  = UserStorySubtask.findAll([sort: "userStory", order: "asc"]);
//        def values  = UserStorySubtask.findAll([sort: "userPriority",order: "desc"]);
//        def values  = UserStorySubtask.findAll([sort: "userPriority",order: "asc"]);

        tags.append("\n<table class=\"blingTable\" cellspacing=\"0\">")
        tags.append("\n<tr>")
        tags.append("\n<td class=\"green1Head\"><b>&nbsp;USERSTORY ID&nbsp;</b></td>")
        tags.append("\n<td class=\"green1Head\"><b>&nbsp;USERSTORY DESCRIPTION&nbsp;</b></td>")
        tags.append("\n<td class=\"green1Head\"><b>&nbsp;SUBTASK ID&nbsp;</b></td>")
        tags.append("\n<td class=\"green1Head\"><b>&nbsp;SUBTASK DESCRIPTION&nbsp;</b></td>")
        tags.append("\n<td class=\"green1Head\"><b>&nbsp;STATE&nbsp;</b></td>")
        tags.append("\n<td class=\"green1Head\"><b>&nbsp;USER ASSIGNED TO&nbsp;</b></td>")
        tags.append("\n<td class=\"green1Head\"><b>&nbsp;USER PRIORITY&nbsp;</b></td>")
        tags.append("\n<td class=\"green1Head\"><b>&nbsp;COMMENT&nbsp;</b></td>")
        tags.append("\n<td class=\"green1Head\"><b>&nbsp;IMPEDIMENTS&nbsp;</b></td>")
        tags.append("\n<td class=\"green1Head\"><b>&nbsp;DATE STATE CHANGED&nbsp;</b></td>")
        tags.append("\n</tr>")
        values.each {
            def comment=it.comment;
            if (comment==null || comment.length()<1){
                comment="..."
            }
            def impediments=it.impediments
            if (impediments==null || impediments.length()<1){
                impediments="..."
            }
            tags.append("\n <tr>")
            tags.append("\n <td ><a href=\"../userStory/show/${it?.userStory?.id}\"> ${it?.userStory?.userStoryId}<a></td>")
            tags.append("\n <td >${it.userStory.description}</td>")
            tags.append("\n <td ><a href=\"../userStorySubtask/show/${it.id}\">${it?.userStorySubtaskId}<a></td>")
            tags.append("\n <td >${it.description}</td>")
            tags.append("\n<td >${it.state.description}</td>")
            tags.append("\n<td >${it.userAssignedTo.userId}</td>")
            tags.append("\n<td >${it.userPriority}</td>")
            tags.append("\n<td >${comment}</td>")
            tags.append("\n<td >${impediments}</td>")
            tags.append("\n<td >${it.dateStateChanged}</td>")
            tags.append("\n </tr>")
        }
        tags.append("\n</table>")

        return tags.toString()
    }
}
