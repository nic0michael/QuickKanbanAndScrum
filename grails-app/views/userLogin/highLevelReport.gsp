<%--
  Created by IntelliJ IDEA.
  User: NMichael
  Date: 2014/03/16
  Time: 2:42 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="quickkanban.BasicTagLib" %>
<LINK href="../css/datatable.css" rel="stylesheet" type="text/css">

<html>
<head>
  <title></title>
</head>
<body>

<h1>&nbsp;&nbsp;&nbsp; High level Report &nbsp;&nbsp;${session?.userId} &nbsp;&nbsp; ${new java.util.Date()}</h1>
<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Quick Kanban v1.0 by N.Michael</h5>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <g:link action="landingPage">Home Page</g:link>
<br><br>
 <table width="99%">
     <tr>
         <td></td>
         <td>
             <tag:getHighLevelReportTags/>
         </td>
         <td></td>
     </tr>
 </table>
</body>
</html>