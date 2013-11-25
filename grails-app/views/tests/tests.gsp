<!doctype html>
<html>
	<head>
		<meta name="layout" content="commonsemantics"/>
	</head>
	<body>
		<div style="padding-left:10px;">
			<h1>${grailsApplication.metadata['app.name']} UI Components Tests list</h1>
			<table cellpadding="2">
				<tr>
					<th>Test Id</th>
					<th>Description</th>
					<th>Version 0.1</th>
				</tr>
				
				<g:render plugin="cs-agents" template="/tests/testEntry" 
					model="[
						action:'testShowPerson', 
						plugin:'cs-agents',
						testId: '00', 
						testDescription: 'Person show',
						passed: 'true']" />
						
				<g:render plugin="cs-agents" template="/tests/testEntry" 
					model="[
						action:'testShowPersonLensNoPerson', 
						plugin:'cs-agents',
						testId: '01', 
						testDescription: 'Person show (no person defined)',
						passed: 'true']" />
						
				<g:render plugin="cs-agents" template="/tests/testEntry" 
					model="[
						action:'testEditPerson', 
						plugin:'cs-agents',
						testId: '02', 
						testDescription: 'Person edit',
						passed: 'true']" />	
						
				<g:render plugin="cs-agents" template="/tests/testEntry" 
					model="[
						action:'testCreatePerson',
						plugin:'cs-agents',
						testId: '03', 
						testDescription: 'Person create',
						passed: 'true']" />	
						
				<g:render plugin="cs-agents" template="/tests/testEntry" 
					model="[
						action:'testListPersons', 
						plugin:'cs-agents',
						testId: '04', 
						testDescription: 'Persons list',
						passed: 'true']" />		

				<tr>
					<th>Test Id</th>
					<th>Description</th>
					<th>Version 0.1</th>
				</tr>	
			
				<g:render plugin="cs-agents" template="/tests/testEntry" 
					model="[
						action:'testShowSoftware', 
						plugin:'cs-agents',
						testId: '10', 
						testDescription: 'Software show',
						passed: 'true']" />
						
				<g:render plugin="cs-agents" template="/tests/testEntry" 
					model="[
						action:'testShowSoftwareLensNoSoftware', 
						plugin:'cs-agents',
						testId: '11', 
						testDescription: 'Software show (no software defined)',
						passed: 'true']" />		
						
				<g:render plugin="cs-agents" template="/tests/testEntry" 
					model="[
						action:'testEditSoftware', 
						plugin:'cs-agents',
						testId: '12', 
						testDescription: 'Software edit',
						passed: 'true']" />
						
				<g:render plugin="cs-agents" template="/tests/testEntry" 
					model="[
						action:'testCreateSoftware', 
						plugin:'cs-agents',
						testId: '13', 
						testDescription: 'Software create',
						passed: 'true']" />
						
				<g:render plugin="cs-agents" template="/tests/testEntry" 
					model="[
						action:'testListSoftware', 
						plugin:'cs-agents',
						testId: '14', 
						testDescription: 'Software list',
						passed: 'true']" />	

				<tr>
					<th>Test Id</th>
					<th>Description</th>
					<th>Version 0.1</th>
				</tr>
				
				<g:render plugin="cs-users" template="/tests/testEntry" 
					model="[
						action:'testShowUserPerson',
						plugin:'cs-users', 
						testId: '20', 
						testDescription: 'User Person show lens',
						passed: 'true']" />
						
				<g:render plugin="cs-users" template="/tests/testEntry" 
					model="[
						action:'testEditUserPerson',
						plugin:'cs-users', 
						testId: '21', 
						testDescription: 'User Person edit lens',
						passed: 'true']" />
						
				<g:render plugin="cs-users" template="/tests/testEntry" 
					model="[
						action:'testCreateUserPerson',
						plugin:'cs-users', 
						testId: '22', 
						testDescription: 'User Person create lens',
						passed: 'false']" />
						
				<g:render plugin="cs-users" template="/tests/testEntry" 
					model="[
						action:'testListUserPersons',
						plugin:'cs-users', 
						testId: '23', 
						testDescription: 'User Persons list lens',
						passed: 'true']" />
						
				<tr>
					<th>Test Id</th>
					<th>Description</th>
					<th>Version 0.1</th>
				</tr>	
				
				<g:render plugin="cs-users" template="/tests/testEntry" 
					model="[
						action:'testShowUser',
						plugin:'cs-users', 
						testId: '30', 
						testDescription: 'User show lens',
						passed: 'true']" />
						
				<g:render plugin="cs-users" template="/tests/testEntry" 
					model="[
						action:'testEditUser',
						plugin:'cs-users', 
						testId: '31', 
						testDescription: 'User edit lens',
						passed: 'true']" />
						
				<g:render plugin="cs-users" template="/tests/testEntry" 
					model="[
						action:'testCreateUser',
						plugin:'cs-users', 
						testId: '32', 
						testDescription: 'User create lens',
						passed: 'true']" />		
						
				<g:render plugin="cs-users" template="/tests/testEntry" 
					model="[
						action:'testListUsers',
						plugin:'cs-users', 
						testId: '33', 
						testDescription: 'Users list lens',
						passed: 'true']" />			
			</table>	
		</div>
	</body>
</html>
