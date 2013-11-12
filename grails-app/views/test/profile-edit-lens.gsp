<!doctype html>
<html>
	<head>
		<meta name="layout" content="commonsemantics"/>
		<title>${label}</title>
	</head>
	<body>
		<div style="padding-left:10px;">
			<h1>${label} ${description}</h1>
			<g:render plugin="cs-users" template="/users/userProfileFields" />
		</div>
	</body>
</html>