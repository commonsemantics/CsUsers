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
			
			<h2>User Creation Lens</h2>
			<g:form method="post" >
				<div class="csc-lens-container">
					<g:render plugin="cs-users" template="/users/userCreate" />
				</div>
				<br/>
				<tr>
					<td valign="top" colspan="2" >
						<div class="buttons">
							<span class="button">
								<g:actionSubmit class="save" action="testSaveUser" value="${message(code: 'org.commonsemantics.grails.users.profile.create', default: 'Create Profile')}" />
							</span>
							<span class="button">
								<g:actionSubmit class="cancel" action="showUser" value="${message(code: 'org.commonsemantics.grails.general.cancel', default: 'Cancel')}" />
							</span>
						</div>
					</td>
				</tr>
			</g:form>
		</div>
	</body>
</html>