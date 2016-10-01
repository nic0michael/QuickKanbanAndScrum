<%--
  Created by IntelliJ IDEA.
  User: NMichael
  Date: 2014/03/30
  Time: 6:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%
    Setup setup =Setup.findBySetupId("MASTER_RECORD")

%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<head>
    <%@ page import="za.co.quickkanban.Setup; quickkanban.BasicTagLib" %>
    <meta name="layout" content="main"/>
    <title>Setup</title>
</head>
<body>

<h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Setup :  user ${session?.userId}</h1>
<h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Quick Kanban v1.0 by N.Michael</h2>
<br><br>


&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<g:link controller="userLogin" action="landingPage">Home Page</g:link>
<br><br>

<g:form controller="setup" action="saveSetup" method="post" >
    <table>
        <tr>
            <td></td>
            <td>Set the Default UserStory</td>
            <td>
                <tag:userStoryDropDown name="theDefaultUserStory" id="theDefaultUserStory" selectedValue="${setup?.theDefaultUserStory?.userStoryId}" selectedLabel="${setup?.theDefaultUserStory?.description}" />
            </td>
            <td></td>
        </tr>
        <tr>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td>Organization Name</td>
            <td>
                <g:textField  id="organizationName" name="organizationName" value="${setup?.organizationName}"/>
            </td>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        </tr>
        <tr>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td>Sponsor or Project Owner</td>
            <td>
                <g:textField  id="projectOwner" name="projectOwner" value="${setup?.projectOwner}"/>
            </td>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        </tr>
        <tr>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td>Manager Fullname</td>
            <td>
                <g:textField  id="managerFullName" name="managerFullName" value="${setup?.managerFullName}" />
            </td>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        </tr>
        <tr>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td>Project Managers Title</td>
            <td>
                <select  name="managerTitle"  id="managerTitle"  >
                    <option selected="${setup.managerTitle}">${setup?.managerTitle}</option>
                    <option value="Project Manager" >Project Manager</option>
                    <option value="Scrum Master" >Scrum Master</option>
                    <option value="Kanban Master" >Kanban Master</option>
                </select>

            </td>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        </tr>
        <tr>
            <td></td>
            <td>Project Type</td>
            <td>
                <select name="projectType" required="" id="projectType" >
                    <option value="Kanban Project" selected="selected" >Kanban Project</option>
                    <option value="Scrum Project" >Scrum Project</option>
                    <option value="CPM Scheduled Project" >CPM Scheduled Project</option>
                </select>
            <td></td>
        </tr>
        <td></td>
        <td>Date Format</td>
        <td>
        <select name="dateFormat" required="" id="dateFormat" >
            <option selected="${setup?.dateFormat}" >${setup?.dateFormat}</option>
            <option value="yyyy-MM-dd" >Y2K | yyyy-MM-dd</option>
            <option value="dd/MMM/yyyy" >British | dd/MMM/yyyy</option>
            <option value="MMM/dd/yyyy" selected="selected" >USA | MMM/dd/yyyy</option>
        </select>
    <td></td>
    </tr>
        <tr>
            <td></td>
            <td>Show CPM functions</td>
            <td>
                <g:if test="${setup?.showCPMfunctions}">
                    <input type="checkbox" name="showCPMfunctions" id="showCPMfunctions" checked="checked" />
                </g:if>
                <g:else>
                    <input type="checkbox" name="showCPMfunctions" id="showCPMfunctions" />
                </g:else>

            <td></td>
        </tr>

        <tr>
            <td></td>
            <td>Show Scrumfunctions</td>
            <td>
                <g:if test="${setup?.showScrumfunctions}">
                    <input type="checkbox" name="showScrumfunctions" id="showScrumfunctions" checked="checked" />
                </g:if>
                <g:else>
                    <input type="checkbox" name="showScrumfunctions" id="showScrumfunctions" />
                </g:else>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td>
                <input type="submit" name="create" class="save" value="Save" id="create" />
            </td>
            <td></td>
        </tr>
    </table>
</g:form>

</body>
</html>