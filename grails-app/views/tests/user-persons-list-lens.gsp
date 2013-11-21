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
			
			<g:render plugin="cs-agents" template="/tests/personConfigurationDetails" />
			
			<h3>${description} (lang=<%=RequestContextUtils.getLocale(request).language %>)*</h3>
			<p>
			* to change the Locale add ?lang=language to the URL of this page
			</p>
			<br/>
			

			<g:form>
				<g:hiddenField name="testId" value="${label}" /> 
				<g:hiddenField name="testDescription" value="After ${description}" /> 
				Results per page: <g:textField name="max" style="width: 250px;" value="${max}" /><br/>
				First page: <g:textField name="offset" style="width: 250px;" value="${offset}" /><br/>
				<g:actionSubmit class="edit"  action="testListUserPersons" value="${message(code: 'default.button.edit.account.label', default: 'Refresh')}" />
			
				<br/>				
				<div class="csc-lens-container">	
					<g:render plugin="cs-agents" template="/agents/personsList"/>
				</div>
			</g:form>
		</div>
	</body>
</html>