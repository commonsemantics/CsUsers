/*
 * Copyright 2013  Common Semantics (commonsemantics.org)
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.commonsemantics.grails.users.controllers

import org.commonsemantics.grails.users.commands.UserAccountEditCommand
import org.commonsemantics.grails.users.commands.UserCreateCommand
import org.commonsemantics.grails.users.commands.UserEditCommand
import org.commonsemantics.grails.users.commands.UserProfileEditCommand
import org.commonsemantics.grails.users.model.Role
import org.commonsemantics.grails.users.model.User
import org.commonsemantics.grails.users.model.UserRole
import org.commonsemantics.grails.users.utils.DefaultUsersRoles
import org.commonsemantics.grails.users.utils.UserStatus
import org.commonsemantics.grails.users.utils.UserUtils

/**
 * @author Paolo Ciccarese <paolo.ciccarese@gmail.com>
 */
class TestController {

	static defaultAction = "index"
	
	def usersService;
	
	def index = {
		render (view:'tests')
	}
	
	def testUserDisplayLensNoUser = {
		render (view:'user-show-lens', model:[label:'CsUser.01', description:'User\'s display lens with no user definition']);
	}
	
	def testUserDisplayLens = {
		render (view:'user-show-lens', model:[label:'CsUser.02', description:'User\'s display lens', 
			roles: Role.list(), userRoles:usersService.getUserRoles(User.list()[0]), user:User.list()[0]]);
	}
	
	def testUserProfileFieldsLensNoUser =  {
		render (view:'user-profile-edit-lens', model:[label:'CsUser.03', description:'User\'s profile edit lens with no user definition']);
	}
	
	def testUserProfileFieldsLens = {
		render (view:'user-profile-edit-lens', model:[label:'CsUser.04', description:'User\'s profile edit lens', user:User.list()[0]]);
	}
	
	def testUserAccountFieldsLensNoUser =  {
		render (view:'user-account-edit-lens', model:[label:'CsUser.05', description:'User\'s account edit lens with no user definition']);
	}
	
	def testUserAccountFieldsLens =  {
		render (view:'user-account-edit-lens', model:[label:'CsUser.06', description:'User\'s account edit lens', 
			roles: Role.list(), userRoles:usersService.getUserRoles(User.list()[0]), user:User.list()[0]]);
	}
	
	def testUserEditLensNoUser =  {
		render (view:'user-edit-lens', model:[label:'CsUser.07', description:'User\'s edit lens with no user definition']);
	}
	
	def testUserEditLens =  {
		render (view:'user-edit-lens', model:[label:'CsUser.08', description:'User\'s edit lens',
			roles: Role.list(), userRoles:usersService.getUserRoles(User.list()[0]), user:User.list()[0]]);
	}
	
	def testUserEditLensWithError = {
		render (view:'user-edit-lens', model:[label:'CsUser.09', description:'User\'s edit lens with error ',
			roles: Role.list(), userRoles:usersService.getUserRoles(User.list()[0]), user:User.list()[0], msgError: 'test error message']);
	}
	
	def testUserEditLensWithLongError = {
		render (view:'user-edit-lens', model:[label:'CsUser.10', description:'User\'s edit lens with long error ',
			roles: Role.list(), userRoles:usersService.getUserRoles(User.list()[0]), user:User.list()[0], msgError: 'test error message test error message test error message test error message test error message test error message test error message test error message']);
	}
	
	def testUserEditLensWithWarning = {
		render (view:'user-edit-lens', model:[label:'CsUser.11', description:'User\'s edit lens with warning',
			roles: Role.list(), userRoles:usersService.getUserRoles(User.list()[0]), user:User.list()[0], msgWarning: 'test warning message']);
	}
	
	def testUserEditLensWithLongWarning = {
		render (view:'user-edit-lens', model:[label:'CsUser.12', description:'User\'s edit lens with long warning',
			roles: Role.list(), userRoles:usersService.getUserRoles(User.list()[0]), user:User.list()[0], msgWarning: 'test warning message test warning message test warning message test warning message test warning message test warning message test warning message']);
	}
	
