<%@ page import="org.commonsemantics.grails.users.utils.UserUtils" %>
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
				<g:hiddenField name="id" value="${user?.id}" /> 
				<g:hiddenField name="username" value="${user?.username}" />
				
				********* to add warning and error lenses
				
				<tr>
					<td valign="top" colspan="2">
						<g:if test="${msgError!=null}">
							${msgError}
						</g:if>
					</td>
				</tr>
				
				<g:render plugin="cs-user" template="/users/uProfileFields" />
				<g:render plugin="cs-user" template="/users/uAccountFields" />
				

			</table>
	</g:if>
	<g:else>
		<g:render plugin="cs-commons" template="/lenses/error" model="['message':'CsUser._userEdit: user object cannot be null.']"/>
	</g:else>
</div>