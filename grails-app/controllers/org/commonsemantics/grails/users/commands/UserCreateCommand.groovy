/*
 * Copyright 2013 Common Semantics (commonsemantics.org)
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
package org.commonsemantics.grails.users.commands

import grails.validation.Validateable

import org.commonsemantics.grails.agents.commands.PersonCreateCommand
import org.commonsemantics.grails.users.model.User
import org.commonsemantics.grails.users.utils.UserStatus
import org.commonsemantics.grails.users.utils.UsersUtils

/**
* Object command for User validation and creation.
*
* @author Paolo Ciccarese <paolo.ciccarese@gmail.com>
*/
@Validateable
class UserCreateCommand {

	def grailsApplication
	
	public static final Integer NAME_MAX_SIZE = 255;
	
	// Users status values
	//---------------------
	String status
	
	//Users' data
	PersonCreateCommand person
	
	//Account credentials
	String username
	String password
	String passwordConfirmation
	
	static constraints = {
		importFrom User
	}
	
	def areMandatoryFieldDefined() {
		println UsersUtils.getMandatoryFields(grailsApplication);
	}
	
	boolean isEnabled() {
		return status.equals(UserStatus.ACTIVE_USER.value());
	}
	
	boolean isLocked() {
		return status.equals(UserStatus.LOCKED_USER.value());
	}
	
	boolean isPasswordValid() {
		return password.equals(passwordConfirmation);
	}	
	
	/*
	User createUser() {
		if(isPasswordValid()) {
			return User.findByUsername(username) ? null:
				new User(title: title, firstName: firstName, middleName: middleName, lastName: lastName, displayName: displayName, username: username, 
					email: email, affiliation: affiliation, country: country, password: springSecurityService.encodePassword(password), enabled:isEnabled())
		} else {
			return null;
		}
	}
	*/
}
