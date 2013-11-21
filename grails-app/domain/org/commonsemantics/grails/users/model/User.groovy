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
package org.commonsemantics.grails.users.model

import org.commonsemantics.grails.agents.model.Person
import org.commonsemantics.grails.users.utils.UserStatus

/**
 * @author Paolo Ciccarese <paolo.ciccarese@gmail.com>
 */
class User {

	private static final int NAME_MAX_SIZE = 255;
	
	String id
	String username
	String password
	
	Person person;
	
//	// User's data
//	String email
//	String title
//	String firstName
//	String middleName
//	String lastName
//	String displayName
//	String country
//	String affiliation
	
	Date dateCreated, lastUpdated
	
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	
	static hasMany = [openIds: OpenId]
	
	static mandatory = []
	static optional = []

	static constraints = {
		id maxSize: 36
	
		username blank: false, unique: true, minSize:4, maxSize:16
		password (nullable: true, blank: true)
	}

	static mapping = {
		password column: '`password`'
		id generator:'uuid', sqlType: "varchar(36)"
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}
}
