<%-- by Paolo Ciccarese --%>
<%-- 
Parameters list
 1) user | instance of UserCreateCommand
Stylesheet
 1) fieldError | background and font color in erroneous text fields
--%>
<%@ page import="org.commonsemantics.grails.users.utils.UserStatus" %>
<%@ page import="org.commonsemantics.grails.users.utils.UserUtils" %>

<g:if test="${user!=null}">
	<table>
		<tbody>
			<tr class="prop">
				<td valign="top" width="140px" class="name">
					<label for="username">Username</label>
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
				<td valign="top" class="name">
					<label for="userRole">Role</label>
				</td>
				<td valign="top" colspan="2" class="value">
					<div>
						<g:each in="${roles}">
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
				<td valign="top"  class="name">
					<label for="userStatus">Account Status</label>
				</td>
				<td valign="top" class="value" colspan="2">
					<div>
						<g:if test="${UserUtils.getStatusLabel(user)==UserStatus.CREATED_USER.value()}">
							<g:radio name="userStatus" value="${UserStatus.CREATED_USER.value()}" checked="${true}"/> New account
							<g:radio name="userStatus" value="${UserStatus.ACTIVE_USER.value()}"/> Active account
						</g:if>
						<g:elseif test="${UserUtils.getStatusLabel(user)==UserStatus.ACTIVE_USER.value()}">
							<g:radio name="userStatus" value="${UserStatus.ACTIVE_USER.value()}" checked="${true}"/> Active
							<g:radio name="userStatus" value="${UserStatus.LOCKED_USER.value()}" checked="${false}"/> Lock 
							<g:radio name="userStatus" value="${UserStatus.DISABLED_USER.value()}" checked="${false}"/> Disable 
						</g:elseif>
						<g:elseif test="${UserUtils.getStatusLabel(user)==UserStatus.LOCKED_USER.value()}">
							<g:radio name="userStatus" value="${UserStatus.ACTIVE_USER.value()}" checked="${false}"/> Activate 
							<g:radio name="userStatus" value="${UserStatus.LOCKED_USER.value()}" checked="${true}"/> Lock 
							<g:radio name="userStatus" value="${UserStatus.DISABLED_USER.value()}" checked="${false}"/> Disable 
						</g:elseif>
						<g:else>
							<g:radio name="userStatus" value="${UserStatus.ACTIVE_USER.value()}" checked="${false}"/> Activate 
							<g:radio name="userStatus" value="${UserStatus.LOCKED_USER.value()}" checked="${false}"/> Lock 
							<g:radio name="userStatus" value="${UserStatus.DISABLED_USER.value()}" checked="${true}"/> Disable 
						</g:else>
					</div>
				</td>
			</tr>
		</tbody>
	</table>
</g:if>
<g:else>
	<g:render plugin="cs-commons" template="/lenses/error" model="['message':'CsUser._userAccoundFields: user object cannot be null.']"/>
</g:else>
