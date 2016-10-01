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
    <title>&nbsp;Rejected User Stories</title>
    <meta name="layout" content="main"/>
</head>
<body>
<h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Archived User Stories</h1>
<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Quick Kanban v1.0 by N.Michael</h5>
<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<g:link action="landingPage">Home Page</g:link>
<br>
<br>
<table>
    %{--<tr>--}%
    %{--<td style="border: 1px solid black;">BACKLOG</td>--}%
    %{--<td style="border: 1px solid black;">TO DO</td>--}%
    %{--<td style="border: 1px solid black;">IN PROGRESS</td>--}%
    %{--<td style="border: 1px solid black;">CODE REVIEW</td>--}%
    %{--<td style="border: 1px solid black;">TESTING</td>--}%
    %{--<td style="border: 1px solid black;">READY</td>--}%
    %{--<td style="border: 1px solid black;">RELEASED</td>--}%
    %{--</tr>--}%
    <tr>
        <td></td>
        <td style="border: 1px solid black;"><tag:getHighLevelTags stateId="8" /></td>
        <td></td>
    </tr>
</table>

</body>
</html>