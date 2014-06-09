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
package org.commonsemantics.grails.users.utils

import org.apache.log4j.Logger
import org.commonsemantics.grails.users.commands.UserCreateCommand
import org.commonsemantics.grails.users.commands.UserEditCommand
import org.commonsemantics.grails.users.model.ProfilePrivacy
import org.commonsemantics.grails.users.model.Role
import org.commonsemantics.grails.users.model.User
import org.commonsemantics.grails.users.model.UserProfilePrivacy
import org.commonsemantics.grails.users.model.UserRole
import org.commonsemantics.grails.utils.LoggingUtils

/**
* @author Paolo Ciccarese <paolo.ciccarese@gmail.com>
*/
class UsersUtils {
	
	static Logger log = Logger.getLogger(UsersUtils.class) // log4j

	static String getStatusLabel(def user) {
		if(user.isEnabled()) {
			 if(user.isAccountLocked()) return UserStatus.LOCKED_USER.value();
			 else return UserStatus.ACTIVE_USER.value();
		} else {
			if(user.isAccountLocked()) return UserStatus.LOCKED_USER.value();
			else return UserStatus.DISABLED_USER.value();
		}
	}
	
	static def listRoles(def max, def offset, def sort, def _order) {
		def rolesCount = [:]
		Role.list().each { arole ->
			rolesCount.put (arole.id, UserRole.findAllWhere(role: arole).size())
		}
		
		def roles = [];
		if (sort == 'rolesCount') {
			rolesCount.sort({ a, b -> a <=> b } as Comparator)
			if(_order == "desc")
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

	//-------------------------------------------------------------------------
	// USERS
	//-------------------------------------------------------------------------
//	static def getPersonConfigurationMandatoryFields(def grailsApplication) {
//		def mandatory =[];
//		if(grailsApplication.config.org.commonsemantics.grails.persons.model.fields.mandatory.size()>0) {
//			mandatory.addAll(Eval.me(grailsApplication.config.org.commonsemantics.grails.persons.model.fields.mandatory));
//		}
//		return mandatory;
//	}
//	
//	static boolean isFieldMandatory(def grailsApplication, def fieldName) {
//		// Mandatory fields by configuration
//		def mandatoryByConfiguration =  Eval.me(grailsApplication.config.org.commonsemantics.grails.users.model.fields.mandatory);
//		// Mandatory fields by coding
//		if(!User.constraints[fieldName].nullable) mandatoryByConfiguration.add(fieldName);
//		if(fieldName in User.mandatory || fieldName in mandatoryByConfiguration) {
//			println 'mandatory: ' + fieldName;
//			return true;
//		}
//		return false;
//	}
//	
	static def getUserMandatoryFields(def grailsApplication) {
		def mandatory = [];
		if(grailsApplication.config.org.commonsemantics.grails.users.model.fields.mandatory.size()>0) {
			mandatory.addAll(Eval.me(grailsApplication.config.org.commonsemantics.grails.users.model.fields.mandatory));
		}
		return mandatory;
	}
	
	static def getUserDynamicMandatoryFields(def grailsApplication) {
		def mandatory = User.mandatory.clone();
		mandatory.addAll(getUserMandatoryFields(grailsApplication));
		return mandatory;
	}
	
	static boolean isUserFieldRequired(def grailsApplication, def fieldName) {
		// Mandatory fields by dynamic configuration
		def mandatoryByConfiguration = getUserDynamicMandatoryFields(grailsApplication)
		// Mandatory fields by static coding
		if(!User.constraints[fieldName]?.nullable) mandatoryByConfiguration.add(fieldName);
		
		if((isUserStaticPropertyExisting('mandatory') && fieldName in User.mandatory) || fieldName in mandatoryByConfiguration) {
			log.debug LoggingUtils.LOG_CONF + ' User mandatory field: ' + fieldName;
			return true;
		}
		return false;
	}
	
	static def isUserStaticPropertyExisting(def name) {
		User.class.declaredFields.find {
			it.name == 'x' && isStatic(it.modifiers)
		}
	}

	
	static def getUserProfilePrivacy(def user) {
		def upp = UserProfilePrivacy.findByUser(user)
		if(upp!=null) {
			log.debug("User " + user + " privacy " + upp.profilePrivacy.label);
			return upp.profilePrivacy
		} else return null;
	}
	
	static def getUserRoles(def user) {
		def userRoles = [];
		def ur = UserRole.findAllByUser(user)
		ur.each { userRoles.add(it.role)}
		log.debug("User " + user + " roles " + userRoles.label);
		return userRoles
	}
	
	static def getUserRoles(UserCreateCommand user) {
		def userRoles = []
		return userRoles
	}
	
	static def getUserRoles(UserEditCommand user) {
		def userRoles = []
		return userRoles
	}
	
	String getIsAdmin() {
		boolean flag = false;
		def userrole = UserRole.findAllByUser(this)
		userrole.each {
			if(it.role.authority.equals(DefaultRoles.ADMIN.value())) {
				flag = true;
			}
		}
		flag ? "y" : ""
	}
	
	String getIsManager() {
		boolean flag = false;
		def userrole = UserRole.findAllByUser(this)
		userrole.each {
			if(it.role.authority.equals(DefaultRoles.MANAGER.value())) {
				flag = true;
			}
		}
		flag ? "y" : ""
	}
	
	String getIsUser() {
		boolean flag = false;
		def userrole = UserRole.findAllByUser(this)
		userrole.each {
			if(it.role.authority.equals(DefaultRoles.USER.value())) {
				flag = true;
			}
		}
		flag ? "y" : ""
	}
	
	String getIsAnalyst() {
		boolean flag = false;
		def userrole = UserRole.findAllByUser(this)
		userrole.each {
			if(it.role.authority.equals(DefaultRoles.ANALYST.value())) {
				flag = true;
			}
		}
		flag ? "y" : ""
	}
	
	def getRoleRank() {
		int rank = 0;
		def userrole = UserRole.findAllByUser(this)
		userrole.each {
			println it.role
			println it.role.getRanking()
			rank += it.role.getRanking();
		}
		rank
	}
	
	def getRole() {
		def userrole = UserRole.findByUser(this)
		if(userrole) {
			if(userrole.role.authority.equals("ROLE_ADMIN")) {
				return "Admin"
			} else if(userrole.role.authority.equals("ROLE_USER")) {
				return "User"
			} else {
				return userrole.role.authority;
			}
		} else {
			return "Error"
		}
	}
	
	static protected def updateUserRole(def user, def role, def value) {
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
	static protected def updateUserStatus(def user, def status) {
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
	
	static protected def getProfilePrivacy(def privacy) {
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
		return profilePrivacy;
	}
	
//	static protected def updateUserProfilePrivacy(def user, def privacy) {
//		log.debug 'User ' + user + ' privacy ' + privacy
//		def upp = UserProfilePrivacy.findByUser(user)
//		if(upp!=null) {
//			if(privacy==DefaultUsersProfilePrivacy.PUBLIC.value()) {
//				upp.profilePrivacy = ProfilePrivacy.findByValue(DefaultUsersProfilePrivacy.PUBLIC.value());
//			} else if(privacy==DefaultUsersProfilePrivacy.RESTRICTED.value()) {
//				upp.profilePrivacy = ProfilePrivacy.findByValue(DefaultUsersProfilePrivacy.RESTRICTED.value());
//			} else if(privacy==DefaultUsersProfilePrivacy.PRIVATE.value()) {
//				upp.profilePrivacy = ProfilePrivacy.findByValue(DefaultUsersProfilePrivacy.PRIVATE.value());
//			} else if(privacy==DefaultUsersProfilePrivacy.ANONYMOUS.value()) {
//				upp.profilePrivacy = ProfilePrivacy.findByValue(DefaultUsersProfilePrivacy.ANONYMOUS.value());
//			}
//		} else {
//			UserProfilePrivacy.create(user, ProfilePrivacy.findByValue(privacy));
//		}
//	}
}