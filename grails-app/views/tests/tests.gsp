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
					<th>Test #</th>
					<th>Description</th>
					<th>Version 0.1</th>
				</tr>
				<tr>
					<td>CsUser.01</td>
					<td><g:link plugin="cs-users" controller="tests" action="testUserDisplayLensNoUser">User's display lens with no user definition</g:link></td>
					<td align="center"><img src="${resource(dir:'images/accept', file:'Accept_16.png', plugin:'cs-commons')}"/></td>
				</tr>
				<tr>
					<td>CsUser.02</td>
					<td><g:link plugin="cs-users" controller="tests" action="testUserDisplayLens">User's display lens</g:link></td>
					<td align="center"><img src="${resource(dir:'images/accept', file:'Accept_16.png', plugin:'cs-commons')}"/></td>
				</tr>
				<tr>
					<td>CsUser.03</td>
					<td><g:link plugin="cs-users" controller="tests" action="testUserProfileFieldsLensNoUser">User's profile edit lens with no user definition</g:link></td>
					<td align="center"><img src="${resource(dir:'images/accept', file:'Accept_16.png', plugin:'cs-commons')}"/></td>
				</tr>
				<tr>
					<td>CsUser.04</td>
					<td><g:link plugin="cs-users" controller="tests" action="testUserProfileFieldsLens">User's profile edit lens</g:link></td>
					<td align="center"><img src="${resource(dir:'images/accept', file:'Accept_16.png', plugin:'cs-commons')}"/></td>
				</tr>
				<tr>
					<td>CsUser.05</td>
					<td><g:link plugin="cs-users" controller="tests" action="testUserAccountFieldsLensNoUser">User's account edit lens with no user definition</g:link></td>
					<td align="center"><img src="${resource(dir:'images/accept', file:'Accept_16.png', plugin:'cs-commons')}"/></td>
				</tr>
				<tr>
					<td>CsUser.06</td>
					<td><g:link plugin="cs-users" controller="tests" action="testUserAccountFieldsLens">User's account edit lens</g:link></td>
					<td align="center"><img src="${resource(dir:'images/accept', file:'Accept_16.png', plugin:'cs-commons')}"/></td>
				</tr>
				<tr>
					<td>CsUser.07</td>
					<td><g:link plugin="cs-users" controller="tests" action="testUserEditLensNoUser">User's edit lens with no user definition</g:link></td>
					<td align="center"><img src="${resource(dir:'images/accept', file:'Accept_16.png', plugin:'cs-commons')}"/></td>
				</tr>
				<tr>
					<td>CsUser.08</td>
					<td><g:link plugin="cs-users" controller="tests" action="testUserEditLens">User's edit lens</g:link></td>
					<td align="center"><img src="${resource(dir:'images/accept', file:'Accept_16.png', plugin:'cs-commons')}"/></td>
				</tr>
				<tr>
					<td>CsUser.09</td>
					<td><g:link plugin="cs-users" controller="tests" action="testUserEditLensWithError">User's edit lens with error</g:link></td>
					<td align="center"><img src="${resource(dir:'images/accept', file:'Accept_16.png', plugin:'cs-commons')}"/></td>
				</tr>
				<tr>
					<td>CsUser.10</td>
					<td><g:link plugin="cs-users" controller="tests" action="testUserEditLensWithLongError">User's edit lens with longerror</g:link></td>
					<td align="center"><img src="${resource(dir:'images/accept', file:'Accept_16.png', plugin:'cs-commons')}"/></td>
				</tr>
				<tr>
					<td>CsUser.11</td>
					<td><g:link plugin="cs-users" controller="tests" action="testUserEditLensWithWarning">User's edit lens with warning</g:link></td>
					<td align="center"><img src="${resource(dir:'images/accept', file:'Accept_16.png', plugin:'cs-commons')}"/></td>
				</tr>
				<tr>
					<td>CsUser.12</td>
					<td><g:link plugin="cs-users" controller="tests" action="testUserEditLensWithLongWarning">User's edit lens with long warning</g:link></td>
					<td align="center"><img src="${resource(dir:'images/accept', file:'Accept_16.png', plugin:'cs-commons')}"/></td>
				</tr>
				<tr>
					<td>CsUser.13</td>
					<td><g:link plugin="cs-users" controller="tests" action="testUserEditLensWithFieldValueError">User's edit lens with field value error</g:link></td>
					<td align="center"><img src="${resource(dir:'images/accept', file:'Accept_16.png', plugin:'cs-commons')}"/></td>
				</tr>
				<tr>
					<td>CsUser.14</td>
					<td><g:link plugin="cs-users" controller="tests" action="testUserCreationLens">User's creation lens</g:link></td>
					<td align="center"></td>
				</tr>
			</table>	
		</div>
	</body>
</html>
