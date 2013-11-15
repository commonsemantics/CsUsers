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
		<g:textField name="${variable}" style="width: 276px;"
			value="${user[variable]}"  class="${hasErrors(bean: user, field: variable, 'csc-field-error')}"/>
	</td>
	<td valign="top" class="caption">
		${caption}	
    </td>
</tr>
<g:if test="${user?.errors.hasFieldErrors(variable)}">
	<tr>
		<td></td>
		<td colspan="2" class="csc-error-message"><g:renderErrors bean="${user}" field="${variable}" /></td>
	</tr>
</g:if>