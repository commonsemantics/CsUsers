
<%-- by Paolo Ciccarese --%>
<%-- 
Parameters list
 1) item | instance of UserCreateCommand
Stylesheet
 1) fieldError | background and font color in erroneous text fields
--%>
<g:if test="${user!=null}">
	<table>
		<tbody>
			<g:if test="${grailsApplication.config.org.commonsemantics.grails.users.model.field.title!='hide'}">
				<tr class="prop">
					<td valign="top" width="190px" class="name">
						<label for="name"><g:message code="org.commonsemantics.grails.users.model.field.title" default="Title"/></label>
					</td>
					<td valign="top" width="155px" class="value">
						<g:textField name="title" style="width: 240px;"
							value="${user?.title}"  class="${hasErrors(bean: user, field: 'title', 'fieldError')}"/>
					</td>
					<td valign="top" class="caption">
						<g:if test="${user?.title!=null}">
							<g:renderErrors bean="${user}" field="title" />
						</g:if> 
						<g:else>
			           		(max 255 chars)
			            </g:else>
			        </td>
				</tr>
			</g:if>
			<g:if test="${grailsApplication.config.org.commonsemantics.grails.users.model.field.firstName!='hide'}">
				<tr class="prop">
					<td valign="top" width="190px" class="name">
						<label for="name"><g:message code="org.commonsemantics.grails.users.model.field.firstName" default="First name"/></label>
					</td>
					<td valign="top" width="155px" class="value">
						<g:textField name="firstName" style="width: 240px;"
							value="${user?.firstName}"  class="${hasErrors(bean: user, field: 'firstName', 'fieldError')}"/>
					</td>
					<td valign="top" class="caption">
						<g:if test="${user?.firstName!=null}">
							<g:renderErrors bean="${user}" field="firstName" />
						</g:if> 
						<g:else>
			           		(max 255 chars)
			            </g:else>
			        </td>
				</tr>
			</g:if>
			<g:if test="${grailsApplication.config.org.commonsemantics.grails.users.model.field.middleName!='hide'}">
				<tr class="prop">
					<td valign="top" width="190px" class="name">
						<label for="name">
							<g:message code="org.commonsemantics.grails.users.model.field.middleName" default="Middle name"/>
						</label>
					</td>
					<td valign="top" width="155px" class="value">
						<g:textField name="middleName" style="width: 240px;"
							value="${user?.middleName}"  class="${hasErrors(bean: user, field: 'middleName', 'fieldError')}"/>
					</td>
					<td valign="top" class="caption">
						<g:if test="${user?.middleName!=null}">
							<g:renderErrors bean="${user}" field="middleName" />
						</g:if> 
						<g:else>
			           		(max 255 chars)
			            </g:else>
			        </td>
				</tr>
			</g:if>
			<g:if test="${grailsApplication.config.org.commonsemantics.grails.users.model.field.lastName!='hide'}">
				<tr class="prop">
					<td valign="top" class="name">
						<label for="name">
							<g:message code="org.commonsemantics.grails.users.model.field.lastName" default="Last name"/>
						</label>
					</td>
					<td valign="top" class="value">
						<g:textField name="lastName" style="width: 240px;"
							value="${user?.lastName}"  class="${hasErrors(bean: user, field: 'lastName', 'fieldError')}"/>
					</td>
					<td valign="top" class="caption">
						<g:if test="${user?.lastName!=null}">
							<g:renderErrors bean="${user}" field="lastName" />
						</g:if> 
						<g:else>
			           		(max 255 chars)
			            </g:else>
			        </td>
				</tr>
			</g:if>
			<tr class="prop">
				<td valign="top" class="name">
					<label for="name">
						<g:message code="org.commonsemantics.grails.users.model.field.displayName" default="Display name"/>
					</label>
				</td>
				<td valign="top" class="value">
					<g:textField name="displayName" style="width: 240px;"
						value="${user?.displayName}"  class="${hasErrors(bean: user, field: 'displayName', 'fieldError')}"/>
				</td>
				<td valign="top" class="caption">
					<g:if test="${user?.displayName!=null}">
						<g:renderErrors bean="${user}" field="displayName" />
					</g:if> 
					<g:else>
		           		(max 255 chars)
		            </g:else>
		        </td>
			</tr>
			<tr class="prop">
				<td valign="top" class="name">
					<label for="name">Email*</label>
				</td>
				<td valign="top" class="value">
					<g:textField name="email" style="width: 240px;"
						value="${user?.email}"  class="${hasErrors(bean: user, field: 'displayName', 'fieldError')}"/>
				</td>
				<td valign="top" class="caption">
					<g:if test="${user?.email!=null}">
						<g:renderErrors bean="${user}" field="email" />
					</g:if> 
					<g:else>
		           		(valid email)
		            </g:else>
		        </td>
			</tr>
			<g:if test="${grailsApplication.config.org.commonsemantics.grails.users.model.field.affiliation!='hide'}">
				<tr class="prop">
					<td valign="top" class="name">
						<label for="name">
							<g:message code="org.commonsemantics.grails.users.model.field.affiliation" default="Affiliation"/>
						</label>
					</td>
					<td valign="top" class="value">
						<g:textField name="affiliation" style="width: 240px;"
							value="${user?.affiliation}"  class="${hasErrors(bean: user, field: 'affiliation', 'fieldError')}"/>
					</td>
					<td valign="top" class="caption">
						<g:if test="${user?.affiliation!=null}">
							<g:renderErrors bean="${user}" field="affiliation" />
						</g:if> 
						<g:else>
			           		(max 255 chars)
			            </g:else>
			        </td>
				</tr>
			</g:if>
			<g:if test="${grailsApplication.config.org.commonsemantics.grails.users.model.field.country!='hide'}">
				<tr class="prop">
					<td valign="top" class="name">
						<label for="name">
							<g:message code="org.commonsemantics.grails.users.model.field.country" default="Country"/>
						</label>
					</td>
					<td valign="top" class="value">
						<g:textField name="country" style="width: 240px;"
							value="${user?.country}"  class="${hasErrors(bean: user, field: 'displayName', 'fieldError')}"/>
					</td>
					<td valign="top" class="caption">
						<g:if test="${user?.country!=null}">
							<g:renderErrors bean="${user}" field="country" />
						</g:if> 
						<g:else>
			           		(valid country)
			            </g:else>
			        </td>
				</tr>
			</g:if>
		</tbody>
	</table>
</g:if>
<g:else>
	<g:render plugin="cs-commons" template="/lenses/error" model="['message':'CsUser._userProfileFields: user object cannot be null.']"/>
</g:else>

