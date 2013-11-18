<tr>
	<td>${grailsApplication.metadata['app.name']}.${testId}</td>
	<td><g:link plugin="cs-users" controller="tests" action="${action}" params="[testId: testId, testDescription:testDescription]">${testDescription}</g:link></td>
	<td align="center">
		<g:if test="${passed=='true'}">
			<img src="${resource(dir:'images/accept', file:'Accept_16.png', plugin:'cs-commons')}"/>
		</g:if>
	</td>
</tr>