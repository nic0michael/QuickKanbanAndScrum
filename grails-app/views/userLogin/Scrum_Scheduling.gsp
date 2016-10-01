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
    <title>Scrum Scheduling</title>
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



<h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Scrum Scheduling :  user ${session?.userId}</h1>
<h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Quick Kanban v1.0 by N.Michael</h2>


<div id="status" role="complementary">
    <h1>Scrum Scheduling</h1>
    <g:link controller="userLogin" action="landingPage"><strong>Home Page</strong></g:link><br><br>
    <tag:isAdmin userId="${session.userId}">
        <a href="../documents/AdministratorsManual.pdf" target="_blank">Administrators Manual</a> <br>
    </tag:isAdmin>
    <tag:isInitialized>
        <br>
        <g:link controller="userLogin" action="license">License</g:link><br>
        <a href="../documents/UsersManual.pdf" target="_blank">Users Manual</a><br>
        <a href="../documents/Scrum_Users_Manual.pdf" target="_blank">Scrum Users Manual</a><br>
        %{--<a href="../documents/CPM_Scheduling_Manual.pdf" target="_blank">CPM Scheduling Manual</a><br> <br>--}%
        %{--<a href="../documents/CPM_ScheduleSpreadsheet.xls" target="_blank">CPM Schedule Planning Spreadsheet</a><br>--}%
        %{--<a href="../documents/CPM_ScheduleSpreadsheet_example.xls" target="_blank">CPM Schedule Planning Spreadsheet Example</a><br>--}%
        <a href="https://sourceforge.net/projects/quickkanban/" target="_blank">Download latest version</a>
        <a href="userLogin/logout">Logout</a><br>
    </tag:isInitialized>
</div>

<div id="page-body" role="main">
    <div id="controller-list" role="navigation">

        <ul>
            <tag:isInitialized>


                <tag:isAuthenticated userId="${session.userId}">
                    %{--<tag:showScrum>--}%
                        <li class="controller"><h2>Scrum Scheduling Functions</h2></li>
                        <li class="controller"><g:link controller="userStory"  >1)&nbsp;Add a User Story</g:link></li>
                        <li class="controller"><g:link controller="userStorySubtask" >2)&nbsp;Add all SubTasks</g:link></li>
                        <li class="controller"><g:link controller="userLogin" action="scrumTaskBoard">3)&nbsp;Scrum Task Board</g:link></li>
                    %{--</tag:showScrum>--}%
                </tag:isAuthenticated>
            </tag:isInitialized>

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
