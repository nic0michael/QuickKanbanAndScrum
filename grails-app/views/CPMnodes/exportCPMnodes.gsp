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
    <title>Export CPM Nodes</title>
    <r:require module="export"/>
    <export:resource />
</head>
<body>
<div class="paginateButtons">
    <export:formats formats="['csv', 'excel', 'ods', 'pdf', 'rtf', 'xml']" />
</div>
<h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Export CPM Nodes  &nbsp;&nbsp;${session?.userId} &nbsp;&nbsp; ${new java.util.Date()}</h1>
<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Quick Kanban v1.0 by N.Michael</h5>
<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <g:link controller="userLogin" action="landingPage">Home Page</g:link>
<br>
<br>

<table border="1">
    <tr>
        <td><b>&nbsp;NODE ID&nbsp;</b></td>
        <td><b>&nbsp;NODE NAME &nbsp;</b></td>
        <td><b>&nbsp;NEXT NODE &nbsp;</b></td>
        <td><b>&nbsp;DURATION&nbsp;</b></td>
        <td><b>&nbsp;EARLIEST START&nbsp;</b></td>
        <td><b>&nbsp;EARLIEST FINISH&nbsp;</b></td>
        <td><b>&nbsp;LATEST START&nbsp;</b></td>
        <td><b>&nbsp;LATEST FINISH&nbsp;</b></td>
        <td><b>&nbsp;SLACK&nbsp;</b></td>
    </tr>

    <g:each var="it" in="${nodes}">
        <tr>
            <td>${it?.nodeId}</td>
            <td>${it?.nodeName}</td>
            <td>${it?.nextNodeName}</td>
            <td>${it?.duration}</td>
            <td>${it?.earliestStart}</td>
            <td>${it?.earliestFinish}</td>
            <td>${it?.latestStart}</td>
            <td>${it?.latestFinish}</td>
            <td>${it?.slack}</td>
        </tr>
    </g:each>
</table>

</body>
</html>