<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
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
    <title>Quick Kanban</title>
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
    </style>
</head>

<body>
<a href="#page-body" class="skip"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

<h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Home Page :  user ${session?.userId}</h1>

<h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Quick Kanban v1.0 by N.Michael</h2>

<div id="status" role="complementary">
    <h1>Home Page</h1>

    <tag:isInitialized>
        <tag:isAuthenticated userId="${session.userId}">
            <table width="100">

                <tr>
                    <td>
                        <table WIDTH=70 HEIGHT=20 class="buttonHeading"
                               onMouseOver="this.className = 'buttonMouseOverHeading'"
                               onMouseOut="this.className = 'buttonHeading'" border="0">
                            <tr>
                                <td align="center">
                                    <strong>
                                        <g:link controller="userLogin" action="Kanban_Functions">Kanban Function</g:link>
                                    </strong>
                                </td>
                            </tr>
                        </table>

                        <table WIDTH=70 HEIGHT=20 class="buttonHeading"
                               onMouseOver="this.className = 'buttonMouseOverHeading'"
                               onMouseOut="this.className = 'buttonHeading'" border="0">
                            <tr>
                                <td align="center">
                                    <strong>
                                        <g:link controller="userLogin" action="Scrum_Scheduling">Scrum Scheduling</g:link>
                                    </strong>
                                </td>
                            </tr>
                        </table>

                        %{--<table WIDTH=70 HEIGHT=20 class="buttonHeading"--}%
                               %{--onMouseOver="this.className = 'buttonMouseOverHeading'"--}%
                               %{--onMouseOut="this.className = 'buttonHeading'" border="0">--}%
                            %{--<tr>--}%
                                %{--<td align="center">--}%
                                    %{--<strong>--}%
                                        %{--<g:link controller="userLogin" action="CPM_Scheduling">CPM Scheduling</g:link>--}%
                                    %{--</strong>--}%
                                %{--</td>--}%
                            %{--</tr>--}%
                        %{--</table>--}%
                        <table WIDTH=70 HEIGHT=20 class="buttonHeading"
                               onMouseOver="this.className = 'buttonMouseOverHeading'"
                               onMouseOut="this.className = 'buttonHeading'" border="0">
                            <tr>
                                <td align="center">
                                    <strong>
                                        <g:link controller="userLogin" action="Management_Functions">Management Functions</g:link>
                                    </strong>
                                </td>
                            </tr>
                        </table>
                        <table WIDTH=70 HEIGHT=20 class="buttonHeading"
                               onMouseOver="this.className = 'buttonMouseOverHeading'"
                               onMouseOut="this.className = 'buttonHeading'" border="0">
                            <tr>
                                <td align="center">
                                    <strong>
                                        <g:link controller="userLogin" action="Administrator_Functions">Administrator Functions (Controllers)</g:link>
                                    </strong>
                                    </strong>
                                </td>
                            </tr>
                        </table>
                        <table WIDTH=70 HEIGHT=20 class="buttonHeading"
                               onMouseOver="this.className = 'buttonMouseOverHeading'"
                               onMouseOut="this.className = 'buttonHeading'" border="0">
                            <tr>
                                <td align="center">
                                    <strong>
                                        <g:link controller="userLogin" action="Export_Functions">Export Functions</g:link>
                                    </strong>
                                </td>
                            </tr>
                        </table>
                        <table WIDTH=70 HEIGHT=20 class="buttonHeading"
                               onMouseOver="this.className = 'buttonMouseOverHeading'"
                               onMouseOut="this.className = 'buttonHeading'" border="0">
                            <tr>
                                <td align="center">
                                    <strong>
                                        <g:link controller="userLogin" action="Import_Functions">Import CSV File Functions</g:link>
                                    </strong>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </tag:isAuthenticated>
    </tag:isInitialized>

    <tag:isAdmin userId="${session.userId}">
        <tag:notInitialized>
            <g:link controller="userStorySubtaskState" action="prePopulte"><b>Populate required values</b></g:link> <br>
        </tag:notInitialized>
    </tag:isAdmin>
    <br>
    <g:link action="logout">logout</g:link><br>
</div>

