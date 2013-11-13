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
			<tbody>
				<%-- 
				<tr>
					<td valign="top" colspan="2"  style="horizontal-align:center;">
						<img src="${resource(dir:'images/dashboard',file:'no_image.gif',plugin:'users-module')}" width="200px" />
					</td>
				</tr>
				--%>
				<g:if test="${grailsApplication.config.org.commonsemantics.grails.users.model.field.title!='hide'}">
					<tr>
						<td valign="top" width="150px"  align="left">
							<g:message code="org.commonsemantics.grails.users.model.field.title" default="Title"/>
						</td>
						<td valign="top" width="265px" align="left">
							<g:if test="${user?.title}">${user?.title}</g:if>
							<g:else>-</g:else>
						</td>
					</tr>
				</g:if>
				<g:if test="${grailsApplication.config.org.commonsemantics.grails.users.model.field.firstName!='hide'}">
					<tr>
						<td valign="top" align="left">
							<g:message code="org.commonsemantics.grails.users.model.field.firstName" default="First name"/>
						</td>
						<td valign="top" align="left">
							<g:if test="${user?.firstName}">${user?.firstName}</g:if>
							<g:else>-</g:else>
						</td>
					</tr>
				</g:if>
				<g:if test="${grailsApplication.config.org.commonsemantics.grails.users.model.field.middleName!='hide'}">
					<tr>
						<td valign="top"  align="left">
							<g:message code="org.commonsemantics.grails.users.model.field.middleName" default="Middle name"/>
						</td>
						<td valign="top" align="left">
							<g:if test="${user?.middleName}">${user?.middleName}</g:if>
							<g:else>-</g:else>
						</td>
					</tr>
				</g:if>
				<g:if test="${grailsApplication.config.org.commonsemantics.grails.users.model.field.lastName!='hide'}">
					<tr>
						<td valign="top"  align="left">
							<g:message code="org.commonsemantics.grails.users.model.field.lastName" default="Last name"/>
						</td>
						<td valign="top" align="left">
							<g:if test="${user?.lastName}">${user?.lastName}</g:if>
							<g:else>-</g:else>
						</td>
					</tr>
				</g:if>
				<tr>
					<td valign="top"  align="left">
						<g:message code="org.commonsemantics.grails.users.model.field.displayName" default="Display name"/>
					</td>
					<td valign="top" align="left">
						${user?.displayName}
					</td>
				</tr>
				<tr>
					<td valign="top"  align="left">
						<g:message code="org.commonsemantics.grails.users.model.field.username" default="Username"/>
					</td>
					<td valign="top" align="left">
						${user?.username}
					</td>
				</tr>
				<tr>
					<td valign="top"  align="left">Email</td>
					<td valign="top" align="left">
						${user?.email}
					</td>
				</tr>
				<g:if test="${grailsApplication.config.org.commonsemantics.grails.users.model.field.affiliation!='hide'}">
					<tr>
						<td valign="top"  align="left">
							<g:message code="org.commonsemantics.grails.users.model.field.affiliation" default="Affiliation"/>
						</td>
						<td valign="top" align="left">
							<g:if test="${user?.affiliation}">${user?.affiliation}</g:if>
							<g:else>-</g:else>
						</td>
					</tr>
				</g:if>
				<g:if test="${grailsApplication.config.org.commonsemantics.grails.users.model.field.country!='hide'}">
					<tr>
						<td valign="top"  align="left">
							<g:message code="org.commonsemantics.grails.users.model.field.country" default="Country"/>
						</td>
						<td valign="top" align="left">
							<g:if test="${user?.country}">${user?.country}</g:if>
							<g:else>-</g:else>
						</td>
					</tr>
				</g:if>
				<tr>
					<td valign="top" align="left">
						<g:message code="org.commonsemantics.grails.users.model.field.role" default="Role"/>
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
				<tr>
					<td valign="top" align="left">
						<g:message code="org.commonsemantics.grails.users.model.field.accountStatus" default="Account Status"/>
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

