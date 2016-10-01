<%--
  Created by IntelliJ IDEA.
  User: nickm
  Date: 2014/03/10
  Time: 9:00 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="quickkanban.BasicTagLib" %>
<html>
<head>
  <title>Subtask Set State and assign User</title>
    <meta name="layout" content="main"/>
</head>
<body>
<%
   String errorMessage =session?.errorMessage
%>


<h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Assign State to Subtask     : ${session?.userId}</h1>
<h2>Quick Kanban v1.0 by N.Michael</h2>
<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<g:link controller="userLogin" action="landingPage">Home Page</g:link>
<br> <br>
<ul>
<g:form name="myForm" url="[action:'subtaskAssignSetState',controller:'userStorySubtask']">
    <table>
 <%
      if(errorMessage !=null && errorMessage.length()>2 ) {
 %>

        <tr>
            <td>&nbsp;</td>
            <td>Error Message</td>
            <td>
                ${session.errorMessage}
            </td>
            <td>&nbsp;</td>
        </tr>

<%
      }
%>
        <tr>
            <td>&nbsp;</td>
            <td>Select Subtask</td>
            <td>
                <tag:subtasksDropDown id="userStorySubtaskId" name="userStorySubtaskId"/>
            </td>
            <td>&nbsp;</td>
        </tr>

        <tr>
            <td>&nbsp;</td>
            <td>Set State</td>
            <td>
                <tag:statesDropDown id="stateId" name="stateId"/>
            </td>
            <td>&nbsp;</td>
        </tr>

        %{--<tr>--}%
            %{--<td>&nbsp;</td>--}%
            %{--<td>Comments</td>--}%
            %{--<td>--}%
                %{--<input type="text" id="comment" name="comment" maxlength="250" />--}%
            %{--</td>--}%
            %{--<td>&nbsp;</td>--}%
        %{--</tr>--}%

        %{--<tr>--}%
            %{--<td>&nbsp;</td>--}%
            %{--<td>Impediments</td>--}%
            %{--<td>--}%
                %{--<input type="text" id="impediments" name="impediments" maxlength="250" />--}%
            %{--</td>--}%
            %{--<td>&nbsp;</td>--}%
        %{--</tr>--}%

        %{--<tr>--}%
            %{--<td>&nbsp;</td>--}%
            %{--<td>Assign user</td>--}%
            %{--<td>--}%
                %{--<input type="radio" value="Yes" name="assignUser">Yes--}%
                %{--<input type="radio" value="No" name="assignUser">No--}%
            %{--</td>--}%
            %{--<td>&nbsp;</td>--}%
        %{--</tr>--}%

        %{--<tr>--}%
            %{--<td>&nbsp;</td>--}%
            %{--<td>User Assigned</td>--}%
            %{--<td>--}%
                %{--<tag:selectUsers id="userId" name="userId"/>--}%
            %{--</td>--}%
            %{--<td>&nbsp;</td>--}%
        %{--</tr>--}%
        <tr>
            <td>&nbsp;</td>
            <td>Assign User Story</td>
            <td>
                <input type="radio" value="Yes" name="assignUserStory">Yes
                <input type="radio" value="No" name="assignUserStory">No
            </td>
            <td>&nbsp;</td>
        </tr>

        <tr>
            <td>&nbsp;</td>
            <td></td>
            <td><g:submitButton name="submit" class="save" value="Set" /></td>
            <td>&nbsp;</td>
        </tr>
    </table>
</g:form>
</body>
</html>