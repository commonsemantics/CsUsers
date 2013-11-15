<%@ page import="org.springframework.web.servlet.support.RequestContextUtils" %>

<!doctype html>
<html>
	<head>
		<meta name="layout" content="commonsemantics-tests"/>
		<title>${label}</title>
		<style>
			.prop .value {
			    text-align: left;
			    width: 40%;
			}
		</style>
	</head>
	<body>
		<div style="padding-left:10px;">
			<h1>${label} ${description}</h1>
			
			<h3>Edit Account Lens (lang=<%=RequestContextUtils.getLocale(request).language %>)*</h3>
			<p>
			* to change the Locale add ?lang=language to the URL of this page
			</p>
			<br/>
			<g:form method="post" >
				<div class="csc-lens-container">
					<g:hiddenField name="id" value="${user?.id}" /> 
					<g:hiddenField name="username" value="${user?.username}" /> 
					<g:render plugin="cs-users" template="/users/uAccountFields" />
				</div>	
				<br>
				<div class="buttons">
					<span class="button">
						<g:actionSubmit class="save" action="updateUserAccount" value="${message(code: 'org.commonsemantics.grails.users.profile.submit', default: 'Update Profile')}" />
					</span>
					<span class="button">
						<g:actionSubmit class="cancel" action="showUser" value="${message(code: 'org.commonsemantics.grails.general.cancel', default: 'Cancel')}" />
					</span>
				</div>	
			</g:form>	
		</div>
	</body>
</html>