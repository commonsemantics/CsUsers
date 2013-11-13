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
				
				<tr>
					<td valign="top" >
						<g:if test="${msgError!=null}">
							<g:render plugin="cs-commons" template="/lenses/error" model="['message':msgError]"/>
						</g:if>
						<g:elseif test="${msgWarning!=null}">
							<g:render plugin="cs-commons" template="/lenses/warning" model="['message':msgWarning]"/>
						</g:elseif>
					</td>
				</tr>
				<tr>
					<td>
				<g:render plugin="cs-user" template="/users/uProfileFields" />
				<g:render plugin="cs-user" template="/users/uAccountFields" />
					</td>
				</tr>

			</table>
	</g:if>
	<g:else>
		<g:render plugin="cs-commons" template="/lenses/error" model="['message':'CsUser._userEdit: user object cannot be null.']"/>
	</g:else>
</div>