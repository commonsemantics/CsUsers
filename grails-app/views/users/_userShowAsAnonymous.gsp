<%@ page import="org.commonsemantics.grails.agents.utils.AgentsUtils" %>
<%@ page import="org.commonsemantics.grails.users.utils.UsersUtils" %>

<%-- 
By Dr. Paolo Ciccarese <paolo.ciccarese@gmail.com>

Parameters list
 1) user | instance of GroupCreateCommand
Stylesheet
 1) fieldError | background and font color in erroneous text fields
--%>
<div class="sectioncontainer">
	<g:if test="${user!=null}">
		<table>
			<tbody>
				<tr>
					<td valign="top"  align="left">
						<g:message code="org.commonsemantics.grails.users.model.field.displayName" default="Display name"/>
					</td>
					<td valign="top" align="left">
						Anonymous User
					</td>
				</tr>			
				<tr>
					<td valign="top" align="left">
						<g:message code="org.commonsemantics.grails.users.model.field.profileprivacy" default="Profile Privacy"/>
					</td>
					<td valign="top" colspan="2" align="left">
						<div>			
							${user.profilePrivacy.label}
						</div>
					</td>
				</tr>
				<tr>
					<td valign="top" align="left">
						<g:message code="org.commonsemantics.grails.users.model.field.accountStatus" default="Account Status"/>
					</td>
					<td valign="top" colspan="2" align="left">
						<div>
							${UsersUtils.getStatusLabel(user)}						
						</div>
					</td>
				</tr>
				 <%--
				--%>
			</tbody>
		</table>
	</g:if>
	<g:else>
		<g:render plugin="cs-commons" template="/lenses/error" model="['message':'CsUser._userShow: user object cannot be null.']"/>
	</g:else>
</div>


