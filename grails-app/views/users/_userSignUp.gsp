<%@ page import="org.commonsemantics.grails.users.utils.UsersUtils" %>
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
			<td><g:render plugin="cs-user" template="/users/userCreatePersonFields"/></td>
		</tr>
		<tr>		
			<td><g:render plugin="cs-user" template="/users/userEditSignUpFields" model="[command:'create']"/></td>
		</tr>
	</table>
</div>