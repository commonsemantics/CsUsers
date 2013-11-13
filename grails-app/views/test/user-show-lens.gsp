<%@ page import="org.springframework.web.servlet.support.RequestContextUtils" %>

<!doctype html>
<html>
	<head>
		<meta name="layout" content="commonsemantics"/>
		<title>${label}</title>
	</head>
	<body>
		<div class="csc-main">
			<h1>${label} ${description}</h1>
					
			<g:render plugin="cs-users" template="/test/configurationDetails" />
			
			<h3>User Display Lens (lang=<%=RequestContextUtils.getLocale(request).language %>)*</h3>
			<p>
			* to change the Locale add ?lang=language to the URL of this page
			</p>
			<br/>
			<div class="csc-lens-container">
				<g:render plugin="cs-users" template="/users/userShow" />
			</div>
			
		</div>
	</body>
</html>