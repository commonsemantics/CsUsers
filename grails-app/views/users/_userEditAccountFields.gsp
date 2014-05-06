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
			<tr class="prop">
				<td valign="top" class="name">
					<label for="userRole">
						<g:message code="org.commonsemantics.grails.users.model.field.role" default="Role"/>
					</label>
				</td>
				<td valign="top" colspan="2" class="value">
					<div>
						<g:each in="${Role.list()}">
							<g:set var="roleFlag" value="false" />
							<g:each in="${userRoles}" var="userRole">
								<g:if test="${it.label==userRole.label}">
									<g:set var="roleFlag" value="true" />
								</g:if>
							</g:each>
							<g:if test="${roleFlag=='true'}">
								<g:checkBox name="${it.label}" value="${true}" /> ${it.label}
							</g:if>
							<g:else>
								<g:checkBox name="${it.label}" /> ${it.label}
							</g:else>
						</g:each>
					</div>
				</td>
			</tr>
			<tr class="prop">
				<td valign="top" class="name">
					<label for="userProfilePrivacy">
						<g:message code="org.commonsemantics.grails.users.model.field.profileprivacy" default="Profile Privacy"/>
					</label>
				</td>
				<td valign="top" colspan="2" class="value">
					<div>
						<g:each in="${ProfilePrivacy.list()}">
							<g:set var="privacyFlag" value="false" />
							<g:each in="${userProfilePrivacy}" var="userProfilePrivacy">
								<g:if test="${it.label==userProfilePrivacy.label}">
									<g:set var="privacyFlag" value="true" />
								</g:if>
							</g:each>
							<g:if test="${privacyFlag=='true'}">
								<g:checkBox name="${it.label}" value="${true}" /> ${it.label}
							</g:if>
							<g:else>
								<g:checkBox name="${it.label}" /> ${it.label}
							</g:else>
						</g:each>
					</div>
				</td>
			</tr>
			<tr class="prop">
				<td valign="top"  class="name">
					<label for="userStatus">
						<g:message code="org.commonsemantics.grails.users.model.field.accountStatus" default="Account Status"/>
					</label>
				</td>
				<td valign="top" class="value" colspan="2">
					<div>
						<g:if test="${UsersUtils.getStatusLabel(user)==UserStatus.CREATED_USER.value()}">
							<g:radio name="userStatus" value="${UserStatus.CREATED_USER.value()}" checked="${true}"/> New account
							<g:radio name="userStatus" value="${UserStatus.ACTIVE_USER.value()}"/> Active account
						</g:if>
						<g:elseif test="${UsersUtils.getStatusLabel(user)==UserStatus.ACTIVE_USER.value()}">
							<g:radio name="userStatus" value="${UserStatus.ACTIVE_USER.value()}" checked="${true}"/> Active
							<g:radio name="userStatus" value="${UserStatus.LOCKED_USER.value()}" checked="${false}"/> Lock 
							<g:radio name="userStatus" value="${UserStatus.DISABLED_USER.value()}" checked="${false}"/> Disable 
						</g:elseif>
						<g:elseif test="${UsersUtils.getStatusLabel(user)==UserStatus.LOCKED_USER.value()}">
							<g:radio name="userStatus" value="${UserStatus.ACTIVE_USER.value()}" checked="${false}"/> Activate 
							<g:radio name="userStatus" value="${UserStatus.LOCKED_USER.value()}" checked="${true}"/> Locked 
							<g:radio name="userStatus" value="${UserStatus.DISABLED_USER.value()}" checked="${false}"/> Disable 
						</g:elseif>
						<g:else>
							<g:radio name="userStatus" value="${UserStatus.ACTIVE_USER.value()}" checked="${false}"/> Activate 
							<g:radio name="userStatus" value="${UserStatus.LOCKED_USER.value()}" checked="${false}"/> Lock 
							<g:radio name="userStatus" value="${UserStatus.DISABLED_USER.value()}" checked="${true}"/> Disabled
						</g:else>
					</div>
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
				<td valign="top" class="value">
					<div>
						<g:textField name="username" style="width: 276px;"
							value="${user?.username}"  class="${hasErrors(bean: user, field: 'username', 'csc-field-error')}"/>
					</div>
				</td>
				<td>
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
						<g:message code="org.commonsemantics.grails.users.model.field.passwordconfirmation" default="Password Confirmation"/>*
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
					<label for="userRole">
						<g:message code="org.commonsemantics.grails.users.model.field.role" default="Role"/>
					</label>
				</td>
				<td valign="top" colspan="2" class="value">
					<div>
						<g:each in="${Role.list()}">
							<g:checkBox name="${it.label}" /> ${it.label}
						</g:each>
					</div>
				</td>
				<td></td>
			</tr>
			<tr class="prop">
				<td valign="top"  class="name">
					<label for="userStatus">
						<g:message code="org.commonsemantics.grails.users.model.field.accountStatus" default="Account Status"/>
					</label>
				</td>
				<td valign="top" class="value" colspan="2">
					<div>
						<g:radio name="userStatus" value="${UserStatus.ACTIVE_USER.value()}" checked="${true}"/> Activate 
						<g:radio name="userStatus" value="${UserStatus.LOCKED_USER.value()}" checked="${false}"/> Lock 
						<g:radio name="userStatus" value="${UserStatus.DISABLED_USER.value()}" checked="${false}"/> Disable 
					</div>
				</td>
				<td></td>
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
