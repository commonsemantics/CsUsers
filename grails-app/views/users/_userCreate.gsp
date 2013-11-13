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
		<g:form method="post" >
			<table>
				<tr>
					<td valign="top" colspan="2">
						<g:if test="${msgError!=null}">
							${msgError}
						</g:if>
					</td>
				</tr>
				
				<g:render plugin="cs-user" template="/users/uProfileFields" />
				<g:render plugin="cs-user" template="/users/uAccountFields" />
				
				<tr>
					<td valign="top" colspan="2" >
						<div class="buttons">
							<span class="button">
								<g:actionSubmit class="save" action="updateUser" value="${message(code: 'default.button.edit.account.label', default: 'Create user')}" />
							</span>
							<span class="button">
								<g:actionSubmit class="cancel" action="showUser" value="${message(code: 'default.button.edit.account.label', default: 'Cancel')}" />
							</span>
						</div>
					</td>
				</tr>
			</table>
		</g:form>
	</g:if>
	<g:else>
		<g:render plugin="cs-commons" template="/lenses/error" model="['message':'CsUser._userEdit: user object cannot be null.']"/>
	</g:else>
</div>