<%--
  Created by IntelliJ IDEA.
  User: NMichael
  Date: 2014/03/29
  Time: 8:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%
    List<Schedule> scheduleItems = Schedule.findAll();

    Setup setup =Setup.findBySetupId("MASTER_RECORD")
    UserStory story = setup?.theDefaultUserStory
    Date projectStartDate = story?.startDate
%>
<html>
<head>
    <%@ page import="za.co.quickkanban.UserStory; za.co.quickkanban.Setup; za.co.quickkanban.Schedule; quickkanban.BasicTagLib" %>
    <meta name="layout" content="main"/>
    <title>Project Time Table</title>

</head>
<body>
<h1>&nbsp;&nbsp;&nbsp;&nbsp; Project Time Table &nbsp;&nbsp;${session?.userId} &nbsp;&nbsp; ${new java.util.Date()}</h1>
<br>
<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Quick Kanban v1.0 by N.Michael</h5> <br>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <g:link controller="userLogin" action="landingPage">Home Page</g:link>
<br>
      <h5>
      <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; User Story : ${story?.userStoryId}
      <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Description : ${story?.description}
      <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Project Start Date : ${projectStartDate}
      </h5>

<br><br>
<table>
<tr>
    <td>NODE ID</td>
    <td>SUBTASK ID</td>
    <td>DURATION</td>
    <td>EARLIEST START DATE</td>
    <td>LATEST END DATE</td>
</tr>
<g:each var="sched" in="${scheduleItems}">

        <tr>
            <td>${sched?.nodeId}</td>
            <td>${sched?.subtask?.userStorySubtaskId}</td>
<%
    if(sched.criticalPath){
%>
            <td><b>[${sched?.duration}]</b></td>
<%
    } else {
%>
            <td>${sched?.duration}</td>
<%
    }
%>
            %{--<td>${sched?.startDate?.format('dd/MMM/yyyy')}</td>--}%
            <td>${sched?.startDate?.format(setup?.dateFormat)}</td>
            %{--<td>${sched?.endDate?.format('yyyy-MM-dd')} ${setup?.dateFormat}</td>--}%
            <td>${sched?.endDate?.format(setup?.dateFormat)}</td>
        </tr>

</g:each>
</table>
</body>
</html>