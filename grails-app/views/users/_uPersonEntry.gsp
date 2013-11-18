<%-- 
messageCode, messageDefault, mandatory, variable, value, caption
--%>

<tr class="prop">
	<td valign="top" class="name" width="160px">
		<label for="${variable}">
			<g:message code="${messageCode}" default="${messageDefault}"/><g:if test="${mandatory==true}">*</g:if>
		</label>
	</td>
	<td valign="top" class="value">
		<g:if test="${person!=null}">
			<g:textField name="${variable}" style="width: 276px;"
				value="${value}"  class="${hasErrors(bean: person, field: variable, 'csc-field-error')}"/>
		</g:if>
		<%-- 
		<g:else>
			<g:textField name="${variable}" style="width: 276px;"
				class="${hasErrors(bean: person, field: variable, 'csc-field-error')}"/>
		</g:else>
		--%>
	</td>
	<td valign="top" class="caption">
		${caption}	
    </td>
</tr>
<g:if test="${person?.errors?.hasFieldErrors(variable)}">
	<tr>
		<td></td>
		<td colspan="2" class="csc-error-message"><g:renderErrors bean="${person}" field="${variable}" /></td>
	</tr>
</g:if>