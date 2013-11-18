<%@ page import="org.commonsemantics.grails.users.model.User" %>
<%@ page import="org.commonsemantics.grails.users.utils.UserUtils" %>

<h2>Configurations (External configuration)</h2>

<h3>Mandatory Fields </h3>
&nbsp;org.commonsemantics.grails.users.model.fields.mandatory = ${UserUtils.getMandatoryFields(grailsApplication)}<br/><br/>

<h3>Flexible Fields </h3>
&nbsp;${User.optional}<br/><br/>

<%-- Alternative A --%>
<table class="tabletest">
	<tr align="left">
		<th>Field</th><th>Value</th><th>Configuration entry</th>
	</tr>
	<g:each in="${User.optional}" var="option">
		<tr>	
			<td>${option}</td>
			<td>${grailsApplication.config.org.commonsemantics.grails.users.model.field[option]}</td>
			<td> org.commonsemantics.grails.users.model.field.${option}</td>
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