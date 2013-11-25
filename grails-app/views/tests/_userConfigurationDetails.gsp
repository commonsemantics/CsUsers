<%@ page import="org.commonsemantics.grails.agents.model.Person" %>
<%@ page import="org.commonsemantics.grails.agents.utils.AgentsUtils" %>
<%@ page import="org.commonsemantics.grails.users.utils.UsersUtils" %>

<h2>Configurations (External configuration)</h2>

<h3>Mandatory Fields </h3>
<table class="tabletest">
	<tr align="left">
		<td>org.commonsemantics.grails.persons.model.fields.mandatory = ${AgentsUtils.getPersonDynamicMandatoryFields(grailsApplication)}</td>
	</tr>
	<tr align="left">
		<td>org.commonsemantics.grails.users.model.fields.mandatory = ${UsersUtils.getUserDynamicMandatoryFields(grailsApplication)}</td>
	</tr>
</table>

<h3>All Resulting Mandatory Fields </h3>
<table class="tabletest">
	<tr align="left">
		<td width="80px">Person:</td><td>${AgentsUtils.getPersonDynamicMandatoryFields(grailsApplication)}</td>
	</tr>
	<tr align="left">
		<td>User:</td><td>${UsersUtils.getUserDynamicMandatoryFields(grailsApplication)}</td>
	</tr>
</table>

<h3>Flexible Fields </h3>
<table class="tabletest">
	<tr align="left">
		<g:if test="${AgentsUtils.isPersonStaticPropertyExisting('optional')!=null}">
			<td>${Person.optional}</td>
		</g:if>
		<g:else>
			<td>No optional person's fields defined</td>
		</g:else>
	</tr>
	<tr align="left">
		<g:if test="${UsersUtils.isUserStaticPropertyExisting('optional')!=null}">
			<td>${User.optional}</td>
		</g:if>
		<g:else>
			<td>No optional user's fields defined</td>
		</g:else>
	</tr>
</table>

<%-- Alternative A --%>
<table class="tabletest">
	<tr align="left">
		<th>Field</th><th>Value</th><th>Configuration entry</th>
	</tr>
	<g:each in="${Person.optional}" var="option">
		<tr>	
			<td>${option}</td>
			<td>${grailsApplication.config.org.commonsemantics.grails.persons.model.field[option]}</td>
			<td> org.commonsemantics.grails.persons.model.field.${option}=${grailsApplication.config.org.commonsemantics.grails.persons.model.field[option]}</td>
		</tr>
	</g:each>
</table>


<%-- Alternative B --%>
<%-- 
<table cellpadding=5>
	<tr align="left">
		<th>Field</th><th>Value</th><th>Configuration entry</th>
	</tr>
	<% User.optional.each { option -> %>
         <tr>
         	<td><%="${option}" %></td>
         	<td><%="${grailsApplication.config.org.commonsemantics.grails.users.model.field[option]}" %></td>
         	<td> org.commonsemantics.grails.users.model.field.${option}</td>
         </tr>
    <%}%>
</table>
--%>