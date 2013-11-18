<%@ page import="org.springframework.web.servlet.support.RequestContextUtils" %>

<!doctype html>
<html>
	<head>
		<meta name="layout" content="commonsemantics-tests"/>
		<title>${grailsApplication.metadata['app.name']}.${label}</title>
	</head>
	<body>
		<div style="padding-left:10px;">
			<h1>${grailsApplication.metadata['app.name']}.${label} ${description}</h1>
			
			<g:render plugin="cs-agents" template="/tests/personConfigurationDetails" />
			
			<h3>${description} (lang=<%=RequestContextUtils.getLocale(request).language %>)*</h3>
			<p>
			* to change the Locale add ?lang=language to the URL of this page
			</p>
			<br/>
			Person id: ${person?.id}
			<g:form method="post" >
				<div class="csc-lens-container">	
					<g:hiddenField name="id" value="${person?.id}" /> 
					<g:render plugin="cs-agents" template="/agents/personEdit" />
				</div>
				<br/>
				<div class="buttons">
					<span class="button">
						<g:actionSubmit class="save" action="updateUserPerson" value="${message(code: 'org.commonsemantics.grails.users.profile.submit', default: 'Update Profile')}" />
					</span>
					<span class="button">
						<g:actionSubmit class="cancel" action="showUser" value="${message(code: 'org.commonsemantics.grails.general.cancel', default: 'Cancel')}" />
					</span>
				</div>
			</g:form>
		</div>
	</body>
</html>