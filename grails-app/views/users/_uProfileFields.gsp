<%@ page import="org.commonsemantics.grails.users.utils.UserUtils" %>

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
				<g:render plugin="cs-user" template="/users/uPropertyEntry" model="[
					messageCode:'org.commonsemantics.grails.users.model.field.title',
					messageDefault:'Title',
					mandatory:UserUtils.isFieldMandatory(grailsApplication, 'title'),
					variable: 'title',
					value: user?.title,
					caption: '(max 255 ' +  g.message(code: 'org.commonsemantics.grails.general.chars',default:'chars') + ')'
				]" />
			</g:if>
			<g:if test="${grailsApplication.config.org.commonsemantics.grails.users.model.field.firstName!='hide'}">
				<g:render plugin="cs-user" template="/users/uPropertyEntry" model="[
					messageCode:'org.commonsemantics.grails.users.model.field.firstName',
					messageDefault:'First name',
					mandatory:UserUtils.isFieldMandatory(grailsApplication, 'firstName'),
					variable: 'firstName',
					value: user?.firstName,
					caption: '(max 255 ' +  g.message(code: 'org.commonsemantics.grails.general.chars',default:'chars') + ')'
				]" />
			</g:if>
			<g:if test="${grailsApplication.config.org.commonsemantics.grails.users.model.field.middleName!='hide'}">
				<g:render plugin="cs-user" template="/users/uPropertyEntry" model="[
					messageCode:'org.commonsemantics.grails.users.model.field.middleName',
					messageDefault:'Middle name',
					mandatory:UserUtils.isFieldMandatory(grailsApplication, 'middleName'),
					variable: 'middleName',
					value: user?.middleName,
					caption: '(max 255 ' +  g.message(code: 'org.commonsemantics.grails.general.chars',default:'chars') + ')'
				]" />
			</g:if>
			<g:if test="${grailsApplication.config.org.commonsemantics.grails.users.model.field.lastName!='hide'}">				
				<g:render plugin="cs-user" template="/users/uPropertyEntry" model="[
					messageCode:'org.commonsemantics.grails.users.model.field.lastName',
					messageDefault:'Last name',
					mandatory:UserUtils.isFieldMandatory(grailsApplication, 'lastName'),
					variable: 'lastName',
					value: user?.lastName,
					caption: '(max 255 ' +  g.message(code: 'org.commonsemantics.grails.general.chars',default:'chars') + ')'
				]" />
			</g:if>			
			<g:render plugin="cs-user" template="/users/uPropertyEntry" model="[
				messageCode:'org.commonsemantics.grails.users.model.field.displayName',
				messageDefault:'Display name',
				mandatory:UserUtils.isFieldMandatory(grailsApplication, 'displayName'),
				variable: 'displayName',
				value: user?.displayName,
				caption: '(max 255 ' +  g.message(code: 'org.commonsemantics.grails.general.chars',default:'chars') + ')'
			]" />
			<g:render plugin="cs-user" template="/users/uPropertyEntry" model="[
				messageCode:'org.commonsemantics.grails.users.model.field.email',
				messageDefault:'Email',
				mandatory:UserUtils.isFieldMandatory(grailsApplication, 'email'),
				variable: 'email',
				value: user?.email,
				caption: '(valid email)'
			]" />
			<g:if test="${grailsApplication.config.org.commonsemantics.grails.users.model.field.affiliation!='hide'}">
				<g:render plugin="cs-user" template="/users/uPropertyEntry" model="[
					messageCode:'org.commonsemantics.grails.users.model.field.affiliation',
					messageDefault:'Affiliation',
					mandatory:UserUtils.isFieldMandatory(grailsApplication, 'affiliation'),
					variable: 'affiliation',
					value: user?.affiliation,
					caption: '(max 255 ' +  g.message(code: 'org.commonsemantics.grails.general.chars',default:'chars') + ')'
				]" />
			</g:if>
			<%-- Custom code to accommodate the country selector --%>
			<g:if test="${grailsApplication.config.org.commonsemantics.grails.users.model.field.country!='hide'}">
				<tr class="prop">
					<td valign="top" class="name">
						<label for="country">
							<g:message code="org.commonsemantics.grails.users.model.field.country" default="Country"/>
							<g:if test="${UserUtils.isFieldMandatory(grailsApplication, 'country')==true}">*</g:if>
						</label>
					</td>
					<td valign="top" class="value">
						<%-- 
						<g:textField name="country" style="width: 240px;"
							value="${user?.country}"  class="${hasErrors(bean: user, field: 'displayName', 'fieldError')}"/>
						--%>
						<%-- http://grails.org/doc/latest/ref/Tags/countrySelect.html --%>
						<g:countrySelect name="country" value="${user?.country}"
                 			noSelection="['':'-Choose your country-']" default="usa"/>
                 		
                 		<%--  create select from a list of ISO3166_3 country codes --%>
						<%-- <g:countrySelect name="country" from="['gbr', 'usa', 'deu', 'ita']" value="${user?.country}"/> --%>
					</td>
					<td valign="top" class="caption">
			        </td>
				</tr>
			</g:if>
		</tbody>
	</table>
