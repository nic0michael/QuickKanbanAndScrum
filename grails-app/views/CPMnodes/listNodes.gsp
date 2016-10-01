<%--
  Created by IntelliJ IDEA.
  User: NMichael
  Date: 2014/04/01
  Time: 8:04 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<head>
    <%@ page import="quickkanban.BasicTagLib" %>
    <meta name="layout" content="main"/>
    <title>List Nodes</title>
</head>
<body>

<h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; List Schedule Nodes |  user : ${session?.userId}</h1>
<h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Quick Kanban v1.0 by N.Michael</h2>
<br><br>


&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<g:link controller="userLogin" action="landingPage">Home Page</g:link>
<br><br>
<tag:listNodes/>
<br><br>   <h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Critical Path</h2>
<tag:listCriticalPathNodes/>
</body>
</html>