<%--
  Created by IntelliJ IDEA.
  User: NMichael
  Date: 2014/03/20
  Time: 10:09 PM
  To change this template use File | Settings | File Templates.
--%>


<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="quickkanban.BasicTagLib" %>
<html>
<head>
    <title>Read me first</title>
    <meta name="layout" content="main"/>
</head>
<body>
<h2>&nbsp;Read me first</h2>
<br><br>
&nbsp;<g:link action="landingPage">Home Page</g:link>
<br><br><b>&nbsp;1) The first thing you need to do is create one or more administrator users</b>
<br><br>
<br><b>&nbsp;2) Then you need to create a user :</b>
<br>&nbsp;userId : klidi
<br>&nbsp;password : P@55w0rd
<br>&nbsp;<b>and disable this user </b>
<br>&nbsp;This will disable the default user
<br> <br>
<b>&nbsp;3) Then you need to add the following UserStorySubtaskStates :</b> <br>
<table>
    <tr>
        <td>&nbsp;</td>
        <td align="right">State Id</td><td>Description</td>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td>&nbsp;</td>
        <td align="right">0</td><td>UNASIGNED</td>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td>&nbsp;</td>
        <td align="right">1</td><td>TO DO</td>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td>&nbsp;</td>
        <td>2</td><td>IN PROGRESS</td>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td>&nbsp;</td>
        <td align="right">3</td><td>CODE REVIEW</td>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td>&nbsp;</td>
        <td align="right">4</td><td>TESTING</td>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td>&nbsp;</td>
        <td align="right">5</td><td>READY</td>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td>&nbsp;</td>
        <td>6</td><td>RELEASED</td>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td>&nbsp;</td>
        <td align="right">7</td><td>REJECTED</td>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td>&nbsp;</td>
        <td align="right">8</td><td>ARCHIVED</td>
        <td>&nbsp;</td>
    </tr>
</table>

</body>
</html>