<div id="page-body" role="main">
    <h1>Welcome to Quick Kanban</h1>

    <p>
        This is the Online Web based Project Management tool that can
        be used for Agile Project Management with a built in database.<br>
        Today we have moved away from the traditional (Waterfall) CPM or PERT
        project management tools to ones based on <b>Agile Methodologies</b> <br>
        The following agile methodologies are supported by this program:</p>
    <ol>
        <li><b>Kanban</b></li>
        <li><b>Scrum</b></li>
    </ol>

    <h2>Kanban</h2>
    According to wikipedia the name 'Kanban' originated from Japanese,
    and translates roughly as "signboard" or "billboard". <br>
        &nbsp;Kanban was developed by Taiichi Ohno, at Toyota, as a system to
        improve and maintain a high level of production. Kanban is one method
        to achieve Just In Time and giving Toyota an advantage over Ford.<br>
        You cannot improve what you cannot see. Knowledge work needs a way to
        show progress. Kanban boards are one of the ways to display progress.<br>
        To implement the Kanban method of project management Quick Kanban
        provides you with Electronic Kanban Boards<br>
        The Kanban Board is useful for Software/Telecommunications Support
        teams for managing their tasks by displaying their progress.<br>
        Quick Kanban provides you with a High Level Kanban board for looking
        at User Stories and a low level Kanban board for looking at progress
        of the Sub-tasks of a User Story.</p>
    <p>Kanban is now used for Marketing Teams , HR Teams , in Vehicle
    Manufacture , in Vehicle repair Shops , Software support, Production
    Support (for tracking the progress of problems being fixed )

    <h2>Scrum</h2>
    According to wikipedia : Scrum was first defined as "a flexible,
    holistic product development strategy where a development team works
    as a unit to reach a common goal" as opposed to a "traditional,
    sequential approach" in 1986 by Hirotaka Takeuchi and Ikujiro Nonaka <br>
    <br>
    Scrum is a highly iterative and incremental agile software development
    methodology for managing product development, and software development
    teams. <br>
    The advantage this Methodology has over the traditional CPM
    (waterfall) project methodology is that the tasks normally assigned to
    your team are <br>
    broken into smaller subtasks . Withe Scrum you deliver to your
    customer small increments of the project every week or two weeks <br>
    This period is referred to as a sprint<br>
    <br>
    A key principle of Scrum is its recognition that during a project the
    customers can change their minds about what they want and need .<br>
    The disadvantage of CPM project management is that you deliver
    completed projects after a few months or even years then you need
    weeks or months to <br>
    deliver the changes the customer requested.<br>
    The benefit with Scrum is that&nbsp; as you deliver small increments
    the customer gets to learn earlier what you are delivering and makes
    requests to have changes made <br>
    these will have a much smaller impact on the project delivery and with
    time the developers learn the expectations of the customer and the <br>
    customer learns what to expect from the developer this greatly reduces
    wastage.<br>
    <br>
    With CPM project management the team is always guessing how long it
    will take to develop something and usually the project plan is too
    optimistic<br>
    With Scrum the developers learn to better judge how long it will take
    to deliver sub-tasks and you forecast your sub-tasks delivered in the
    sprints more accurately</p>

    <br><br>

    </p>

    <div id="controller-list" role="navigation">

        <tag:isAdmin userId="${session.userId}">
            <tag:isInitialized>
                <a href="../documents/AdministratorsManual.pdf" target="_blank">Administrators Manual</a> <br>
            </tag:isInitialized>
        </tag:isAdmin>

        <tag:isInitialized>
            <a href="../documents/UsersManual.pdf" target="_blank">Users Manual</a><br>
            <a href="../documents/Scrum_Users_Manual.pdf" target="_blank">Scrum Users Manual</a><br>
            %{--<a href="../documents/CPM_Scheduling_Manual.pdf" target="_blank">CPM Scheduling Manual</a><br><br>--}%
            %{--<a href="../documents/CPM_ScheduleSpreadsheet.xls" target="_blank">CPM Schedule Planning Spreadsheet</a><br>--}%
            %{--<a href="../documents/CPM_ScheduleSpreadsheet_example.xls"--}%
               %{--target="_blank">CPM Schedule Planning Spreadsheet Example</a><br>--}%
            <g:link controller="userLogin" action="license">License</g:link><br>
            <a href="https://sourceforge.net/projects/quickkanban/" target="_blank">Download latest version</a> <br>
        </tag:isInitialized><br>
        <ul>
            <li class="controller"><h2>Other Functions</h2></li>
            <tag:isAuthenticated userId="${session.userId}">
                <li class="controller"><g:link action="logout">logout</g:link></li>
            </tag:isAuthenticated>
            <tag:isNotAuthenticated userId="${session.userId}">
                <li class="controller"><g:link controller="userLogin" action="logon">User Logon</g:link></li>
            </tag:isNotAuthenticated>

        </ul>
    </div>
</div>
</body>
</html>
