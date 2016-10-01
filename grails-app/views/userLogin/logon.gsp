<%--
  Created by IntelliJ IDEA.
  User: nickm
  Date: 2014/03/08
  Time: 5:05 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="quickkanban.BasicTagLib" %>
<html>
<head>
  <title>Logon</title>
    <meta name="layout" content="main"/>
</head>
<body>
<h1>${session?.userId} User Logon</h1>
<g:form name="myForm" url="[action:'user',controller:'userLogin']">
<table>
    <tr>
        <td colspan="2">${session?.errorMessage}</td><td>
    </td>
    <tr>
        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        <td align="right">Name : </td><td>
        <!-- input name="loginname" type="text" value="$ {endUserInstance?.userName}" / -->
        <g:textField name="loginname" />
    </td>
    </tr>
    <tr>
        <td></td>
        <td align="right">Password : </td><td>
        <!-- input name="password" type="password" value="$ {endUserInstance?.password}" / -->
        <g:field type="password" name="password" />
    </td>
    </tr>
    <tr>
        <td></td>
        <td></td>
        <td><g:submitButton name="login" class="save" value="Login" /></td>

    </tr>
    <tr>
        <td></td>
        <td></td>
        <td>
            <br><br><br><br>
            <tag:isAuthenticated userId="${session.userId}">
                <h2>You are already Authenticated </h2>
                <g:link action="landingPage">Back to Landing page</g:link>
            </tag:isAuthenticated>
        </td>

    </tr>
</table><br>
<!-- input type="submit" value="Submit" name="B1" -->

</g:form>




<br><br><br><br><br><br><br><br><br><br><br>
</body>
</html>