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
						passed: 'false']" />
						
				<g:render plugin="cs-users" template="/tests/testEntry" 
					model="[
						action:'testEditUserPerson',
						plugin:'cs-users', 
						testId: '21', 
						testDescription: 'User Person edit lens',
						passed: 'false']" />
						
				<tr>
					<th>Test Id</th>
					<th>Description</th>
					<th>Version 0.1</th>
				</tr>	
						
				<%-- 
				
						
			
						

						
					
						
				
				<g:render plugin="cs-user" template="/tests/testEntry" 
					model="[
						action:'testUserPersonEditLens', 
						testId: '00', 
						testDescription: 'Person edit lens',
						passed: 'false']" />
				
				
				
				
				<g:render plugin="cs-user" template="/tests/testEntry" 
					model="[
						action:'testUserDisplayLensNoUser', 
						testId: '01', 
						testDescription: 'User\'s display lens with no user definition',
						passed: 'false']" />
						
				<g:render plugin="cs-user" template="/tests/testEntry" 
					model="[
						action:'testUserDisplayLens', 
						testId: '02', 
						testDescription: 'User\'s display lens',
						passed: 'false']" />
						
				<g:render plugin="cs-user" template="/tests/testEntry" 
					model="[
						action:'testUserProfileFieldsLensNoUser', 
						testId: '03', 
						testDescription: 'User\'s profile edit lens with no user definition',
						passed: 'false']" />
						
				<g:render plugin="cs-user" template="/tests/testEntry" 
					model="[
						action:'testUserProfileFieldsLens', 
						testId: '04', 
						testDescription: 'User\'s profile edit lens',
						passed: 'false']" />
						
				<g:render plugin="cs-user" template="/tests/testEntry" 
					model="[
						action:'testUserAccountFieldsLensNoUser', 
						testId: '05', 
						testDescription: 'User\'s account edit lens with no user definition',
						passed: 'false']" />

				<g:render plugin="cs-user" template="/tests/testEntry" 
					model="[
						action:'testUserAccountFieldsLens', 
						testId: '06', 
						testDescription: 'User\'s account edit lens',
						passed: 'false']" />
						
				<g:render plugin="cs-user" template="/tests/testEntry" 
					model="[
						action:'testUserEditLensNoUser', 
						testId: '07', 
						testDescription: 'User\'s edit lens with no user definition',
						passed: 'false']" />
				
				<g:render plugin="cs-user" template="/tests/testEntry" 
					model="[
						action:'testUserEditLens', 
						testId: '08', 
						testDescription: 'User\'s edit lens',
						passed: 'false']" />
						
				<g:render plugin="cs-user" template="/tests/testEntry" 
					model="[
						action:'testUserEditLensWithError', 
						testId: '09', 
						testDescription: 'User\'s edit lens with error',
						passed: 'false']" />
						
				<g:render plugin="cs-user" template="/tests/testEntry" 
					model="[
						action:'testUserEditLensWithLongError', 
						testId: '10', 
						testDescription: 'User\'s edit lens with longerror',
						passed: 'false']" />

				<g:render plugin="cs-user" template="/tests/testEntry" 
					model="[
						action:'testUserEditLensWithWarning', 
						testId: '11', 
						testDescription: 'User\'s edit lens with warning',
						passed: 'false']" />
						
				<g:render plugin="cs-user" template="/tests/testEntry" 
					model="[
						action:'testUserEditLensWithLongWarning', 
						testId: '12', 
						testDescription: 'User\'s edit lens with long warning',
						passed: 'false']" />
						
				<g:render plugin="cs-user" template="/tests/testEntry" 
					model="[
						action:'testUserEditLensWithFieldValueError', 
						testId: '13', 
						testDescription: 'User\'s edit lens with field value error',
						passed: 'false']" />
						
				<g:render plugin="cs-user" template="/tests/testEntry" 
					model="[
						action:'testUserCreationLens', 
						testId: '14', 
						testDescription: 'User\'s creation lens',
						passed: 'false']" />
						
				<g:render plugin="cs-user" template="/tests/testEntry" 
					model="[
						action:'testUsersList', 
						testId: '15', 
						testDescription: 'List of users',
						passed: 'false']" />
				--%>					
			</table>	
		</div>
	</body>
</html>
