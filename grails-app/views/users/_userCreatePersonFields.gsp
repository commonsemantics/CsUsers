<%@ page import="org.commonsemantics.grails.agents.utils.AgentsUtils" %>

<%-- by Paolo Ciccarese --%>
<%-- 
Parameters list
 1) item | instance of UserCreateCommand
Stylesheet
 1) fieldError | background and font color in erroneous text fields
--%>
		<table>
		<tbody>
			<g:if test="${grailsApplication.config.org.commonsemantics.grails.persons.model.field.title!='hide'}">			
				<g:render plugin="cs-user" template="/users/uPropertyEntry" model="[
					messageCode:'org.commonsemantics.grails.persons.model.field.title',
					messageDefault:'Title',
					mandatory:AgentsUtils.isPersonFieldRequired(grailsApplication, 'title'),
					variable: 'title',
					bean: user?.person,
					value: user?.person?.title,
					caption: '(max 255 ' +  g.message(code: 'org.commonsemantics.grails.general.chars',default:'chars') + ')'
				]" />
			</g:if>
			<g:if test="${grailsApplication.config.org.commonsemantics.grails.persons.model.field.firstName!='hide'}">
				<g:render plugin="cs-user" template="/users/uPropertyEntry" model="[
					messageCode:'org.commonsemantics.grails.persons.model.field.firstName',
					messageDefault:'First name',
					mandatory:AgentsUtils.isPersonFieldRequired(grailsApplication, 'firstName'),
					variable: 'firstName',
					bean: user?.person,
					value: user?.person?.firstName,
					caption: '(max 255 ' +  g.message(code: 'org.commonsemantics.grails.general.chars',default:'chars') + ')'
				]" />
			</g:if>
			<g:if test="${grailsApplication.config.org.commonsemantics.grails.persons.model.field.middleName!='hide'}">
				<g:render plugin="cs-user" template="/users/uPropertyEntry" model="[
					messageCode:'org.commonsemantics.grails.persons.model.field.middleName',
					messageDefault:'Middle name',
					mandatory:AgentsUtils.isPersonFieldRequired(grailsApplication, 'middleName'),
					variable: 'middleName',
					bean: user?.person,
					value: user?.person?.middleName,
					caption: '(max 255 ' +  g.message(code: 'org.commonsemantics.grails.general.chars',default:'chars') + ')'
				]" />
			</g:if>
			<g:if test="${grailsApplication.config.org.commonsemantics.grails.persons.model.field.lastName!='hide'}">
				<g:render plugin="cs-user" template="/users/uPropertyEntry" model="[
					messageCode:'org.commonsemantics.grails.persons.model.field.lastName',
					messageDefault:'Last name',
					mandatory:AgentsUtils.isPersonFieldRequired(grailsApplication, 'lastName'),
					variable: 'lastName',
					bean: user?.person,
					value: user?.person?.lastName,
					caption: '(max 255 ' +  g.message(code: 'org.commonsemantics.grails.general.chars',default:'chars') + ')'
				]" />
			</g:if>
			<tr class="prop">
				<g:render plugin="cs-user" template="/users/uPropertyEntry" model="[
					messageCode:'org.commonsemantics.grails.persons.model.field.displayName',
					messageDefault:'Display name',
					mandatory:AgentsUtils.isPersonFieldRequired(grailsApplication, 'displayName'),
					variable: 'displayName',
					bean: user?.person,
					value: user?.person?.displayName,
					caption: '(max 255 ' +  g.message(code: 'org.commonsemantics.grails.general.chars',default:'chars') + ')'
				]" />
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
				<g:render plugin="cs-user" template="/users/uPropertyEntry" model="[
					messageCode:'org.commonsemantics.grails.persons.model.field.email',
					messageDefault:'Email',
					mandatory:AgentsUtils.isPersonFieldRequired(grailsApplication, 'email'),
					variable: 'email',
					bean: user?.person,
					value: user?.person?.email,
					caption: '(max 255 ' +  g.message(code: 'org.commonsemantics.grails.general.chars',default:'chars') + ')'
				]" />
			</tr>
			<g:if test="${grailsApplication.config.org.commonsemantics.grails.persons.model.field.affiliation!='hide'}">
				<g:render plugin="cs-user" template="/users/uPropertyEntry" model="[
					messageCode:'org.commonsemantics.grails.persons.model.field.affiliation',
					messageDefault:'Affiliation',
					mandatory:AgentsUtils.isPersonFieldRequired(grailsApplication, 'affiliation'),
					variable: 'affiliation',
					bean: user?.person,
					value: user?.person?.affiliation,
					caption: '(max 255 ' +  g.message(code: 'org.commonsemantics.grails.general.chars',default:'chars') + ')'
				]" />
			</g:if>
			<g:if test="${grailsApplication.config.org.commonsemantics.grails.persons.model.field.country!='hide'}">
				<tr class="prop">
					<td valign="top" class="name">
						<label for="country">
							<g:message code="org.commonsemantics.grails.users.model.field.country" default="Country"/><g:if test="${AgentsUtils.isPersonFieldRequired(grailsApplication, 'country')==true}">*</g:if>
						</label>
					</td>
					<td valign="top" class="value">
						<%-- 
						<g:textField name="country" style="width: 240px;"
							value="${user?.country}"  class="${hasErrors(bean: user, field: 'displayName', 'fieldError')}"/>
						--%>
						<%-- http://grails.org/doc/latest/ref/Tags/countrySelect.html --%>
						<g:countrySelect name="country" value="${user?.person?.country}"
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