</g:if>
<g:elseif test="${command=='create'}">
		<table>
		<tbody>
			<g:if test="${grailsApplication.config.org.commonsemantics.grails.users.model.field.title!='hide'}">
				<tr class="prop">
					<td valign="top" width="160px" class="name">
						<label for="title"><g:message code="org.commonsemantics.grails.users.model.field.title" default="Title"/></label>
					</td>
					<td valign="top" width="155px" class="value">
						<g:textField name="title" style="width: 276px;"
							value="${user?.title}"  class="${hasErrors(bean: user, field: 'title', 'csc-field-error')}"/>
					</td>
					<td valign="top" class="caption">
						(max 255 <g:message code="org.commonsemantics.grails.general.chars" default="chars"/>)
			        </td>
				</tr>
				<g:if test="${user?.errors?.hasFieldErrors('title')}">
					<tr>
						<td></td>
						<td colspan="2" class="csc-error-message"><g:renderErrors bean="${user}" field="title" /></td>
					</tr>
				</g:if>
			</g:if>
			<g:if test="${grailsApplication.config.org.commonsemantics.grails.users.model.field.firstName!='hide'}">
				<tr class="prop">
					<td valign="top" width="160px" class="name">
						<label for="firstName"><g:message code="org.commonsemantics.grails.users.model.field.firstName" default="First name"/></label>
					</td>
					<td valign="top" width="155px" class="value">
						<g:textField name="firstName" style="width: 276px;"
							value="${user?.firstName}"  class="${hasErrors(bean: user, field: 'firstName', 'csc-field-error')}"/>
					</td>
					<td valign="top" class="caption">
			           	(max 255 <g:message code="org.commonsemantics.grails.general.chars" default="chars"/>)
			        </td>
				</tr>
				<g:if test="${user?.errors?.hasFieldErrors('firstName')}">
					<tr>
						<td></td>
						<td colspan="2" class="csc-error-message"><g:renderErrors bean="${user}" field="firstName" /></td>
					</tr>
				</g:if>
			</g:if>
			<g:if test="${grailsApplication.config.org.commonsemantics.grails.users.model.field.middleName!='hide'}">
				<tr class="prop">
					<td valign="top" width="160px" class="name">
						<label for="middleName">
							<g:message code="org.commonsemantics.grails.users.model.field.middleName" default="Middle name"/>
						</label>
					</td>
					<td valign="top" width="155px" class="value">
						<g:textField name="middleName" style="width: 276px;"
							value="${user?.middleName}"  class="${hasErrors(bean: user, field: 'middleName', 'csc-field-error')}"/>
					</td>
					<td valign="top" class="caption">
						(max 255 <g:message code="org.commonsemantics.grails.general.chars" default="chars"/>)
			        </td>
				</tr>
				<g:if test="${user?.errors?.hasFieldErrors('middleName')}">
					<tr>
						<td></td>
						<td colspan="2" class="csc-error-message"><g:renderErrors bean="${user}" field="middleName" /></td>
					</tr>
				</g:if>
			</g:if>
			<g:if test="${grailsApplication.config.org.commonsemantics.grails.users.model.field.lastName!='hide'}">
				<tr class="prop">
					<td valign="top" class="name">
						<label for="lastName">
							<g:message code="org.commonsemantics.grails.users.model.field.lastName" default="Last name"/>
						</label>
					</td>
					<td valign="top" class="value">
						<g:textField name="lastName" style="width: 276px;"
							value="${user?.lastName}"  class="${hasErrors(bean: user, field: 'lastName', 'csc-field-error')}"/>
					</td>
					<td valign="top" class="caption">
						(max 255 <g:message code="org.commonsemantics.grails.general.chars" default="chars"/>)
			        </td>
				</tr>
				<g:if test="${user?.errors?.hasFieldErrors('lastName')}">
					<tr>
						<td></td>
						<td colspan="2" class="csc-error-message"><g:renderErrors bean="${user}" field="lastName" /></td>
					</tr>
				</g:if>
			</g:if>
			<tr class="prop">
				<td valign="top" class="name">
					<label for="displayName">
						<g:message code="org.commonsemantics.grails.users.model.field.displayName" default="Display name"/>*
					</label>
				</td>
				<td valign="top" class="value">
					<g:textField name="displayName" style="width: 276px;"
						value="${user?.displayName}"  class="${hasErrors(bean: user, field: 'displayName', 'csc-field-error')}"/>
				</td>
				<td valign="top" class="caption">
					(max 255 <g:message code="org.commonsemantics.grails.general.chars" default="chars"/>)
		        </td>
		        <g:if test="${user?.errors?.hasFieldErrors('displayName')}">
					<tr>
						<td></td>
						<td colspan="2" class="csc-error-message"><g:renderErrors bean="${user}" field="displayName" /></td>
					</tr>
				</g:if>
			</tr>
			
			<%-- 
			<g:render plugin="cs-user" template="/users/uPropertyEntry" model="[
				messageCode:'org.commonsemantics.grails.users.model.field.displayName',
				messageDefault:'Display name',
				mandatory:UserUtils.isFieldMandatory(grailsApplication, 'displayName'),
				variable: 'displayName',
				value: user?.displayName,
				caption: '(max 255 ' +  g.message(code: 'org.commonsemantics.grails.general.chars',default:'chars') + ')'
			]" />
			--%>
			
			<tr class="prop">
				<td valign="top" class="name">
					<label for="email">Email*</label>
				</td>
				<td valign="top" class="value">
					<g:textField name="email" style="width: 276px;"
						value="${user?.email}"  class="${hasErrors(bean: user, field: 'email', 'csc-field-error')}"/>
				</td>
				<td valign="top" class="caption">
					(valid email)
		        </td>
		        <g:if test="${user?.errors?.hasFieldErrors('email')}">
					<tr>
						<td></td>
						<td colspan="2" class="csc-error-message"><g:renderErrors bean="${user}" field="email" /></td>
					</tr>
				</g:if>
			</tr>
			<g:if test="${grailsApplication.config.org.commonsemantics.grails.users.model.field.affiliation!='hide'}">
				<tr class="prop">
					<td valign="top" class="name">
						<label for="affiliation">
							<g:message code="org.commonsemantics.grails.users.model.field.affiliation" default="Affiliation"/>
						</label>
					</td>
					<td valign="top" class="value">
						<g:textField name="affiliation" style="width: 276px;"
							value="${user?.affiliation}"  class="${hasErrors(bean: user, field: 'affiliation', 'csc-field-error')}"/>
					</td>
					<td valign="top" class="caption">
						(max 255 <g:message code="org.commonsemantics.grails.general.chars" default="chars"/>)
			        </td>
			        <g:if test="${user?.errors?.hasFieldErrors('affiliation')}">
						<tr>
							<td></td>
							<td colspan="2" class="csc-error-message"><g:renderErrors bean="${user}" field="affiliation" /></td>
						</tr>
					</g:if>
				</tr>
			</g:if>
			<g:if test="${grailsApplication.config.org.commonsemantics.grails.users.model.field.country!='hide'}">
				<tr class="prop">
					<td valign="top" class="name">
						<label for="country">
							<g:message code="org.commonsemantics.grails.users.model.field.country" default="Country"/>
						</label>
					</td>
					<td valign="top" class="value">
						<%-- 
						<g:textField name="country" style="width: 240px;"
							value="${user?.country}"  class="${hasErrors(bean: user, field: 'displayName', 'fieldError')}"/>
						--%>
						<%-- http://grails.org/doc/latest/ref/Tags/countrySelect.html --%>
						<g:countrySelect name="country" value="${user?.country}"
                 			noSelection="['':'-Choose your country-']" default="usa"/>
                 		
                 		<%--  create select from a list of ISO3166_3 country codes --%>
						<%-- <g:countrySelect name="country" from="['gbr', 'usa', 'deu', 'ita']" value="${user?.country}"/> --%>
					</td>
					<td valign="top" class="caption">
			        </td>
				</tr>
			</g:if>
		</tbody>
	</table>
</g:elseif>
<g:else>
	<g:render plugin="cs-commons" template="/lenses/error" model="['message':'CsUser._userProfileFields: user object cannot be null.']"/>
</g:else>

