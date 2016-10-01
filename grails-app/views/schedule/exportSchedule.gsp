<%--
  Created by IntelliJ IDEA.
  User: NMichael
  Date: 2014/05/03
  Time: 9:51 PM
  To change this template use File | Settings | File Templates.
--%>


<%@ page import="za.co.quickkanban.Schedule; za.co.quickkanban.Setup; za.co.quickkanban.UserStory" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
  <title>Export Schedule</title>
    <r:require module="export"/>
    <export:resource />
</head>
<body>
<div class="paginateButtons">
<export:formats formats="['csv', 'excel', 'ods', 'pdf', 'rtf', 'xml']" />
</div>
<h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Export Schedule  &nbsp;&nbsp;${session?.userId} &nbsp;&nbsp; ${new java.util.Date()}</h1>
<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Quick Kanban v1.0 by N.Michael</h5>
<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <g:link controller="userLogin" action="landingPage">Home Page</g:link>
<br>
<br>


<table border="1">
    <tr>
        <td>NODE_ID</td>
        <td>SUBTASK_ID</td>
        <td>DURATION</td>
        <td>EARLIEST_START_DATE</td>
        <td>LATEST_END_DATE</td>
        <td>EARLIEST_START</td>
        <td>CRITICAL_PATH</td>
    </tr>

    <g:each var="sched" in="${scheduleList}">
        <tr>
            <td>${sched?.nodeId}</td>
            <td>${sched?.userStorySubtaskId}</td>
            <td>${sched?.duration}</td>
            <td>${sched?.startDate}</td>
            <td>${sched?.endDate}</td>
            <td>${sched?.earliestStart}</td>
            <td>${sched?.criticalPath}</td>
        </tr>
    </g:each>
</table>

</body>
</html>