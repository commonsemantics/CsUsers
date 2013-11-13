package org.commonsemantics.grails.users.controllers

import org.commonsemantics.grails.users.model.Role
import org.commonsemantics.grails.users.model.User
import org.commonsemantics.grails.users.model.UserRole

class TestController {

	static defaultAction = "index"
	
	def index = {
		render (view:'tests')
	}
	
	def userUnknown = {
		render (view:'user-show-lens', model:[label:'CsUser.01', description:'User\'s display lens with no user definition']);
	}
	
	def userKnown = {
		render (view:'user-show-lens', model:[label:'CsUser.02', description:'User\'s display lens with user definition', 
			roles: Role.list(), userRoles:getUserRoles(User.list()[0]), user:User.list()[0]]);
	}
	
	def userFieldsUnknown =  {
		render (view:'user-profile-edit-lens', model:[label:'CsUser.03', description:'User\'s edit lens with no user definition']);
	}
	
	def userFieldsKnown =  {
		render (view:'user-profile-edit-lens', model:[label:'CsUser.04', description:'User\'s edit lens with user definition', user:User.list()[0]]);
	}
	
	def accountFieldsUnknown =  {
		render (view:'user-account-edit-lens', model:[label:'CsUser.05', description:'User\'s account edit lens with no user definition']);
	}
	
	def accountFieldsKnown =  {
		render (view:'user-account-edit-lens', model:[label:'CsUser.06', description:'User\'s account edit lens with user definition', 
			roles: Role.list(), userRoles:getUserRoles(User.list()[0]), user:User.list()[0]]);
	}
	
	def userEditUnknown =  {
		render (view:'user-edit-lens', model:[label:'CsUser.07', description:'User\'s edit lens with no user definition']);
	}
	
	def userEditKnown =  {
		render (view:'user-edit-lens', model:[label:'CsUser.08', description:'User\'s edit lens with user definition',
			roles: Role.list(), userRoles:getUserRoles(User.list()[0]), user:User.list()[0], msgError: 'error', msgWarning: 'warning']);
	}
	
	def getUserRoles(def user) {
		def userRoles = []
		def ur = UserRole.findAllByUser(user)
		ur.each { userRoles.add(it.role)}
		return userRoles
	}

}
