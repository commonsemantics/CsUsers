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

import org.commonsemantics.grails.agents.commands.PersonCreateCommand
import org.commonsemantics.grails.agents.commands.PersonEditCommand
import org.commonsemantics.grails.agents.model.Person
import org.commonsemantics.grails.agents.utils.AgentsUtils
import org.commonsemantics.grails.users.commands.UserCreateCommand
import org.commonsemantics.grails.users.commands.UserEditCommand
import org.commonsemantics.grails.users.model.Role
import org.commonsemantics.grails.users.model.User
import org.commonsemantics.grails.users.model.UserRole
import org.commonsemantics.grails.users.utils.DefaultUsersRoles
import org.commonsemantics.grails.users.utils.UserStatus
import org.commonsemantics.grails.users.utils.UsersUtils


/**
 * @author Paolo Ciccarese <paolo.ciccarese@gmail.com>
 */
class TestsController {

	static defaultAction = "index"
	
	def usersService;
	
	def index = {
		render (view:'tests')
	}
	
	// ------------------------------------------------------------------------
	//  CS-USERS:Person
	// ------------------------------------------------------------------------
	def testShowUserPerson = {
		def user = getUser(params.userid)
		render (view:'user-person-show-lens', model:[label:params.testId, description:params.testDescription, user:user]);
	}
	
	def testEditUserPerson = {
		def user = getUser(params.userid)
		render (view:'user-person-edit-lens', model:[label:params.testId, description:params.testDescription, user:user, person:user.person]);
	}
	
	def testUpdateUserPerson = { PersonEditCommand cmd ->
		def validationFailed = AgentsUtils.validatePerson(grailsApplication, cmd);
		if (validationFailed) {
			println 'problems ' + cmd.errors;
		} else {
			def person = Person.findById(params.id);
			println person
			if(person!=null) {
				person.title = params.title;
				person.firstName = params.firstName;
				person.middleName = params.middleName;
				person.lastName = params.lastName;
				person.affiliation = params.affiliation;
				person.country = params.country;
				person.displayName = params.displayName;
				person.email = params.email;
	
				def user = getUser(params.userid);
				render (view:'user-person-show-lens', model:[label:params.testId, description:params.testDescription, user:user, person:user.person]);
				return;
			}
		}
		def user = getUser(params.userid);
		render (view:'user-person-edit-lens', model:[label:params.testId, description:params.testDescription, user:user, person:cmd]);
	}
	
	def testCreateUserPerson = {
		render (view:'user-person-create', model:[label:params.testId, description:params.testDescription]);
	}
	
	def testListUserPersons = {
		render (view:'user-persons-list-lens', model:[label:params.testId, description:params.testDescription, persons:Person.list(params), personsTotal: Person.count(),
			max: params.max, offset: params.offset, controller:'tests', action: 'testListPersons']);
	}
	
	private def getUser(def id) {
		def user;
		if(id==null)  user = User.list()[0];
		else user = User.findById(id);
		user
	}
	
	private def getPerson(def id) {
		def person;
		if(id==null)  person=Person.list()[0];
		else person = Person.findById(id);
		person
	}
	
	// ------------------------------------------------------------------------
	//  CS-USERS:User
	// ------------------------------------------------------------------------
	
	def testShowUser = {
		def user = getUser(params.id)
		render (view:'user-show', model:[label:params.testId, description:params.testDescription, user:user]);
	}
	
	def testEditUser = {
		def user = getUser(params.id)
		render (view:'user-edit', model:[label:params.testId, description:params.testDescription, user:user, 
			userRoles: UsersUtils.getUserRoles(user)]);
	}
	
