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
		<table class="csc-lens-container" style="width: 460px; border: 5px solid #efefef;margin-top:10px;">
			<tbody>
				<%-- 
				<tr class="sprop">
					<td valign="top" colspan="2"  style="horizontal-align:center;">
						<img src="${resource(dir:'images/dashboard',file:'no_image.gif',plugin:'users-module')}" width="200px" />
					</td>
				</tr>
				--%>
				<tr class="sprop">
					<td valign="top" width="150px"  align="left">
						<label for="title">Title</label>
					</td>
					<td valign="top" width="265px" align="left">
						<g:if test="${user?.title}">${user?.title}</g:if>
						<g:else>-</g:else>
					</td>
				</tr>
				<tr class="sprop">
					<td valign="top" align="left">
						<label for="firstName">First name</label>
					</td>
					<td valign="top" align="left">
						${user?.firstName}
					</td>
				</tr>
				<tr class="sprop">
					<td valign="top"  align="left">
						<label for="middleName">Middle name</label>
					</td>
					<td valign="top" align="left">
						<g:if test="${user?.middleName}">${user?.middleName}</g:if>
						<g:else>-</g:else>
					</td>
				</tr>
				<tr class="sprop">
					<td valign="top"  align="left">
						<label for="lastName">Last name</label>
					</td>
					<td valign="top" align="left">
						${user?.lastName}
					</td>
				</tr>
				<tr class="sprop">
					<td valign="top"  align="left">
						<label for="displayName">Display name</label>
					</td>
					<td valign="top" align="left">
						${user?.displayName}
					</td>
				</tr>
				<tr class="sprop">
					<td valign="top"  align="left">
						<label for="displayName">Username</label>
					</td>
					<td valign="top" align="left">
						${user?.username}
					</td>
				</tr>
				<tr class="sprop">
					<td valign="top"  align="left">
						<label for="displayName">Email</label>
					</td>
					<td valign="top" align="left">
						${user?.email}
					</td>
				</tr>
				<tr class="sprop">
					<td valign="top"  align="left">
						<label for="displayName">Affiliation</label>
					</td>
					<td valign="top" align="left">
						<g:if test="${user?.affiliation}">${user?.affiliation}</g:if>
						<g:else>-</g:else>
					</td>
				</tr>
				<tr class="sprop">
					<td valign="top"  align="left">
						<label for="displayName">Country</label>
					</td>
					<td valign="top" align="left">
						<g:if test="${user?.country}">${user?.country}</g:if>
						<g:else>-</g:else>
					</td>
				</tr>
				<tr class="sprop">
					<td valign="top" align="left">
						<label for="userRole">Role</label>
					</td>
					<td valign="top" colspan="2" align="left">
						<div>
							<g:if test="${userRoles!=null && userRoles.size()>0}">
								<g:each in="${userRoles}">
									${it.label}
								</g:each>
							</g:if>
							<g:else>-</g:else>
						</div>
					</td>
				</tr>
				<tr class="sprop">
					<td valign="top" align="left">
						<label for="userRole">Account Status</label>
					</td>
					<td valign="top" colspan="2" align="left">
						<div>
							${UserUtils.getStatusLabel(user)}						
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</g:if>
	<g:else>
		<g:render plugin="cs-commons" template="/lenses/error" model="['message':'CsUser._userShow: user object cannot be null.']"/>
	</g:else>
</div>


