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
			
			<h3>User Display Lens (lang=<%=RequestContextUtils.getLocale(request).language %>)*</h3>
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
				<g:hiddenField name="testId" value="${label}" /> 
				<g:hiddenField name="testDescription" value="After ${description}" /> 
			
				<div class="csc-lens-container">
					<g:hiddenField name="id" value="${user.id}" /> 
					<g:render plugin="cs-users" template="/users/userShow" />
				</div>
				<br/>
				<div class="buttons">
					<span class="button">
						<g:actionSubmit class="save" action="testEditUser" value="${message(code: 'org.commonsemantics.grails.users.profile.submit', default: 'Edit User')}" />
					</span>
					<span class="button">
						<g:actionSubmit class="save" action="testListUsers" value="${message(code: 'org.commonsemantics.grails.users.profile.submit', default: 'List Users')}" />
					</span>
				</div>
			</g:form>
		</div>
	</body>
</html>