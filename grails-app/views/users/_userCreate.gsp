<%@ page import="org.commonsemantics.grails.users.utils.UserUtils" %>
<%-- 
By Dr. Paolo Ciccarese <paolo.ciccarese@gmail.com>

Parameters list
 1) user | instance of GroupCreateCommand
Stylesheet
 1) fieldError | background and font color in erroneous text fields
--%>
<div class="sectioncontainer">
	<table>		
		<tr>		
			<td><g:render plugin="cs-user" template="/users/uProfileFields" model="[command:'create']"/></td>
		</tr>
		<tr>		
			<td><g:render plugin="cs-user" template="/users/uAccountFields" model="[command:'create']"/></td>
		</tr>
	</table>
</div>