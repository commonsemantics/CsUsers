<%-- by Paolo Ciccarese --%>
<%-- 
Parameters list
 1) user | instance of UserCreateCommand
Stylesheet
 1) fieldError | background and font color in erroneous text fields
--%>
<%@ page import="org.commonsemantics.grails.users.utils.DefaultUsersProfilePrivacy" %>
<%@ page import="org.commonsemantics.grails.users.utils.UserStatus" %>
<%@ page import="org.commonsemantics.grails.users.utils.UsersUtils" %>
<%@ page import="org.commonsemantics.grails.users.model.Role" %>
<%@ page import="org.commonsemantics.grails.users.model.ProfilePrivacy" %>

<g:if test="${user!=null && command!='create'}">
	<table>
		<tbody>
			<tr class="prop">
				<td valign="top" width="160px" class="name">
					<label for="username">
						<g:message code="org.commonsemantics.grails.users.model.field.username" default="Username"/>
					</label>
				</td>
				<td valign="top" width="265px" class="value">
					<div>
						${user?.username}
					</div>
				</td>
				<td valign="top">
			    </td>
			</tr>
			<tr class="prop">
				<td valign="top" width="160px" class="name">
					<label for="password">
						<g:message code="org.commonsemantics.grails.users.model.field.password" default="Password"/>
					</label>
				</td>
				<td valign="top" width="265px" class="value">
					<div>
						${user?.password}
					</div>
				</td>
				<td valign="top">
			    </td>
			</tr>
		</tbody>
	</table>
</g:if>
<g:elseif test="${command=='create'}">
	<table>
		<tbody>
			<tr class="prop">
				<td valign="top" width="160px" class="name">
					<label for="username">
						<g:message code="org.commonsemantics.grails.users.model.field.username" default="Username"/>*
					</label>
				</td>
				<td valign="top" class="value" width="279px">
					<g:textField name="username" style="width: 276px;"
						value="${user?.username}"  class="${hasErrors(bean: user, field: 'username', 'csc-field-error')}"/>
				</td>
				<td valign="top" class="caption">
					(4-16 <g:message code="org.commonsemantics.grails.general.chars" default="chars"/>)
				</td>
				
				<g:if test="${user?.errors?.hasFieldErrors('username')}">
					<tr>
						<td></td>
						<td colspan="2" class="csc-error-message"><g:renderErrors bean="${user}" field="username" /></td>
					</tr>
				</g:if>
			</tr>
			<tr class="prop">
				<td valign="top" width="160px" class="name">
					<label for="password">
						<g:message code="org.commonsemantics.grails.users.model.field.password" default="Password"/>*
					</label>
				</td>
				<td valign="top" class="value">
					<div>
						<g:textField name="password" style="width: 276px;"
							value="${user?.password}"  class="${hasErrors(bean: user, field: 'password', 'csc-field-error')}"/>
					</div>
				</td>
				<td>
					(4-16 <g:message code="org.commonsemantics.grails.general.chars" default="chars"/>)
				</td>
				
				<g:if test="${user?.errors?.hasFieldErrors('password')}">
					<tr>
						<td></td>
						<td colspan="2" class="csc-error-message"><g:renderErrors bean="${user}" field="password" /></td>
					</tr>
				</g:if>
			</tr>
			<tr class="prop">
				<td valign="top" width="160px" class="name">
					<label for="passwordConfirmation">
						<g:message code="org.commonsemantics.grails.users.model.field.passwordconfirmation" default="Pwd Confirmation"/>*
					</label>
				</td>
				<td valign="top" class="value">
					<div>
						<g:textField name="passwordConfirmation" style="width: 276px;"
							value="${user?.passwordConfirmation}"  class="${hasErrors(bean: user, field: 'passwordConfirmation', 'csc-field-error')}"/>
					</div>
				</td>
				<td>
					(4-16 <g:message code="org.commonsemantics.grails.general.chars" default="chars"/>)
				</td>
				
				<g:if test="${user?.errors?.hasFieldErrors('passwordConfirmation')}">
					<tr>
						<td></td>
						<td colspan="2" class="csc-error-message"><g:renderErrors bean="${user}" field="passwordConfirmation" /></td>
					</tr>
				</g:if>
			</tr>
			<tr class="prop">
				<td valign="top" class="name">
					<label for="userProfilePrivacy">
						<g:message code="org.commonsemantics.grails.users.model.field.profileprivacy" default="Profile Privacy"/>
					</label>
				</td>
				<td valign="top" colspan="2" class="value">
					<g:radio name="userProfilePrivacy" value="${DefaultUsersProfilePrivacy.PUBLIC.value()}" checked="${true}"/> ${DefaultUsersProfilePrivacy.PUBLIC.label()} 
					<g:radio name="userProfilePrivacy" value="${DefaultUsersProfilePrivacy.RESTRICTED.value()}" checked="${false}"/> Restricted 
					<g:radio name="userProfilePrivacy" value="${DefaultUsersProfilePrivacy.PRIVATE.value()}" checked="${false}"/> Private
					<g:radio name="userProfilePrivacy" value="${DefaultUsersProfilePrivacy.ANONYMOUS.value()}" checked="${false}"/> Anonymous 
				</td>
				<td></td>
			</tr>
		</tbody>
	</table>
</g:elseif>
<g:else>
	<g:render plugin="cs-commons" template="/lenses/error" model="['message':'CsUser._userAccoundFields: user object cannot be null.']"/>
</g:else>
