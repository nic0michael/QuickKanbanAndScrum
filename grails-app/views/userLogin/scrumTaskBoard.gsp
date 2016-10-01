<%--
  Created by IntelliJ IDEA.
  User: nickm
  Date: 2014/03/10
  Time: 9:53 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="quickkanban.BasicTagLib" %>
<html>
<head>
    <title>&nbsp;Scrum Task Board</title>
    <meta name="layout" content="main"/>
</head>
<body>
<h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Scrum Task Board &nbsp;&nbsp;${session?.userId} &nbsp;&nbsp; ${new java.util.Date()}</h1>
<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Quick Kanban v1.0 by N.Michael</h5>
<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <g:link action="landingPage">Home Page</g:link>
<br>
<br>
<table width="99%">
    <tr>
        <td style="border: 1px solid black;">STORIES</td>
        <td style="border: 1px solid black;">TO DO</td>
        <td style="border: 1px solid black;">IN PROGRESS</td>
        <td style="border: 1px solid black;">TESTING</td>
        <td style="border: 1px solid black;">SPRINT END</td>
    </tr>
    <tr>
        <td style="border: 1px solid black;"><tag:getScrumUserStoryTags stateId="0" /></td> <!--STORIES -->
        <td style="border: 1px solid black;"><tag:getKanbanBoardLowLevelTags stateId="1" /></td> <!--TO DO -->
        <td style="border: 1px solid black;"><tag:getKanbanBoardLowLevelDualTags stateId="2" stateId2="3" /></td> <!--IN PROGRESS -->
        <td style="border: 1px solid black;"><tag:getKanbanBoardLowLevelTags stateId="4" /></td>  <!--TESTING -->
        <td style="border: 1px solid black;"><tag:getKanbanBoardLowLevelTags stateId="5" /></td>   <!--SPRINT END -->
    </tr>
</table>

</body>
</html>