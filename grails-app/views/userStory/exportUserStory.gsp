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
    <title>Export UserStories</title>
    <r:require module="export"/>
    <export:resource />
</head>
<body>
<div class="paginateButtons">
    <export:formats formats="['csv', 'excel', 'ods', 'pdf', 'rtf', 'xml']" />
</div>
<h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Export UserStories  &nbsp;&nbsp;${session?.userId} &nbsp;&nbsp; ${new java.util.Date()}</h1>
<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Quick Kanban v1.0 by N.Michael</h5>
<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <g:link controller="userLogin" action="landingPage">Home Page</g:link>
<br>
<br>

<table border="1">
    <tr>
        <td><b>&nbsp;USERSTORYID&nbsp;</b></td>
        <td><b>&nbsp;PRIORITY&nbsp;</b></td>
        <td><b>&nbsp;DESCRIPTION&nbsp;</b></td>
        <td><b>&nbsp;STARTDATE&nbsp;</b></td>
        <td><b>&nbsp;COMPLETIONDATE&nbsp;</b></td>
        <td><b>&nbsp;USERREQUESTING&nbsp;</b></td>
        <td><b>&nbsp;STATE&nbsp;</b></td>
        <td><b>&nbsp;DATESTATECHANGED&nbsp;</b></td>
        <td><b>&nbsp;CARDTYPE&nbsp;</b></td>
    </tr>

    <g:each var="it" in="${userStoryPojos}">
        <tr>
            <td>${it?.userStoryId}</td>
            <td>${it?.priority}</td>
            <td>${it?.description}</td>
            <td>${it?.startDate}</td>
            <td>${it?.completionDate}</td>
            <td>${it?.userRequesting}</td>
            <td>${it?.state}</td>
            <td>${it?.dateStateChanged}</td>
            <td>${it?.cardType}</td>
        </tr>
    </g:each>
</table>

</body>
</html>