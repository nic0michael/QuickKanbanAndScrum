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
  <title>Link Node</title>
</head>
<body>

<h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Link Schedule Node :  user ${session?.userId}</h1>
<h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Quick Kanban v1.0 by N.Michael</h2>
<br><br>


&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<g:link controller="userLogin" action="landingPage">Home Page</g:link>
<br><br>

<g:form controller="CPMnodes" action="saveNode" method="post" >
    <table>
        <tr>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td>Node Id</td>
            <td>
                <tag:nodeDropDown name="nodeId" id="nodeId" />
            </td>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        </tr>
        %{--<tr>--}%
            %{--<td></td>--}%
            %{--<td align="RIGHT">Is Start an Immediate Predecessor</td>--}%
            %{--<td>--}%
                %{--<tag:yesNoDropDown name="startIsImmediatePredecessorYN" id="startIsImmediatePredecessorYN"  selectedLabel="No" selectedValue="No"/>--}%
            %{--</td>--}%
            %{--<td></td>--}%
        %{--</tr>--}%
        <tr>
            <td></td>
            <td>Next Node Id</td>
            <td>
                <tag:nodeDropDown name="nextNodeId" id="nextNodeId" />
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

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<g:link controller="CPMnodes" action="setNodeStartAndFinishTimes">Recalculate Node start and finish times</g:link>
<br>
%{--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<g:link controller="CPMnodes" action="setNodeStartAndFinishTimes"><b>Set Node start and finish times</b></g:link>--}%
<br>
<tag:listNodes/>
<br><br>   <h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Critical Path</h2>
<tag:listCriticalPathNodes/>
<tag:isAdmin userId="${session.userId}">
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<g:link controller="CPMnodes" action="deleteAllNodes" >DELETE ALL NODES (Start again)</g:link>
</tag:isAdmin>
</body>
</html>