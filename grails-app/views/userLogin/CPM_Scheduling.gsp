<!DOCTYPE html>
<html>
<head>
    <%@ page import="quickkanban.BasicTagLib" %>
    <%
        boolean isAdministrator = false;
        boolean isAuthenticated = false;
        String isAdmin = session?.isAdmin;
        String userId = session?.userId;


        if (userId != null) {
            isAuthenticated = true;
        }

        if (isAdmin != null && isAdmin.equals("TRUE")) {
            isAdministrator = true;
        }
    %>

    <meta name="layout" content="main"/>
    <title>CPM Scheduling</title>
    <style type="text/css" media="screen">
    #status {
        background-color: #eee;
        border: .2em solid #fff;
        margin: 2em 2em 1em;
        padding: 1em;
        width: 12em;
        float: left;
        -moz-box-shadow: 0px 0px 1.25em #ccc;
        -webkit-box-shadow: 0px 0px 1.25em #ccc;
        box-shadow: 0px 0px 1.25em #ccc;
        -moz-border-radius: 0.6em;
        -webkit-border-radius: 0.6em;
        border-radius: 0.6em;
    }

    .ie6 #status {
        display: inline; /* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
    }

    #status ul {
        font-size: 0.9em;
        list-style-type: none;
        margin-bottom: 0.6em;
        padding: 0;
    }

    #status li {
        line-height: 1.3;
    }

    #status h1 {
        text-transform: uppercase;
        font-size: 1.1em;
        margin: 0 0 0.3em;
    }

    #page-body {
        margin: 2em 1em 1.25em 18em;
    }

    h2 {
        margin-top: 1em;
        margin-bottom: 0.3em;
        font-size: 1em;
    }

    p {
        line-height: 1.5;
        margin: 0.25em 0;
    }

    #controller-list ul {
        list-style-position: inside;
    }

    #controller-list li {
        line-height: 1.3;
        list-style-position: inside;
        margin: 0.25em 0;
    }

    @media screen and (max-width: 480px) {
        #status {
            display: none;
        }

        #page-body {
            margin: 0 1em 1em;
        }

        #page-body h1 {
            margin-top: 0;
        }
    }
    .GreenImg {
        background-image: url('${request.contextPath}GreeButton.png');
        height:57px
    }
    .BlackImg {
        background-image: url('${request.contextPath}BlackButton.png');
        height:57px
    }
    <!--
        /*body { margin:0px; width: 1160px; }*/
    div#LayoutLYR { float:left; position:absolute; }
    div#NavigationBar1LYR { position:absolute; top:0px; left:100px; width:1000px; height:57px; z-index:1 }
    -->

    </style>
</head>

<body>



<h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; CPM Scheduling :  user ${session?.userId}</h1>
<h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Quick Kanban v1.0 by N.Michael</h2>


<div id="status" role="complementary">
    <h1>CPM Scheduling</h1>
    <g:link controller="userLogin" action="landingPage"><strong>Home Page</strong></g:link><br><br>
    <tag:isAdmin userId="${session.userId}">
        <a href="../documents/AdministratorsManual.pdf" target="_blank">Administrators Manual</a> <br>
    </tag:isAdmin>
    <tag:isInitialized>
        <br>
        <g:link controller="userLogin" action="license">License</g:link><br>
        <a href="../documents/UsersManual.pdf" target="_blank">Users Manual</a><br>
        <a href="../documents/Scrum_Users_Manual.pdf" target="_blank">Scrum Users Manual</a><br>
        <a href="../documents/CPM_Scheduling_Manual.pdf" target="_blank">CPM Scheduling Manual</a><br> <br>
        <a href="../documents/CPM_ScheduleSpreadsheet.xls" target="_blank">CPM Schedule Planning Spreadsheet</a><br>
        <a href="../documents/CPM_ScheduleSpreadsheet_example.xls" target="_blank">CPM Schedule Planning Spreadsheet Example</a><br>
        <a href="https://sourceforge.net/projects/quickkanban/" target="_blank">Download latest version</a>
        <a href="userLogin/logout">Logout</a><br>
    </tag:isInitialized>
</div>

<div id="page-body" role="main">
    <div id="controller-list" role="navigation">

        <ul>
