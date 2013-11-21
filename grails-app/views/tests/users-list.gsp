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
			
			<h3>${description} (lang=<%=RequestContextUtils.getLocale(request).language %>)*</h3>
			<p>
			* to change the Locale add ?lang=language to the URL of this page
			</p>
			<br/>
			<div class="csc-lens-container">
				<g:render plugin="cs-users" template="/users/usersList" />
			</div>
			
		</div>
	</body>
</html>