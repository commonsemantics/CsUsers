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
			<g:render plugin="cs-users" template="/users/userAccountFields" />
		</div>
	</body>
</html>