	def testUserEditLensWithFieldValueError = {
		def user = User.list()[0];
		user.errors.reject('reason title 1',                    	// Error code within the grails-app/i18n/message.properties
			['title', 'class User'] as Object[],                          	// Groovy list cast to Object[]
			'[Property [{0}] of class [{1}] does not match confirmation]')   	// Default mapping string
		user.errors.rejectValue('title',                                                 // Field in view to highlight using <g:hasErrors> tag
			'reason title 1')
		
		user.errors.reject('reason firstName 1',                    	// Error code within the grails-app/i18n/message.properties
			['firstName', 'class User'] as Object[],                          	// Groovy list cast to Object[]
			'[Property [{0}] of class [{1}] does not match confirmation]')   	// Default mapping string
		user.errors.rejectValue('firstName',                                                 // Field in view to highlight using <g:hasErrors> tag
			'reason firstName 1')
		user.errors.reject('reason firstName 2',                    	// Error code within the grails-app/i18n/message.properties
			['firstName', 'class User'] as Object[],                          	// Groovy list cast to Object[]
			'[Property [{0}] of class [{1}] does not match confirmation]')   	// Default mapping string
		user.errors.rejectValue('firstName',                                                 // Field in view to highlight using <g:hasErrors> tag
			'reason firstName 2')
		
		user.errors.reject('reason middleName 1',                    	// Error code within the grails-app/i18n/message.properties
			['middleName', 'class User'] as Object[],                          	// Groovy list cast to Object[]
			'[Property [{0}] of class [{1}] does not match confirmation]')   	// Default mapping string
		user.errors.rejectValue('middleName',                                                 // Field in view to highlight using <g:hasErrors> tag
			'reason middleName 1')
		
		user.errors.reject('reason lastName 1',                    	// Error code within the grails-app/i18n/message.properties
			['lastName', 'class User'] as Object[],                          	// Groovy list cast to Object[]
			'[Property [{0}] of class [{1}] does not match confirmation]')   	// Default mapping string
		user.errors.rejectValue('lastName',                                                 // Field in view to highlight using <g:hasErrors> tag
			'reason lastName 1')
		
		user.errors.reject('reason displayName 1',                    	// Error code within the grails-app/i18n/message.properties
			['displayName', 'class User'] as Object[],                          	// Groovy list cast to Object[]
			'[Property [{0}] of class [{1}] does not match confirmation]')   	// Default mapping string
		user.errors.rejectValue('displayName',                                                 // Field in view to highlight using <g:hasErrors> tag
			'reason displayName 1')
		
		user.errors.reject('reason email 1',                    	// Error code within the grails-app/i18n/message.properties
			['email', 'class User'] as Object[],                          	// Groovy list cast to Object[]
			'[Property [{0}] of class [{1}] does not match confirmation]')   	// Default mapping string
		user.errors.rejectValue('email',                                                 // Field in view to highlight using <g:hasErrors> tag
			'reason email 1')
		
		user.errors.reject('reason affiliation 1',                    	// Error code within the grails-app/i18n/message.properties
			['affiliation', 'class User'] as Object[],                          	// Groovy list cast to Object[]
			'[Property [{0}] of class [{1}] does not match confirmation]')   	// Default mapping string
		user.errors.rejectValue('affiliation',                                                 // Field in view to highlight using <g:hasErrors> tag
			'reason affiliation 1')
		
		render (view:'user-edit-lens', model:[label:'CsUser.13', description:'User\'s edit lens with field value error', user:user]);
	}
	
	def testUserCreationLens =  {
		render (view:'user-create-lens', model:[label:'CsUser.14', description:'User\'s creation lens']);
	}
	
	def updateUserProfile = { UserProfileEditCommand cmd ->
		def validationFailed = validateUser(cmd);
		if (validationFailed) {
			println 'problems ' + cmd.errors;
		} else {
			def user = User.findById(params.id);
			println user
			if(user!=null) {
				user.title = params.title;
				user.firstName = params.firstName;
				user.middleName = params.middleName;
				user.lastName = params.lastName;
				user.affiliation = params.affiliation;
				user.country = params.country;
				user.displayName = params.displayName;
				user.email = params.email;

				
				render (view:'user-profile-edit-lens', model:[label:'CsUser.04', description:'User\'s profile edit lens', user:user]);
				return;
			}
		}
		
		// Validate agains build in rules
		render (view:'user-profile-edit-lens', model:[label:'CsUser.04', description:'User\'s profile edit lens', user:cmd]);
		
	}
	
	def updateUserAccount = { UserAccountEditCommand cmd ->
		def validationFailed = validateUser(cmd);
		if (validationFailed) {
			println 'problems ' + cmd.errors;
		} else {
			def user = User.findById(params.id);
			println '----- user ' + user
			if(user!=null) {
				
				updateUserRole(user, Role.findByAuthority(DefaultUsersRoles.ADMIN.value()), params.Administrator)
				updateUserRole(user, Role.findByAuthority(DefaultUsersRoles.MANAGER.value()), params.Manager)
				updateUserRole(user, Role.findByAuthority(DefaultUsersRoles.USER.value()), params.User)
	
				updateUserStatus(user, params.userStatus)
				
				render (view:'user-account-edit-lens', model:[label:'CsUser.04', description:'User\'s profile edit lens', user:user]);
				return;
			}
		}
		
		// Validate agains build in rules
		render (view:'user-account-edit-lens', model:[label:'CsUser.04', description:'User\'s profile edit lens', user:cmd]);
		
	}
	
