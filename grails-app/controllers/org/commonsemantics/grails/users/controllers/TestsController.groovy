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

	def agentsService;
	def usersService;

	def index = { render (view:'tests') }

	// ------------------------------------------------------------------------
	//  CS-USERS:Person
	// ------------------------------------------------------------------------
	def testShowUserPerson = {
		log.debug("[TEST] show-user's-person " + (params.userid?("(id:" + params.userid + ")"):"(No id specified)"));
		def user = getUser(params.userid)
		render (view:'user-person-show-lens', model:[label:params.testId, description:params.testDescription, user:user]);
	}

	def testEditUserPerson = {
		log.debug("[TEST] edit-user's-person " + (params.userid?("(id:" + params.userid + ")"):"(No id specified)"));
		def user = getUser(params.userid)
		render (view:'user-person-edit-lens', model:[label:params.testId, description:params.testDescription, user:user, person:user.person]);
	}

	def testUpdateUserPerson = { PersonEditCommand cmd ->
		log.debug("[TEST] Creating person")
		def validationFailed = agentsService.validatePerson(cmd);
		if (validationFailed) {
			log.error("[TEST] While Creating Person " + cmd.errors)
		} else {
			def person = Person.findById(params.id);
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
		log.debug("[TEST] list-person max:" + params.max + " offset:" + params.offset)
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
		log.debug("[TEST] show-user " + (params.userid?("(id:" + params.userid + ")"):"(No id specified)"));
		def user = getUser(params.id)
		render (view:'user-show', model:[label:params.testId, description:params.testDescription, user:user]);
	}

	def testEditUser = {
		log.debug("[TEST] edit-user " + (params.userid?("(id:" + params.userid + ")"):"(No id specified)"));
		def user = getUser(params.id)
		render (view:'user-edit', model:[label:params.testId, description:params.testDescription, user:user,
			userRoles: UsersUtils.getUserRoles(user)]);
	}

	def testUpdateUser = { PersonEditCommand cmd ->
		def validationFailed = agentsService.validatePerson(cmd);
		if (validationFailed) {
			log.error("[TEST] While Updating User " + cmd.errors)
		} else {
			def user = User.findById(params.id);
			log.debug("[TEST] Updating User " + user)
			def person = user.person;
			log.debug("[TEST] Updating Person " + person)
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
		log.debug("[TEST] save-user " + cmd.displayName);
		UserCreateCommand c = new UserCreateCommand();
		def validationFailed = agentsService.validatePerson(cmd);
		if (validationFailed) {
			log.error("[TEST] While Saving User's Person " + cmd.errors)
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
					log.error("[TEST] While Saving User's Person " + person.errors)
					person.errors.each {
						// http://grails.org/doc/latest/api/grails/validation/ValidationErrors.html
						log.error("[TEST] While Saving User's Person " + it.target)
						it.fieldErrors.each { error ->
							// http://docs.spring.io/spring/docs/1.2.9/api/org/springframework/validation/FieldError.html
							// println '---- error ----' + error.getClass().getName()
							// println '---- error ----' + error.getField()
							// println '---- error ----' + error.getDefaultMessage()
							cmd.errors.rejectValue(error.getField(),
									g.message(code: 'org.commonsemantics.grails.users.model.field.username.not.available.message', default: error.getDefaultMessage()))
						}
					}
				} else {
					def user = new User(username: params.username, person:person)
					if(!user.save(flush: true)) {
						log.error("[TEST] While Saving User " + cmd.errors)
						user.errors.each {
							// http://grails.org/doc/latest/api/grails/validation/ValidationErrors.html
							log.error("[TEST] While Saving User " + it.target)
							it.fieldErrors.each { error ->
								// http://docs.spring.io/spring/docs/1.2.9/api/org/springframework/validation/FieldError.html
								//println '---- error ----' + error.getClass().getName()
								//println '---- error ----' + error.getField()
								//println '---- error ----' + error.getDefaultMessage()
								c.errors.rejectValue(error.getField(),
										g.message(code: 'org.commonsemantics.grails.users.model.field.username.not.available.message', default: error.getDefaultMessage()))

								println c.errors
							}
						}
						log.error("[TEST] Rolling back User's Person " + person)
						personStatus.setRollbackOnly();

						c.username = params.username;
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
						log.debug("[TEST] save-user roles and status");
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

	def testListUsers = {
		log.debug("[TEST] list-users max:" + params.max + " offset:" + params.offset)
		render (view:'users-list', model:[label:params.testId, description:params.testDescription, users:User.list(params),
			usersTotal: User.count(), max: params.max, offset: params.offset, controller:'tests', action: 'testListUsers']);
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
		log.debug 'User ' + user + ' status ' + status
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
}
