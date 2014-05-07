<!DOCTYPE html>
<%-- by Paolo Ciccarese --%>

<html>
  <head>
    <meta name="layout" content="commonsemantics-tests" /> 
    <title>:: Users Roles</title> 
  </head>

  <body>
	<div class="csc-main">
		<h1>${grailsApplication.metadata['app.name']}.${label} ${description}</h1>
		
		<div class="csc-lens-container">
			<g:render template="/users/listRoles" />
		</div>
	</div>
  </body>
</html>