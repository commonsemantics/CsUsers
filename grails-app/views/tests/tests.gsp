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
						action:'testAgentsPersonShow', 
						testId: '00', 
						testDescription: 'CsAgents :: Person show lens',
						passed: 'true']" />
						
				<g:render plugin="cs-agents" template="/tests/testEntry" 
					model="[
						action:'testAgentsPersonEdit', 
						testId: '01', 
						testDescription: 'CsAgents :: Person edit lens',
						passed: 'true']" />
						
				<g:render plugin="cs-agents" template="/tests/testEntry" 
					model="[
						action:'testAgentsPersonCreate', 
						testId: '02', 
						testDescription: 'CsAgents :: Person create lens',
						passed: 'true']" />
				
				<g:render plugin="cs-agents" template="/tests/testEntry" 
					model="[
						action:'testAgentsListPersons', 
						testId: '03', 
						testDescription: 'CsAgents :: Person list',
						passed: 'true']" />
						
				<tr>
					<th>Test Id</th>
					<th>Description</th>
					<th>Version 0.1</th>
				</tr>
				
				<g:render plugin="cs-agents" template="/tests/testEntry" 
					model="[
						action:'testAgentsSoftwareShow', 
						testId: '04', 
						testDescription: 'CsAgents :: Software show lens',
						passed: 'false']" />
						
				<g:render plugin="cs-agents" template="/tests/testEntry" 
					model="[
						action:'testAgentsSoftwareEdit', 
						testId: '05', 
						testDescription: 'CsAgents :: Software edit lens',
						passed: 'false']" />

				<g:render plugin="cs-agents" template="/tests/testEntry" 
					model="[
						action:'testAgentsSoftwareCreate', 
						testId: '06', 
						testDescription: 'CsAgents :: Software create lens',
						passed: 'false']" />
				
				<g:render plugin="cs-agents" template="/tests/testEntry" 
					model="[
						action:'testAgentsListSoftware', 
						testId: '07', 
						testDescription: 'CsAgents :: Software list',
						passed: 'false']" />

				<tr>
					<th>Test Id</th>
					<th>Description</th>
					<th>Version 0.1</th>
				</tr>
				
				<g:render plugin="cs-agents" template="/tests/testEntry" 
					model="[
						action:'testPersonShow', 
						testId: '08', 
						testDescription: 'Person show lens',
						passed: 'false']" />
						
				<g:render plugin="cs-agents" template="/tests/testEntry" 
					model="[
						action:'testPersonEdit', 
						testId: '09', 
						testDescription: 'Person edit lens',
						passed: 'false']" />
						
				<g:render plugin="cs-agents" template="/tests/testEntry" 
					model="[
						action:'testPersonCreate', 
						testId: '10', 
						testDescription: 'Person create lens',
						passed: 'false']" />
				
				<g:render plugin="cs-agents" template="/tests/testEntry" 
					model="[
						action:'testListPersons', 
						testId: '11', 
						testDescription: 'Person list',
						passed: 'false']" />
						
				<tr>
					<th>Test Id</th>
					<th>Description</th>
					<th>Version 0.1</th>
				</tr>		
						
				
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
			</table>	
		</div>
	</body>
</html>
