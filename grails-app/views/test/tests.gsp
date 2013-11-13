<!doctype html>
<html>
	<head>
		<meta name="layout" content="commonsemantics"/>
	</head>
	<body>
		<div style="padding-left:10px;">
		<h1>${grailsApplication.metadata['app.name']} UI Components Tests list</h1>
		<blockquote>
			CsUser.01 <g:link plugin="cs-users" controller="test" action="testUserDisplayLensNoUser">User's display lens with no user definition</g:link><br/>
			CsUser.02 <g:link plugin="cs-users" controller="test" action="testUserDisplayLens">User's display lens with user definition</g:link><br/>
			CsUser.03 <g:link plugin="cs-users" controller="test" action="testUserProfileFieldsLensNoUser">User's profile edit lens with no user definition</g:link><br/>
			CsUser.04 <g:link plugin="cs-users" controller="test" action="testUserProfileFieldsLens">User's profile edit lens with user definition</g:link><br/>
			CsUser.05 <g:link plugin="cs-users" controller="test" action="testUserAccountFieldsLensNoUser">User's account edit lens with no user definition</g:link><br/>
			CsUser.06 <g:link plugin="cs-users" controller="test" action="testUserAccountFieldsLens">User's account edit lens with user definition</g:link><br/>
			CsUser.07 <g:link plugin="cs-users" controller="test" action="testUserEditLensNoUser">User's edit lens with no user definition</g:link><br/>
			CsUser.08 <g:link plugin="cs-users" controller="test" action="testUserEditLens">User's edit lens with user definition</g:link><br/>
		</blockquote>
		</div>
	</body>
</html>
