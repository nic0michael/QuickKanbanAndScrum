<%--
  Created by IntelliJ IDEA.
  User: NMichael
  Date: 2014/05/05
  Time: 6:56 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="quickkanban.BasicTagLib" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Upload UserStory CSV File</title>
</head>
<body>

<h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Upload CSV file and Import UserStories  &nbsp;&nbsp;${session?.userId} &nbsp;&nbsp; ${new java.util.Date()}</h1>
<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Quick Kanban v1.0 by N.Michael</h5>
<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <g:link controller="userLogin" action="landingPage">Home Page</g:link>
<br>
<br>
<br>
<h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Please note you can only upload and import CSV Files</h3>
<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<tag:currentDateFormat /> Make sure your CSV file uses this date format or you will not be able to upload it
<br>
<br>

<g:uploadForm controller="setup" action="importUserStories">
    <table>
        <tr>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td colspan="2">${session?.errorMessage}</td>
        <tr>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td align=right>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Select CSV file to upload : </b></td>
            <td align=left><input type="file" name="csvfile" /></td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td><input type="submit" /></td>

        </tr>
    </table>
</g:uploadForm>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
</body>
</html>