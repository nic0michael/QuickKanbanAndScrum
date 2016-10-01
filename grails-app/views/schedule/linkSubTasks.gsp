<%--
  Created by IntelliJ IDEA.
  User: NMichael
  Date: 2014/03/30
  Time: 6:05 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<head>
    <%@ page import="quickkanban.BasicTagLib" %>
    <meta name="layout" content="main"/>
    <title>Link SubTasks</title>
</head>
<body>

<h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Link and Add SubTasks to Schedule :  user ${session?.userId}</h1>
<h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Quick Kanban v1.0 by N.Michael</h2>
<br><br>


&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<g:link controller="userLogin" action="landingPage">Home Page</g:link>
<br><br>

<g:form controller="schedule" action="saveSubTasks" method="post" >
    <table>
        <tr>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td align="RIGHT">Node Id (number not zero)</td>
            <td>
                <input type="text" name="nodeId" id="nodeId" />
            </td>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        </tr>
        <tr>
            <td></td>
            <td align="RIGHT">SubTask</td>
            <td>
                <tag:subtasksDropDown name="subtask" id="subtask" />
            </td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td>
                <input type="submit" name="create" class="save" value="Create" id="create" />
            </td>
            <td></td>
        </tr>
    </table>
</g:form>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<tag:isAdmin userId="${session.userId}">
<g:link controller="CPMnodes" action="linkNode"><b>Add and Link CPM Nodes in Network</b></g:link><br><br>
</tag:isAdmin>
<tag:listSubTasks/>
</body>
</html>