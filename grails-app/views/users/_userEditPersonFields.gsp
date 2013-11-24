<%@ page import="org.commonsemantics.grails.users.utils.UsersUtils" %>
<%@ page import="org.commonsemantics.grails.agents.utils.AgentsUtils" %>

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
			<g:if test="${grailsApplication.config.org.commonsemantics.grails.agents.model.field.title!='hide'}">
				<g:render plugin="cs-user" template="/users/userPersonEntry" model="[
					messageCode:'org.commonsemantics.grails.agents.model.field.title',
					messageDefault:'Title',
					mandatory:AgentsUtils.isPersonFieldRequired(grailsApplication, 'title'),
					variable: 'title',
					value: user?.person?.title,
					caption: '(max 255 ' +  g.message(code: 'org.commonsemantics.grails.general.chars',default:'chars') + ')'
				]" />
			</g:if>
			<g:if test="${grailsApplication.config.org.commonsemantics.grails.agents.model.field.firstName!='hide'}">
				<g:render plugin="cs-user" template="/users/userPersonEntry" model="[
					messageCode:'org.commonsemantics.grails.agents.model.field.firstName',
					messageDefault:'First name',
					mandatory:AgentsUtils.isPersonFieldRequired(grailsApplication, 'firstName'),
					variable: 'firstName',
					value: user.person?.firstName,
					caption: '(max 255 ' +  g.message(code: 'org.commonsemantics.grails.general.chars',default:'chars') + ')'
				]" />
			</g:if>
			<g:if test="${grailsApplication.config.org.commonsemantics.grails.agents.model.field.middleName!='hide'}">
				<g:render plugin="cs-user" template="/users/userPersonEntry" model="[
					messageCode:'org.commonsemantics.grails.agents.model.field.middleName',
					messageDefault:'Middle name',
					mandatory:AgentsUtils.isPersonFieldRequired(grailsApplication, 'middleName'),
					variable: 'middleName',
					value: user?.person?.middleName,
					caption: '(max 255 ' +  g.message(code: 'org.commonsemantics.grails.general.chars',default:'chars') + ')'
				]" />
			</g:if>
			<g:if test="${grailsApplication.config.org.commonsemantics.grails.agents.model.field.lastName!='hide'}">				
				<g:render plugin="cs-user" template="/users/userPersonEntry" model="[
					messageCode:'org.commonsemantics.grails.agents.model.field.lastName',
					messageDefault:'Last name',
					mandatory:AgentsUtils.isPersonFieldRequired(grailsApplication, 'lastName'),
					variable: 'lastName',
					value: user?.person?.lastName,
					caption: '(max 255 ' +  g.message(code: 'org.commonsemantics.grails.general.chars',default:'chars') + ')'
				]" />
			</g:if>			
			<g:render plugin="cs-user" template="/users/userPersonEntry" model="[
				messageCode:'org.commonsemantics.grails.agents.model.field.displayName',
				messageDefault:'Display name',
				mandatory:AgentsUtils.isPersonFieldRequired(grailsApplication, 'displayName'),
				variable: 'displayName',
				value: user?.person?.displayName,
				caption: '(max 255 ' +  g.message(code: 'org.commonsemantics.grails.general.chars',default:'chars') + ')'
			]" />
			<g:render plugin="cs-user" template="/users/userPersonEntry" model="[
				messageCode:'org.commonsemantics.grails.agents.model.field.email',
				messageDefault:'Email',
				mandatory:AgentsUtils.isPersonFieldRequired(grailsApplication, 'email'),
				variable: 'email',
				value: user?.person?.email,
				caption: '(valid email)'
			]" />
			<g:if test="${grailsApplication.config.org.commonsemantics.grails.agents.model.field.affiliation!='hide'}">
				<g:render plugin="cs-user" template="/users/userPersonEntry" model="[
					messageCode:'org.commonsemantics.grails.agents.model.field.affiliation',
					messageDefault:'Affiliation',
					mandatory:AgentsUtils.isPersonFieldRequired(grailsApplication, 'affiliation'),
					variable: 'affiliation',
					value: user?.person?.affiliation,
					caption: '(max 255 ' +  g.message(code: 'org.commonsemantics.grails.general.chars',default:'chars') + ')'
				]" />
			</g:if>
			<%-- Custom code to accommodate the country selector --%>
			<g:if test="${grailsApplication.config.org.commonsemantics.grails.agents.model.field.country!='hide'}">
				<tr class="prop">
					<td valign="top" class="name">
						<label for="country">
							<g:message code="org.commonsemantics.grails.agents.model.field.country" default="Country"/>
							<g:if test="${AgentsUtils.isPersonFieldRequired(grailsApplication, 'country')==true}">*</g:if>
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
</g:if>
<g:else>
	<g:render plugin="cs-commons" template="/lenses/error" model="['message':'CsUser._userProfileFields: user object cannot be null.']"/>
</g:else>