<tag:isAdmin userId="${session.userId}">
            %{--<tag:showCPM>--}%
                <li class="controller"><h2>CPM Scheduling Functions</h2></li>
                <li class="controller"><g:link controller="userStory"  >1)&nbsp;Add a User Story</g:link></li>
                <li class="controller"><g:link controller="userStorySubtask" >2)&nbsp;Add all SubTasks</g:link></li>
                <li class="controller"><g:link controller="userStorySubtask" action="durationCalculator" >3)&nbsp;Calculate SubTask duration</g:link></li>
                <li class="controller"><g:link controller="schedule" action="linkSubTasks" >4)&nbsp;Add and Link SubTasks to Schedule</g:link></li>
                <li class="controller"><g:link controller="CPMnodes" action="linkNode">5)&nbsp;Add and Link CPM Nodes in Network</g:link></li>
                <li class="controller"><g:link controller="CPMnodes" action="listNodes" >6)&nbsp;List CPM Nodes</g:link></li>
                <li class="controller"><g:link controller="CPMnodes" action="listCriticalPath" >7)&nbsp;List Critical Path Nodes</g:link></li>
                <li class="controller"><g:link controller="schedule" action="setTimeTable" >8) List Time table</g:link></li>
                <li class="controller"><g:link controller="schedule" action="ganttChart">9) Gantt Chart</g:link></li>
                <li class="controller"><g:link controller="CPMnodes" action="setNodeStartAndFinishTimes">Recalculate Node start and finish times</g:link></li>
                <li class="controller"><g:link controller="schedule" >&nbsp;&nbsp;Manage Schedule</g:link></li>
                <li class="controller"><g:link controller="CPMnodes" >&nbsp;&nbsp;Manage CPM Nodes</g:link></li>
    <li class="controller"><a href="../documents/CPM_Sample_UserStorySubTasks.csv">Download CPM_Sample_UserStorySubTasks.csv</a></li>
    <li class="controller"><a href="../documents/CPM_Sample_UserStories.csv">Download CPM_Sample_UserStories.csv</a></li>
            %{--<li class="controller"><g:link controller="CPMnodes" action="setNodeStartAndFinishTimes">Set Node start and finish times</g:link></li>--}%
            %{--<li class="controller"><g:link controller="CPMnodes" >List and Manage CPM Nodes</g:link></li>--}%

            %{--</tag:showCPM>--}%
</tag:isAdmin>

            <tag:isAuthenticatedAndNotAdmin userId="${session.userId}">
                <h2>User Functions:</h2>
                <li class="controller"><a href="userStory/index">1)&nbsp;List and Manage User Stories</a></li>
                <li class="controller"><a href="userStorySubtask/index">2)&nbsp;List and Manage SubTasks</a></li>
                <li class="controller"><h2>Reporting Functions</h2></li>
                <li class="controller"><a href="userLogin/kanbanBoardHighLevel">1)&nbsp;High Level Kanban Board</a></li>
                <li class="controller"><a href="userLogin/kanbanBoardLowLevel">2)&nbsp;Low Level Kanban Board</a></li>
                <li class="controller"><a href="userLogin/highLevelReport">3)&nbsp;High Level Printable Report</a></li>
                <li class="controller"><a href="userLogin/lowLevelReport">4)&nbsp;Low Level Printable Report</a></li>
                <li class="controller"><a href="../documents/CPM_Sample_UserStorySubTasks.csv">Download CPM_Sample_UserStorySubTasks.csv</a></li>
                <li class="controller"><a href="../documents/CPM_Sample_UserStories.csv">Download CPM_Sample_UserStories.csv</a></li>
            </tag:isAuthenticatedAndNotAdmin>

            <li class="controller"><h2>Other Functions</h2></li>
            <tag:isAuthenticated userId="${session.userId}">
                <li class="controller"><a href="userLogin/logout">Logout</a></li>
            </tag:isAuthenticated>

            <tag:isNotAuthenticated userId="${session.userId}">
                <li class="controller"><g:link controller="userLogin" action="logon">User Logon</g:link></li>
            </tag:isNotAuthenticated>

        </ul>
    </div>
</div>
</body>
</html>
