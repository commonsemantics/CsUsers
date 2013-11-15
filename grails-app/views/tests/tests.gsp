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
				
				<g:render plugin="cs-user" template="/tests/testEntry" 
					model="[
						action:'testUserDisplayLensNoUser', 
						testId: '01', 
						testDescription: 'User\'s display lens with no user definition',
						passed: 'true']" />
						
				<g:render plugin="cs-user" template="/tests/testEntry" 
					model="[
						action:'testUserDisplayLens', 
						testId: '02', 
						testDescription: 'User\'s display lens',
						passed: 'true']" />
						
				<g:render plugin="cs-user" template="/tests/testEntry" 
					model="[
						action:'testUserProfileFieldsLensNoUser', 
						testId: '03', 
						testDescription: 'User\'s profile edit lens with no user definition',
						passed: 'true']" />
						
				<g:render plugin="cs-user" template="/tests/testEntry" 
					model="[
						action:'testUserProfileFieldsLens', 
						testId: '04', 
						testDescription: 'User\'s profile edit lens',
						passed: 'true']" />
						
				<g:render plugin="cs-user" template="/tests/testEntry" 
					model="[
						action:'testUserAccountFieldsLensNoUser', 
						testId: '05', 
						testDescription: 'User\'s account edit lens with no user definition',
						passed: 'true']" />

				<g:render plugin="cs-user" template="/tests/testEntry" 
					model="[
						action:'testUserAccountFieldsLens', 
						testId: '06', 
						testDescription: 'User\'s account edit lens',
						passed: 'true']" />
						
				<g:render plugin="cs-user" template="/tests/testEntry" 
					model="[
						action:'testUserEditLensNoUser', 
						testId: '07', 
						testDescription: 'User\'s edit lens with no user definition',
						passed: 'true']" />
				
				<g:render plugin="cs-user" template="/tests/testEntry" 
					model="[
						action:'testUserEditLens', 
						testId: '08', 
						testDescription: 'User\'s edit lens',
						passed: 'true']" />
						
				<g:render plugin="cs-user" template="/tests/testEntry" 
					model="[
						action:'testUserEditLensWithError', 
						testId: '09', 
						testDescription: 'User\'s edit lens with error',
						passed: 'true']" />
						
				<g:render plugin="cs-user" template="/tests/testEntry" 
					model="[
						action:'testUserEditLensWithLongError', 
						testId: '10', 
						testDescription: 'User\'s edit lens with longerror',
						passed: 'true']" />

				<g:render plugin="cs-user" template="/tests/testEntry" 
					model="[
						action:'testUserEditLensWithWarning', 
						testId: '11', 
						testDescription: 'User\'s edit lens with warning',
						passed: 'true']" />
						
				<g:render plugin="cs-user" template="/tests/testEntry" 
					model="[
						action:'testUserEditLensWithLongWarning', 
						testId: '12', 
						testDescription: 'User\'s edit lens with long warning',
						passed: 'true']" />
						
				<g:render plugin="cs-user" template="/tests/testEntry" 
					model="[
						action:'testUserEditLensWithFieldValueError', 
						testId: '13', 
						testDescription: 'User\'s edit lens with field value error',
						passed: 'true']" />
						
				<g:render plugin="cs-user" template="/tests/testEntry" 
					model="[
						action:'testUserCreationLens', 
						testId: '14', 
						testDescription: 'User\'s creation lens',
						passed: 'false']" />
			</table>	
		</div>
	</body>
</html>
