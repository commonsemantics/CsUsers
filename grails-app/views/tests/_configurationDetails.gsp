<%@ page import="org.commonsemantics.grails.users.model.User" %>

<h2>Configurations (External configuration)</h2>
<h3>Optional Fields </h3>
&nbsp;${User.optional}<br/><br/>

<%-- Alternative A --%>
<table cellpadding=5>
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