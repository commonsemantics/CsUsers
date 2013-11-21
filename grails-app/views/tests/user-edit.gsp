<%@ page import="org.springframework.web.servlet.support.RequestContextUtils" %>

<!doctype html>
<html>
	<head>
		<meta name="layout" content="commonsemantics-tests"/>
		<title>${grailsApplication.metadata['app.name']}.${label}</title>
	</head>
	<body>
		<div class="csc-main">
			<h1>${grailsApplication.metadata['app.name']}.${label} ${description}</h1>
			
			<g:render plugin="cs-users" template="/tests/userConfigurationDetails" />
			
			<h2>User Edit Lens (lang=<%=RequestContextUtils.getLocale(request).language %>)*</h3>
			<p>
			* to change the Locale add ?lang=language to the URL of this page
			</p>
			<br/>
			User id: ${user.id}
				<g:if test="${user.hasProperty('dateCreated')}">dateCreated=<%=user.dateCreated %></g:if>
				<g:if test="${user.hasProperty('lastUpdated')}">lastUpdated=<%=user.lastUpdated %></g:if>
			<br/>
			Person id: ${user.person.id}
				<g:if test="${user.person.hasProperty('dateCreated')}">dateCreated=<%=user.person.dateCreated %></g:if>
				<g:if test="${user.person.hasProperty('lastUpdated')}">lastUpdated=<%=user.person.lastUpdated %></g:if>
			<br/>
			<g:form method="post" >
				<div class="csc-lens-container">
					<g:render plugin="cs-users" template="/users/userEdit" />
				</div>
				<br/>
				<tr>
					<td valign="top" colspan="2" >
						<div class="buttons">
							<span class="button">
								<g:actionSubmit class="save" action="testUpdateUser" value="${message(code: 'org.commonsemantics.grails.users.profile.submit', default: 'Update Profile')}" />
							</span>
							<span class="button">
								<g:actionSubmit class="cancel" action="testShowUser" value="${message(code: 'org.commonsemantics.grails.general.cancel', default: 'Cancel')}" />
							</span>
						</div>
					</td>
				</tr>
			</g:form>
		</div>
	</body>
</html>