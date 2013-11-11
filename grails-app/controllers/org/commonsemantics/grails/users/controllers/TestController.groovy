package org.commonsemantics.grails.users.controllers

import org.commonsemantics.grails.users.model.User

class TestController {

	def userUnknown = {
		render (view:'user-display-lens', model:[label:'CsUser.01', description:'User\'s display lens with no user definition']);
	}
	
	def userKnown = {
		render (view:'user-display-lens', model:[label:'CsUser.02', description:'User\'s display lens with user definition', user:User.list()[0]]);
	}
}