	def createUser = { UserCreateCommand cmd ->
		println 'createUser'

			// Validate against custom rules
			def validationFailed = validateUser(cmd);
			if (validationFailed) {
				println 'problems ' + cmd.errors;
			} else {
				def user = new User();
				user.title = params.title;
				user.firstName = params.firstName;
				user.middleName = params.middleName;
				user.lastName = params.lastName;
				user.affiliation = params.affiliation;
				user.country = params.country;
				user.displayName = params.displayName;
				user.email = params.email;
				user.username =  params.username;
				
				if(!user.save()) {
					println 'problems ' + user.errors;
					
					render (view:'user-create-lens', model:[label:'CsUser.14', description:'User\'s create lens with field value error', user:user]);
					return
				} else {
					updateUserRole(user, Role.findByAuthority(DefaultUsersRoles.ADMIN.value()), params.Administrator)
					updateUserRole(user, Role.findByAuthority(DefaultUsersRoles.MANAGER.value()), params.Manager)
					updateUserRole(user, Role.findByAuthority(DefaultUsersRoles.USER.value()), params.User)
		
					updateUserStatus(user, params.userStatus)
					
					render (view:'user-edit-lens', model:[label:'CsUser.13', description:'User\'s edit lens with field value error', user:user]);
					return;
				}

			}
			
			// Validate agains build in rules
			render (view:'user-create-lens', model:[label:'CsUser.14', description:'User\'s create lens with field value error', user:cmd]);
	}
	
	def updateUser = { UserEditCommand cmd ->

		// Validate against custom rules
		def validationFailed = validateUser(cmd);
		if (validationFailed) {
			println 'problems ' + cmd.errors;
		} else {
			def user = User.findById(params.id);
			println user
			if(user!=null) {
				user.title = params.title;
				user.firstName = params.firstName;
				user.middleName = params.middleName;
				user.lastName = params.lastName;
				user.affiliation = params.affiliation;
				user.country = params.country;
				user.displayName = params.displayName;
				user.email = params.email;
				
				println '----'
				
				updateUserRole(user, Role.findByAuthority(DefaultUsersRoles.ADMIN.value()), params.Administrator)
				updateUserRole(user, Role.findByAuthority(DefaultUsersRoles.MANAGER.value()), params.Manager)
				updateUserRole(user, Role.findByAuthority(DefaultUsersRoles.USER.value()), params.User)
	
				updateUserStatus(user, params.userStatus)
				
				render (view:'user-edit-lens', model:[label:'CsUser.08', description:'User\'s edit lens', user:user]);
				return;
			}
		}
		
		// Validate agains build in rules
		render (view:'user-edit-lens', model:[label:'CsUser.08', description:'User\'s edit lens with field value error', user:cmd]);
	}
	protected def updateUserRole(def user, def role, def value) {
		if(value=='on') {
			def ur = UserRole.findByUserAndRole(user, role)
			if(ur==null) {
				UserRole urr = UserRole.create(user, role)
				urr.save(flush:true)
			}
		} else {
			def ur = UserRole.findByUserAndRole(user, role)
			if(ur!=null) {
				ur.delete(flush:true)
			}
		}
	}
	def updateUserStatus(def user, def status) {
		println 'status: ' + status
		if(status==UserStatus.CREATED_USER.value()) {
			user.enabled = false
			user.accountExpired = false
			user.accountLocked = false
		} else if(status==UserStatus.ACTIVE_USER.value()) {
			user.enabled = true
			user.accountExpired = false
			user.accountLocked = false
		} else if(status==UserStatus.DISABLED_USER.value()) {
			user.enabled = false
			user.accountExpired = false
			user.accountLocked = false
		} else if(status==UserStatus.LOCKED_USER.value()) {
			user.enabled = true
			user.accountExpired = false
			user.accountLocked = true
		}
	}
	
