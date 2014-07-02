<%@ page import="org.commonsemantics.grails.users.model.User" %>
<%@ page import="org.commonsemantics.grails.users.model.UserRole" %>
<%@ page import="org.commonsemantics.grails.users.utils.DefaultUsersRoles" %>
<%@ page import="org.commonsemantics.grails.users.utils.DefaultUsersProfilePrivacy" %>
<%@ page import="org.commonsemantics.grails.users.utils.UsersUtils" %>

<div id="request" class="sectioncontainer">

	<g:set var="loggedUserRole" value="${UserRole.findAllByUser(loggedUser)}"/>
	<g:if test="${loggedUserRole.role.authority.contains(DefaultUsersRoles.ADMIN.value())}"><g:set var="loggedUserRoleLevel" value="3"/></g:if>
	<g:else>
		<g:if test="${loggedUserRole.role.authority.contains(DefaultUsersRoles.MANAGER.value())}"><g:set var="loggedUserRoleLevel" value="2"/></g:if>
		<g:else>
			<g:if test="${loggedUserRole.role.authority.contains(DefaultUsersRoles.USER.value())}"><g:set var="loggedUserRoleLevel" value="1"/></g:if>
		</g:else>
	</g:else>
		

<div class="dialog">
	<div class="list">
	<table class="tablelist">
		<thead>
			<tr>
				<g:sortableColumn property="username" title="${message(code: 'agentPerson.id.label', default: 'Username')}" />
				<g:sortableColumn property="name" title="${message(code: 'agentPerson.id.label', default: 'Name')}" />
				<g:sortableColumn property="isAdmin" title="${message(code: 'agentPerson.id.label', default: 'Adm')}" />
				<g:sortableColumn property="isManager" title="${message(code: 'agentPerson.id.label', default: 'Mgr')}" />
				<g:sortableColumn property="isUser" title="${message(code: 'agentPerson.id.label', default: 'Usr')}" />
				<g:sortableColumn property="dateCreated" title="${message(code: 'agentPerson.id.label', default: 'Member Since')}" />
				<g:sortableColumn property="lastUpdated" title="${message(code: 'agentPerson.id.label', default: 'Last updated')}" />
				<g:sortableColumn property="status" title="${message(code: 'agentPerson.id.label', default: 'Status')}" />
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<g:each in="${users}" status="i" var="user">
				<g:set var="userObject" value="${User.findByUsername(user.username)}"/>
				<g:set var="userRole" value="${UserRole.findAllByUser(userObject)}"/>
				<g:if test="${userRole.role.authority.contains(DefaultUsersRoles.ADMIN.value())}"><g:set var="userRoleLevel" value="3"/></g:if>
				<g:else>
					<g:if test="${userRole.role.authority.contains(DefaultUsersRoles.MANAGER.value())}"><g:set var="userRoleLevel" value="2"/></g:if>
					<g:else>
						<g:if test="${userRole.role.authority.contains(DefaultUsersRoles.USER.value())}"><g:set var="userRoleLevel" value="1"/></g:if>
					</g:else>
				</g:else>	
			
				<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
		     		<td><g:link action="showUser" id="${user.id}">${user.username}</g:link></td>
		     		<g:if test="${loggedUserRoleLevel=='3' || user.profilePrivacy.label==DefaultUsersProfilePrivacy.PUBLIC.label()}">
		     			<td>${user.person.lastName} ${user.person.firstName} <g:if test="${user?.person.displayName?.length()>0}">(${user.person.displayName})</g:if></td>
		     		</g:if>
		     		<g:elseif test="${user.profilePrivacy.label==DefaultUsersProfilePrivacy.PRIVATE.label()}">
		     			<td><g:if test="${user?.person.displayName?.length()>0}">${user.person.displayName}</g:if><g:else>${user.person.lastName} ${user.person.firstName} </g:else></td>
		     		</g:elseif>
		     		<g:elseif test="${user.profilePrivacy.label==DefaultUsersProfilePrivacy.ANONYMOUS.label()}">
		     			<td>Anonymous</td>
		     		</g:elseif>
			
		     		<td>
		     			<g:if test="${userRole.role.authority.contains(DefaultUsersRoles.ADMIN.value())}">Y</g:if>
				    </td>
				    <td>
				    	<g:if test="${userRole.role.authority.contains(DefaultUsersRoles.MANAGER.value())}">Y</g:if>
				    </td>
				     <td>
				     	<g:if test="${userRole.role.authority.contains(DefaultUsersRoles.USER.value())}">Y</g:if>
				    </td>

		     		<td><g:formatDate format="MM/dd/yyyy hh:mm" date="${user.dateCreated}"/></td>
		     		<td><g:formatDate format="MM/dd/yyyy hh:mm" date="${user.lastUpdated}"/></td>
		     		<td>
		     			${UsersUtils.getStatusLabel(userObject)}
					</td>
		     		<td>
		     			<div class="buttons">
		     				<g:if test="${grailsApplication.config.org.commonsemantics.grails.users.dashboard.user.creation!='disabled'}">
		     					<g:if test="${loggedUserRoleLevel>=userRoleLevel || loggedUser.id == user.id}">
									<g:form>
										<g:hiddenField name="id" value="${user?.id}" /> 
										<g:hiddenField name="redirect" value="listUsers" />
										<span class="button">
											<g:actionSubmit class="edit"  action="editUser" value="${message(code: 'default.button.edit.account.label', default: 'Edit')}" />
										</span>
										<span class="button">
											<g:actionSubmit class="password"  action="changeUserPassword" value="${message(code: 'default.button.edit.account.label', default: 'Change password')}" />
										</span>
										<g:if test="${fieldValue(bean: user, field: 'accountLocked') == 'true'}">
											<span class="button">
												<g:actionSubmit class="unlock" action="unlockUser" value="${message(code: 'default.button.unlock.account.label', default: 'Unlock')}" />
											</span>
										</g:if>
										<g:elseif test="${fieldValue(bean: user, field: 'accountLocked') == 'false'}">
											<span class="button">
												<g:actionSubmit class="lock" action="lockUser" value="${message(code: 'default.button.lock.account.label', default: 'Lock')}"
												onclick="return confirm('${message(code: 'default.button.lock.account.confirm.message', default: 'Are you sure you want to lock this account?')}');" />
											</span>
										</g:elseif>
										<g:if test="${fieldValue(bean: user, field: 'enabled') == 'true'}">
											<span class="button">
												<g:actionSubmit class="disable" action="disableUser" value="${message(code: 'default.button.disable.account.label', default: 'Disable')}" 
												onclick="return confirm('${message(code: 'default.button.disable.account.confirm.message', default: 'Are you sure you want to disable this account?')}');"/>
											</span>
										</g:if>
										<g:elseif test="${fieldValue(bean: user, field: 'enabled') == 'false'}">
											<span class="button">
												<g:actionSubmit class="enable" action="enableUser" value="${message(code: 'default.button.enable.account.label', default: 'Enable')}" />
											</span>
										</g:elseif>
									</g:form>
								</g:if>
								<g:else>
									&nbsp;Not accessible
								</g:else>
							</g:if>
							<g:else>
								&nbsp;Not available
							</g:else>
						</div>
		     		</td>
		     	</tr>
			</g:each>
		</tbody>
	</table>
	<div class="paginateButtons">
   		<g:paginate total="${usersTotal}" controller="${controller}" action="${action}"/> 
	</div>
</div>
</div>
</div>