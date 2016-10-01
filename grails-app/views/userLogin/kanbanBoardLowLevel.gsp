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
    <title>&nbsp;Highlevel Kanban Board</title>
    <meta name="layout" content="main"/>
</head>
<body>
<h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Low level Kanban Board &nbsp;&nbsp;${session?.userId} &nbsp;&nbsp; ${new java.util.Date()}</h1>
<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Quick Kanban v1.0 by N.Michael</h5>
<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <g:link action="landingPage">Home Page</g:link>
<br>
<br>
<table width="99%">
    <tr>
        <td style="border: 1px solid black;">BACKLOG</td>
        <td style="border: 1px solid black;">TO DO</td>
        <td style="border: 1px solid black;">IN PROGRESS</td>
        <td style="border: 1px solid black;">CODE REVIEW</td>
        <td style="border: 1px solid black;">TESTING</td>
        <td style="border: 1px solid black;">READY</td>
        <td style="border: 1px solid black;">RELEASED</td>
    </tr>
    <tr>
        <td style="border: 1px solid black;"><tag:getKanbanBoardLowLevelTags stateId="0" /></td>
        <td style="border: 1px solid black;"><tag:getKanbanBoardLowLevelTags stateId="1" /></td>
        <td style="border: 1px solid black;"><tag:getKanbanBoardLowLevelTags stateId="2" /></td>
        <td style="border: 1px solid black;"><tag:getKanbanBoardLowLevelTags stateId="3" /></td>
        <td style="border: 1px solid black;"><tag:getKanbanBoardLowLevelTags stateId="4" /></td>
        <td style="border: 1px solid black;"><tag:getKanbanBoardLowLevelTags stateId="5" /></td>
        <td style="border: 1px solid black;"><tag:getKanbanBoardLowLevelTags stateId="6" /></td>
    </tr>
</table>

</body>
</html>