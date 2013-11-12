<%@ page import="org.commonsemantics.grails.users.utils.UserUtils" %>
<%-- 
By Dr. Paolo Ciccarese <paolo.ciccarese@gmail.com>

Parameters list
 1) user | instance of GroupCreateCommand
Stylesheet
 1) fieldError | background and font color in erroneous text fields
--%>
<div class="sectioncontainer">
	<g:if test="${user!=null}">
		<g:render plugin="cs-user" template="/users/userProfileFields" />
		<g:render plugin="cs-user" template="/users/userAccountFields" />
	</g:if>
	<g:else>
		<g:render plugin="cs-commons" template="/lenses/error" model="['message':'CsUser._userEdit: user object cannot be null.']"/>
	</g:else>
</div>