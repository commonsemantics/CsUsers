/*
 * Copyright 2013 Common Semantics  (commonsemantics.org)
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
package org.commonsemantics.grails.users.services

import org.codehaus.groovy.grails.plugins.web.taglib.ValidationTagLib
import org.commonsemantics.grails.users.model.ProfilePrivacy
import org.commonsemantics.grails.users.model.Role
import org.commonsemantics.grails.users.model.User
import org.commonsemantics.grails.users.model.UserRole
import org.commonsemantics.grails.users.utils.DefaultUsersProfilePrivacy
import org.commonsemantics.grails.users.utils.UserStatus
import org.commonsemantics.grails.users.utils.UsersUtils

/**
 * @author Paolo Ciccarese <paolo.ciccarese@gmail.com>
 */
class UsersService {
	
	static transactional = false
	
	def grailsApplication;
	def agentsService;
	
	def validateUser(def cmd) {
		def g = new ValidationTagLib()
		boolean validationFailed = false;
		def mandatory = UsersUtils.getUserMandatoryFields(grailsApplication);
		println mandatory

		if(!cmd.validate()) {
			println 'validationFailed ' + cmd.errors
			validationFailed=true;
		}
		
		if(cmd.isPasswordValid()) {
			//c.password = params.password;
		} else {
			log.error("Passwords not matching while saving ")
			cmd.passwordConfirmation = null;
			cmd.errors.rejectValue("password",
				g.message(code: 'org.commonsemantics.grails.users.model.field.password.not.matching.message', default: "Passwords not matching"));
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
		validationFailed;
	}
	
	/**
	 * Returns the list of all the users of the node with pagination
	 * @param user		The user requesting the list
	 * @param max		The maximum results to be returned
	 * @param offset	The offset from the beginning of the list
	 * @param sort		The sorting criteria
	 * @param _order	The order asc or desc
	 * @return The list of users
	 */
	def listUsers(def user, def _max, def _offset, def _sort, def _order) {
		
		def users = [];
		
		if(_sort == 'name') {
			def userStatusCriteria = User.createCriteria();
			def r = userStatusCriteria.list {
				maxResults(_max?.toInteger())
				firstResult(_offset?.toInteger())
				order('lastName', _order)
				order('firstName', _order)
			}
			users = r.toList();
		}  else if (_sort == 'isAdmin' || _sort == 'isAnalyst' || _sort == 'isManager'
				|| _sort == 'isUser') {
			def buffer = [];
			def usersStatus = [:]
			User.list().each { auser ->
				if(_sort == 'isAdmin')
					usersStatus.put (auser.id, auser.isAdmin)
				else if(_sort == 'isManager')
					usersStatus.put (auser.id, auser.isManager)
				else if(_sort == 'isUser')
					usersStatus.put (auser.id, auser.isUser)
				//else if(_sort == 'isAnalyst')
				//	usersStatus.put (auser.id, auser.isAnalyst)
			}
			usersStatus = usersStatus.sort{ a, b -> a.value.compareTo(b.value) }
			if(_order == "desc")
				usersStatus.each { userStatus ->
					buffer.add(User.findById(userStatus.key));
				}
			else
				usersStatus.reverseEach { userStatus ->
					buffer.add(User.findById(userStatus.key));
				}
				
			int offset = (_offset instanceof String) ? Integer.parseInt(_offset) : _offset
			int max = (_max instanceof String) ? Integer.parseInt(_max) : _max
			for(int i=offset;i< Math.min(offset+max, usersStatus.size()); i++) {
				users.add(buffer[i]);
			}
		} else if (_sort == 'status') {
			def buffer = [];
			def usersStatus = [:]
			User.list().each { auser ->
				usersStatus.put (auser.id, auser.status)
			}
			usersStatus = usersStatus.sort{ a, b -> a.value.compareTo(b.value) }
			if(_order == "desc")
				usersStatus.each { userStatus ->
					buffer.add(User.findById(userStatus.key));
				}
			else
				usersStatus.reverseEach { userStatus ->
					buffer.add(User.findById(userStatus.key));
				}
				
			int offset = (_offset instanceof String) ? Integer.parseInt(_offset) : _offset
			int max = (_max instanceof String) ? Integer.parseInt(_max) : _max
			for(int i=offset;i< Math.min(offset+max, usersStatus.size()); i++) {
				users.add(buffer[i]);
			}
		} else {
			def userStatusCriteria = User.createCriteria();
			def r = userStatusCriteria.list {
				maxResults(_max?.toInteger())
				firstResult(_offset?.toInteger())
				order(_sort, _order)
			}
			users = r.toList();
		}
		users
	}
	
	def listRoles(def max, def offset, def sort, def _order) {
		def rolesCount = [:]
		Role.list().each { arole ->
			rolesCount.put (arole.id, UserRole.findAllWhere(role: arole).size())
		}
		
		def roles = [];
		if (sort == 'rolesCount') {
			rolesCount.sort({ a, b -> a <=> b } as Comparator)
			if(order == "desc")
				rolesCount.each { roleCount ->
					roles.add Role.findById(roleCount.key);
				}
			else
				rolesCount.reverseEach { roleCount ->
					roles.add Role.findById(roleCount.key);
				}
		} else {
			roles = Role.withCriteria {
				maxResults(max?.toInteger())
				firstResult(offset?.toInteger())
				order(sort, _order)
			}
		}
		[roles, rolesCount]
	}
	
	def updateUserProfilePrivacy(def user, def privacy) {
		log.debug 'User ' + user + ' privacy ' + privacy
		
		ProfilePrivacy profilePrivacy;
		if(privacy==DefaultUsersProfilePrivacy.PUBLIC.value()) {
			profilePrivacy = ProfilePrivacy.findByValue(DefaultUsersProfilePrivacy.PUBLIC.value());
		} else if(privacy==DefaultUsersProfilePrivacy.RESTRICTED.value()) {
			profilePrivacy = ProfilePrivacy.findByValue(DefaultUsersProfilePrivacy.RESTRICTED.value());
		} else if(privacy==DefaultUsersProfilePrivacy.PRIVATE.value()) {
			profilePrivacy = ProfilePrivacy.findByValue(DefaultUsersProfilePrivacy.PRIVATE.value());
		} else if(privacy==DefaultUsersProfilePrivacy.ANONYMOUS.value()) {
			profilePrivacy = ProfilePrivacy.findByValue(DefaultUsersProfilePrivacy.ANONYMOUS.value());
		} else {
			profilePrivacy = ProfilePrivacy.findByValue(DefaultUsersProfilePrivacy.PRIVATE.value());
		}
		
		user.profilePrivacy = profilePrivacy;
		
		/*
		def upp = UserProfilePrivacy.findByUser(user)
		if(upp==null) {
			upp = UserProfilePrivacy.create(user, profilePrivacy);
		} else {
		    upp.delete();
			upp = UserProfilePrivacy.create(user, profilePrivacy);
		}
		*/
	}
	
	def boolean updateUserRole(def user, def role, def value) {
		if(value=='on') {
			def ur = UserRole.findByUserAndRole(user, role)
			if(ur==null) {
				UserRole urr = UserRole.create(user, role)
				urr.save(flush:true)
				return true;
			}
		} else {
			def ur = UserRole.findByUserAndRole(user, role)
			if(ur!=null) {
				ur.delete(flush:true)
				return false;
			}
		}
	}
	
	def updateUserStatus(def user, def status) {
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
	
	def getUserAgentIdentifiers(def userId) {
		if(userId.indexOf('user:')>-1) userId = userId.substring(5);
		def user = User.findById(userId);
		return agentsService.getAgentIdentifiers(user.person);
	}
}
