<%--
  Created by IntelliJ IDEA.
  User: NMichael
  Date: 2014/05/03
  Time: 9:50 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="za.co.quickkanban.CPMnodes" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Export UserStory Subtasks</title>
    <r:require module="export"/>
    <export:resource />
</head>
<body>
<div class="paginateButtons">
    <export:formats formats="['csv', 'excel', 'ods', 'pdf', 'rtf', 'xml']" />
</div>
<h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Export UserStory Subtasks  &nbsp;&nbsp;${session?.userId} &nbsp;&nbsp; ${new java.util.Date()}</h1>
<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Quick Kanban v1.0 by N.Michael</h5>
<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <g:link controller="userLogin" action="landingPage">Home Page</g:link>
<br>
<br>

<table border="1">
    <tr>
        <td><b>&nbsp;USERSTORY_SUBTASK_ID&nbsp;</b></td>
        <td><b>&nbsp;DESCRIPTION&nbsp;</b></td>
        <td><b>&nbsp;USERSTORY&nbsp;</b></td>
        <td><b>&nbsp;USER_ASSIGNED TO&nbsp;</b></td>
        <td><b>&nbsp;USER_PRIORITY&nbsp;</b></td>
        <td><b>&nbsp;COMMENT&nbsp;</b></td>
        <td><b>&nbsp;IMPEDIMENTS&nbsp;</b></td>
        <td><b>&nbsp;STATE&nbsp;</b></td>
        <td><b>&nbsp;DURATION&nbsp;</b></td>
        %{--<td><b>&nbsp;DATE_STATE_CHANGED&nbsp;</b></td>--}%
        <td><b>&nbsp;MILESTONE_DATE&nbsp;</b></td>
        <td><b>&nbsp;PERCENTAGE_COMPLETION&nbsp;</b></td>
    </tr>

    <g:each var="it" in="${userStorySubtasks}">
        <tr>
            <td>${it?.userStorySubtaskId}</td>
            <td>${it?.description}</td>
            <td>${it?.userStory}</td>
            <td>${it?.userAssignedTo}</td>
            <td>${it?.userPriority}</td>
            <td>${it?.comment}</td>
            <td>${it?.impediments}</td>
            <td>${it?.state}</td>
            <td>${it?.duration}</td>
            %{--<td>${it?.dateStateChanged}</td>--}%
            <td>${it?.milestoneDate}</td>
            <td>${it.percentageCompletion}</td>
        </tr>
    </g:each>
</table>

</body>
</html>