	private def validateUser(User cmd) {
		boolean validationFailed = false;
		def mandatory = UserUtils.getMandatoryFields(grailsApplication);
		println mandatory

		if(!cmd.validate()) {
			println 'validationFailed'
			validationFailed=true;
		}
		
		mandatory.each { item ->
			if(!(cmd[item]!=null && cmd[item].trim().length()>0)) {
				println 'problem ' + item;
				//cmd.errors.reject(g.message(code: 'org.commonsemantics.grails.general.message.error.cannotbenull', default: 'Field cannot be null'),
				//	[item, 'class User'] as Object[],
				//	'[Property [{0}] of class [{1}] does not match confirmation]')
				cmd.errors.rejectValue(item,
					g.message(code: 'default.blank.message', default: 'Field cannot be null'))
				validationFailed=true;
			}
		}
		
		//if(!validationFailed) {
			
		//}
		
		validationFailed;
	}
	
	private def validateUser(UserProfileEditCommand cmd) {
		boolean validationFailed = false;
		def mandatory = UserUtils.getMandatoryFields(grailsApplication);
		println mandatory

		if(!cmd.validate()) {
			println 'validationFailed'
			validationFailed=true;
		}
		
		mandatory.each { item ->
			if(!(cmd[item]!=null && cmd[item].trim().length()>0)) {
				println 'problem ' + item;
				//cmd.errors.reject(g.message(code: 'org.commonsemantics.grails.general.message.error.cannotbenull', default: 'Field cannot be null'),
				//	[item, 'class User'] as Object[],
				//	'[Property [{0}] of class [{1}] does not match confirmation]')
				cmd.errors.rejectValue(item,
					g.message(code: 'default.blank.message', default: 'Field cannot be null'))
				validationFailed=true;
			}
		}
		
		//if(!validationFailed) {
			
		//}
		
		validationFailed;
	}
	
	private def validateUser(UserCreateCommand cmd) {
		boolean validationFailed = false;
		def mandatory = UserUtils.getMandatoryFields(grailsApplication);
		println mandatory

		if(!cmd.validate()) {
			println 'validationFailed'
			validationFailed=true;
		}
		
		mandatory.each { item ->
			if(!(cmd[item]!=null && cmd[item].trim().length()>0)) {
				println 'problem ' + item;
				//cmd.errors.reject(g.message(code: 'org.commonsemantics.grails.general.message.error.cannotbenull', default: 'Field cannot be null'),
				//	[item, 'class User'] as Object[],
				//	'[Property [{0}] of class [{1}] does not match confirmation]')
				cmd.errors.rejectValue(item,
					g.message(code: 'default.blank.message', default: 'Field cannot be null'))
				validationFailed=true;
			}
		}
		
		//if(!validationFailed) {
			
		//} 
		
		validationFailed;
	}
	
	private def validateUser(UserAccountEditCommand cmd) {
		boolean validationFailed = false;
		def mandatory = UserUtils.getMandatoryFields(grailsApplication);
		println mandatory

		if(!cmd.validate()) {
			println 'validationFailed'
			validationFailed=true;
		}
		
		mandatory.each { item ->
			if(cmd.hasProperty(item)!=null) {
				if(!(cmd[item]!=null && cmd[item].trim().length()>0)) {
					println 'problem ' + item;
					//cmd.errors.reject(g.message(code: 'org.commonsemantics.grails.general.message.error.cannotbenull', default: 'Field cannot be null'),
					//	[item, 'class User'] as Object[],
					//	'[Property [{0}] of class [{1}] does not match confirmation]')
					cmd.errors.rejectValue(item,
						g.message(code: 'default.blank.message', default: 'Field cannot be null'))
					validationFailed=true;
				}
			}
		}
		
		//if(!validationFailed) {
			
		//}
		
		validationFailed;
	}
	
	private def validateUser(UserEditCommand cmd) {
		boolean validationFailed = false;
		def mandatory = UserUtils.getMandatoryFields(grailsApplication);
		println mandatory

		if(!cmd.validate()) {
			println 'validationFailed'
			validationFailed=true;
		}
		
		mandatory.each { item ->
			if(cmd.hasProperty(item)!=null) {
				if(!(cmd[item]!=null && cmd[item].trim().length()>0)) {
					println 'problem ' + item;
					//cmd.errors.reject(g.message(code: 'org.commonsemantics.grails.general.message.error.cannotbenull', default: 'Field cannot be null'),
					//	[item, 'class User'] as Object[],
					//	'[Property [{0}] of class [{1}] does not match confirmation]')
					cmd.errors.rejectValue(item,
						g.message(code: 'default.blank.message', default: 'Field cannot be null'))
					validationFailed=true;
				}
			}
		}
		
		//if(!validationFailed) {
			
		//}
		
		validationFailed;
	}
}