	def testUpdateUser = { PersonEditCommand cmd ->
		println params
		def validationFailed = AgentsUtils.validatePerson(grailsApplication, cmd);
		if (validationFailed) {
			println 'problems ' + cmd.errors;
		} else {
			def user = User.findById(params.id);
			def person = user.person;
			if(person!=null) {
				person.title = params.title;
				person.firstName = params.firstName;
				person.middleName = params.middleName;
				person.lastName = params.lastName;
				person.affiliation = params.affiliation;
				person.country = params.country;
				person.displayName = params.displayName;
				person.email = params.email;
				
				
	
				updateUserRole(user, Role.findByAuthority(DefaultUsersRoles.ADMIN.value()), params.Administrator)
				updateUserRole(user, Role.findByAuthority(DefaultUsersRoles.MANAGER.value()), params.Manager)
				updateUserRole(user, Role.findByAuthority(DefaultUsersRoles.USER.value()), params.User)
	
				updateUserStatus(user, params.userStatus)
				
				render (view:'user-show', model:[label:params.testId, description:params.testDescription, user:user]);
				return;
			}
		}

		UserEditCommand c = new UserEditCommand();
		c.id = params.id;
		c.username = params.username;
		println '---userStatus ' + params.userStatus;
		c.status = params.userStatus;
		c.person = cmd;
		
		def usersRoles = [];
		if(params.Administrator=='on') {
			usersRoles.add(Role.findByAuthority(DefaultUsersRoles.ADMIN.value()));
		}
		if(params.Manager=='on') {
			usersRoles.add(Role.findByAuthority(DefaultUsersRoles.MANAGER.value()));
		}
		if(params.User=='on') {
			usersRoles.add(Role.findByAuthority(DefaultUsersRoles.USER.value()));
		}
		
		
		render (view:'user-edit', model:[label:params.testId, description:params.testDescription, user:c, userRoles: usersRoles]);
	}
	
	def testCreateUser = {
		render (view:'user-create', model:[label:params.testId, description:params.testDescription]);
	}
	
	def testSaveUser = {PersonCreateCommand cmd ->
		println params
		UserCreateCommand c = new UserCreateCommand();
		def validationFailed = AgentsUtils.validatePerson(grailsApplication, cmd);
		if (validationFailed) {
			println 'problems ' + cmd.errors;
		} else {
			def person = new Person();
			person.title = params.title;
			person.firstName = params.firstName;
			person.middleName = params.middleName;
			person.lastName = params.lastName;
			person.affiliation = params.affiliation;
			person.country = params.country;
			person.displayName = params.displayName;
			person.email = params.email;
			
			Person.withTransaction { personStatus ->
				if(!person.save(flush: true)) {
					person.errors.each { 
						 // http://grails.org/doc/latest/api/grails/validation/ValidationErrors.html
						 println '---- field error ----' + it.target
						 it.fieldErrors.each { error ->
							 // http://docs.spring.io/spring/docs/1.2.9/api/org/springframework/validation/FieldError.html
							 println '---- error ----' + error.getClass().getName()
							 println '---- error ----' + error.getField()
							 println '---- error ----' + error.getDefaultMessage()
							 cmd.errors.rejectValue(error.getField(),
								 g.message(code: 'org.commonsemantics.grails.users.model.field.username.not.available.message', default: error.getDefaultMessage()))
						 }
					} 
				} else {					
					def user = new User(username: params.username, person:person)
					if(!user.save(flush: true)) {
						println '---else04 '
						user.errors.each {
							 // http://grails.org/doc/latest/api/grails/validation/ValidationErrors.html
							 println '---- field error ----' + it.target
							 it.fieldErrors.each { error ->
								 // http://docs.spring.io/spring/docs/1.2.9/api/org/springframework/validation/FieldError.html
								 println '---- error ----' + error.getClass().getName()
								 println '---- error ----' + error.getField()
								 println '---- error ----' + error.getDefaultMessage()
								 c.errors.rejectValue(error.getField(),
									 g.message(code: 'org.commonsemantics.grails.users.model.field.username.not.available.message', default: error.getDefaultMessage()))
								 
								 println c.errors
							 }
						}				
						personStatus.setRollbackOnly();
						
						println '---else05 '
						c.username = params.username;
						println '---userStatus ' + params.userStatus;
						c.status = params.userStatus;
						c.person = cmd;
						UsersUtils.validateUser(grailsApplication, c);
						
						def usersRoles = [];
						if(params.Administrator=='on') {
							usersRoles.add(Role.findByAuthority(DefaultUsersRoles.ADMIN.value()));
						}
						if(params.Manager=='on') {
							usersRoles.add(Role.findByAuthority(DefaultUsersRoles.MANAGER.value()));
						}
						if(params.User=='on') {
							usersRoles.add(Role.findByAuthority(DefaultUsersRoles.USER.value()));
						}
						
						
						render (view:'user-create', model:[label:params.testId, description:params.testDescription, user:c, userRoles: usersRoles]);
					} else {
						println '---else03 '
						updateUserRole(user, Role.findByAuthority(DefaultUsersRoles.ADMIN.value()), params.Administrator)
						updateUserRole(user, Role.findByAuthority(DefaultUsersRoles.MANAGER.value()), params.Manager)
						updateUserRole(user, Role.findByAuthority(DefaultUsersRoles.USER.value()), params.User)
			
						updateUserStatus(user, params.userStatus)
						
						render (view:'user-show', model:[label:params.testId, description:params.testDescription, user:user]);
						return;
					}
				}
			}
		}
		
		
	}
	
