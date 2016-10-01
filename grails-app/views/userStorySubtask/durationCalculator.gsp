<%--
  Created by IntelliJ IDEA.
  User: NMichael
  Date: 2014/03/30
  Time: 6:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%
    List<UserStorySubtask> tasks= UserStorySubtask.findAll([sort: "userStory", order: "asc"]);
%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<head>
    <%@ page import="za.co.quickkanban.UserStorySubtask; quickkanban.BasicTagLib" %>
    <meta name="layout" content="main"/>
    <title>Duration Calculator</title>
</head>
<body>

<h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Duration Calculator :  user ${session?.userId}</h1>
<h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Quick Kanban v1.0 by N.Michael</h2>
<br><br>


&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<g:link controller="userLogin" action="landingPage">Home Page</g:link>
<br><br>

<g:form controller="userStorySubtask" action="saveSubTask" method="post" >
    <table>
        <tr>
            <td></td>
            <td>Select SubTask</td>
            <td>
                <tag:subtasksDropDown name="subtask" id="subtask" />
            </td>
            <td></td>
        </tr>
        <tr>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td>Pessimistic Duration (days)</td>
            <td>
                <input type="text" name="pessimistic" id="pessimistic" />
            </td>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        </tr>
        <tr>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td>Optimistic Duration (days)</td>
            <td>
                <input type="text" name="optimistic" id="optimistic" />
            </td>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        </tr>
        <tr>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td>Most likely Duration (days)</td>
            <td>
                <input type="text" name="mostLikely" id="mostLikely" />
            </td>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td>
                <input type="submit" name="calculate" class="save" value="calculate" id="calculate" />
            </td>
            <td></td>
        </tr>
    </table>
</g:form>

<table>
    <tr>
        <td>&nbsp;&nbsp;</td>
        <td>SUBTASK ID</td>
        <td>DURATION</td>
        <td>&nbsp;&nbsp;</td>
    </tr>
<g:each var="task" in="${tasks}">
    <tr>
        <td>&nbsp;&nbsp;</td>
        <td>
            ${task?.userStorySubtaskId}
        </td>
        <td>
            ${task.duration}
        </td>
        <td>&nbsp;&nbsp;</td>
    </tr>
</g:each>
</table>

</body>
</html>