package org.commonsemantics.grails.users.controllers

import org.commonsemantics.grails.users.model.Role
import org.commonsemantics.grails.users.model.User
import org.commonsemantics.grails.users.utils.UserUtils

class TestController {

	static defaultAction = "index"
	
	def index = {
		render (view:'tests')
	}
	
	def testUserDisplayLensNoUser = {
		render (view:'user-show-lens', model:[label:'CsUser.01', description:'User\'s display lens with no user definition']);
	}
	
	def testUserDisplayLens = {
		render (view:'user-show-lens', model:[label:'CsUser.02', description:'User\'s display lens with user definition', 
			roles: Role.list(), userRoles:UserUtils.getUserRoles(User.list()[0]), user:User.list()[0]]);
	}
	
	def testUserProfileFieldsLensNoUser =  {
		render (view:'user-profile-edit-lens', model:[label:'CsUser.03', description:'User\'s profile edit lens with no user definition']);
	}
	
	def testUserProfileFieldsLens = {
		render (view:'user-profile-edit-lens', model:[label:'CsUser.04', description:'User\'s profile edit lens with user definition', user:User.list()[0]]);
	}
	
	def testUserAccountFieldsLensNoUser =  {
		render (view:'user-account-edit-lens', model:[label:'CsUser.05', description:'User\'s account edit lens with no user definition']);
	}
	
	def testUserAccountFieldsLens =  {
		render (view:'user-account-edit-lens', model:[label:'CsUser.06', description:'User\'s account edit lens with user definition', 
			roles: Role.list(), userRoles:UserUtils.getUserRoles(User.list()[0]), user:User.list()[0]]);
	}
	
	def testUserEditLensNoUser =  {
		render (view:'user-edit-lens', model:[label:'CsUser.07', description:'User\'s edit lens with no user definition']);
	}
	
	def testUserEditLens =  {
		render (view:'user-edit-lens', model:[label:'CsUser.08', description:'User\'s edit lens with user definition',
			roles: Role.list(), userRoles:UserUtils.getUserRoles(User.list()[0]), user:User.list()[0], msgError: 'error', msgWarning: 'warning']);
	}
}
