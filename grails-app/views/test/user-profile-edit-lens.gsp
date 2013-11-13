<!doctype html>
<html>
	<head>
		<meta name="layout" content="commonsemantics"/>
		<title>${label}</title>
	</head>
	<body>
		<div style="padding-left:10px;">
			<h1>${label} ${description}</h1>
			
			<g:render plugin="cs-users" template="/test/configurationDetails" />
			
			<h3>Edit Profile Lens</h3>
			<div class="csc-lens-container">
				<g:form method="post" >
					<g:hiddenField name="id" value="${user?.id}" /> 
					<g:hiddenField name="username" value="${user?.username}" />
					<g:render plugin="cs-users" template="/users/uProfileFields" />
					
					<div class="buttons">
						<span class="button">
							<g:actionSubmit class="save" action="updateUser" value="${message(code: 'default.button.edit.account.label', default: 'Update Profile')}" />
						</span>
						<span class="button">
							<g:actionSubmit class="cancel" action="showUser" value="${message(code: 'default.button.edit.account.label', default: 'Cancel')}" />
						</span>
					</div>
				</g:form>
			</div>
		</div>
	</body>
</html>