	/*
	def testSaveUser = { UserCreateCommand cmd ->
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
				
				if(!user.save(flush: true)) {
					println 'problems ' + user.errors;
					
					render (view:'user-create', model:[label:'CsUser.14', description:'User\'s create lens with field value error', user:user]);
					return
				} else {
					updateUserRole(user, Role.findByAuthority(DefaultUsersRoles.ADMIN.value()), params.Administrator)
					updateUserRole(user, Role.findByAuthority(DefaultUsersRoles.MANAGER.value()), params.Manager)
					updateUserRole(user, Role.findByAuthority(DefaultUsersRoles.USER.value()), params.User)
		
					updateUserStatus(user, params.userStatus)
					
					render (view:'user-edit', model:[label:'CsUser.08', description:'User\'s edit lens', user:user]);
					return;
				}

			}
			
			// Validate against build in rules
			render (view:'user-create', model:[label:'CsUser.14', description:'User\'s create lens with field value error', user:cmd]);
	}
	*/
	private def validateUser(def cmd) {
		boolean validationFailed = false;
		def mandatory = UsersUtils.getMandatoryFields(grailsApplication);
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
	
	def testListUsers = {
		def user = getUser(params.id)
		render (view:'users-list', model:[label:params.testId, description:params.testDescription, users:User.list(),
			usersTotal: User.count(), max: params.max, offset: params.offset]);
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
	protected def updateUserStatus(def user, def status) {
		println 'status: ' + status
		if(status==UserStatus.CREATED_USER.value()) {
			user.enabled = true
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
	
	def testLockUser = {
		def user = User.findById(params.id)
		user.accountLocked = true
		user.enabled = true;
		if(params.redirect)
			redirect(action:params.redirect, params:[id: params.id])
		else
			redirect(action:'testShowUser', params:[id: params.id])
			//render (view:'showProfile', model:[user: user])
	}

	def testUnlockUser = {
		def user = User.findById(params.id)
		user.accountLocked = false
		user.enabled = true;
		if(params.redirect)
			redirect(action:params.redirect, params:[id: params.id])
		else
			redirect(action:'testShowUser', params:[id: params.id])
	}

	def testEnableUser = {
		def user = User.findById(params.id)
		user.enabled = true
		user.accountLocked = false
		if(params.redirect)
			redirect(action:params.redirect, params:[id: params.id])
		else
			redirect(action:'testShowUser', params:[id: params.id])
	}

	def testDisableUser = {
		def user = User.findById(params.id)
		user.enabled = false
		user.accountLocked = false
		if(params.redirect)
			redirect(action:params.redirect, params:[id: params.id])
		else
			redirect(action:'testShowUser', params:[id: params.id])
	}
	
	
	/*
	

	
	def updateUserPerson = { PersonEditCommand cmd ->
		def validationFailed = validatePerson(cmd);
		if (validationFailed) {
			println 'problems ' + cmd.errors;
		} else {
			def person = Person.findById(params.id);
			println person
			if(person!=null) {
				person.title = params.title;
				person.firstName = params.firstName;
				person.middleName = params.middleName;
				person.lastName = params.lastName;
				person.affiliation = params.affiliation;
				person.country = params.country;
				person.displayName = params.displayName;
				person.email = params.email;
	
				render (view:'person-show', model:[label:params.testId, description:params.testDescription, person:person]);
				return;
			}
		}
		render (view:'user-person-edit-lens', model:[label:'CsUser.04', description:'User\'s profile edit lens', person:cmd]);
	}
	
	private def validatePerson(PersonEditCommand cmd) {
		boolean validationFailed = false;
		def mandatory = AgentsUtils.getPersonMandatoryFields(grailsApplication);
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
	
	def testUserDisplayLens = {
		render (view:'user-show-lens', model:[label:params.testId, description:'User\'s display lens',
			roles: Role.list(), userRoles:usersService.getUserRoles(User.list()[0]), user:User.list()[0]]);
	}
	
	
	
	
	
	
	
	
	
	def testUserPersonEditLens = {
		render (view:'user-person-edit-lens', model:[label:params.testId, description:params.testDescription, person:User.list()[0].person]);
	}
	

	
	
	
	def testUserDisplayLensNoUser = {
		render (view:'user-show-lens', model:[label:params.testId, description:'User\'s display lens with no user definition']);
	}
	
	
	
	def showUser = {
		render (view:'user-show-lens', model:[label:params.testId, description:'User\'s display lens',
			roles: Role.list(), userRoles:usersService.getUserRoles(User.findById(params.id)), user:User.findById(params.id)]);
	}
	
	def testUserProfileFieldsLensNoUser =  {
		render (view:'user-profile-edit-lens', model:[label:params.testId, description:'User\'s profile edit lens with no user definition']);
	}
	
	def testUserProfileFieldsLens = {
		render (view:'user-profile-edit-lens', model:[label:params.testId, description:'User\'s profile edit lens', user:User.list()[0]]);
	}
	
	def testUserAccountFieldsLensNoUser =  {
		render (view:'user-account-edit-lens', model:[label:params.testId, description:'User\'s account edit lens with no user definition']);
	}
	
	def testUserAccountFieldsLens =  {
		render (view:'user-account-edit-lens', model:[label:params.testId, description:'User\'s account edit lens', 
			roles: Role.list(), userRoles:usersService.getUserRoles(User.list()[0]), user:User.list()[0]]);
	}
	
	def testUserEditLensNoUser =  {
		render (view:'user-edit-lens', model:[label:params.testId, description:'User\'s edit lens with no user definition']);
	}
	
	def testUserEditLens =  {
		render (view:'user-edit-lens', model:[label:params.testId, description:'User\'s edit lens',
			roles: Role.list(), userRoles:usersService.getUserRoles(User.list()[0]), user:User.list()[0]]);
	}
	
	def testUserEditLensWithError = {
		render (view:'user-edit-lens', model:[label:params.testId, description:'User\'s edit lens with error ',
			roles: Role.list(), userRoles:usersService.getUserRoles(User.list()[0]), user:User.list()[0], msgError: 'test error message']);
	}
	
	def testUserEditLensWithLongError = {
		render (view:'user-edit-lens', model:[label:params.testId, description:'User\'s edit lens with long error ',
			roles: Role.list(), userRoles:usersService.getUserRoles(User.list()[0]), user:User.list()[0], msgError: 'test error message test error message test error message test error message test error message test error message test error message test error message']);
	}
	
	def testUserEditLensWithWarning = {
		render (view:'user-edit-lens', model:[label:params.testId, description:'User\'s edit lens with warning',
			roles: Role.list(), userRoles:usersService.getUserRoles(User.list()[0]), user:User.list()[0], msgWarning: 'test warning message']);
	}
	
	def testUserEditLensWithLongWarning = {
		render (view:'user-edit-lens', model:[label:params.testId, description:'User\'s edit lens with long warning',
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
		
		render (view:'user-edit-lens', model:[label:params.testId, description:'User\'s edit lens with field value error', user:user]);
	}
	
	def testUserCreationLens =  {
		render (view:'user-create-lens', model:[label:params.testId, description:'User\'s creation lens']);
	}
	
	def testUsersList =  {
		render (view:'users-list', model:[label:params.testId, description:params.testDescription, users: User.list(), usersTotal: User.count()]);
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
				
				if(!user.save(flush: true)) {
					println 'problems ' + user.errors;
					
					render (view:'user-create-lens', model:[label:'CsUser.14', description:'User\'s create lens with field value error', user:user]);
					return
				} else {
					updateUserRole(user, Role.findByAuthority(DefaultUsersRoles.ADMIN.value()), params.Administrator)
					updateUserRole(user, Role.findByAuthority(DefaultUsersRoles.MANAGER.value()), params.Manager)
					updateUserRole(user, Role.findByAuthority(DefaultUsersRoles.USER.value()), params.User)
		
					updateUserStatus(user, params.userStatus)
					
					render (view:'user-edit-lens', model:[label:'CsUser.08', description:'User\'s edit lens', user:user]);
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
	*/
}
