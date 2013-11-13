<%@ page import="org.springframework.web.servlet.support.RequestContextUtils" %>

<!doctype html>
<html>
	<head>
		<meta name="layout" content="commonsemantics"/>
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
			<div class="csc-lens-container">
				<g:render plugin="cs-users" template="/users/uAccountFields" />
			</div>
		</div>
	</body>
</html>