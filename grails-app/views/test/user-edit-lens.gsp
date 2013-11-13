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
			
			<h3>User Edit Lens</h3>
			<div class="csc-lens-container">
				<g:render plugin="cs-users" template="/users/userEdit" />
			</div>
		</div>
	</body>
</html>