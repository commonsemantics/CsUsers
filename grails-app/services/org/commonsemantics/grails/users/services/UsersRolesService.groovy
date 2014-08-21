/*
 * Copyright 2014 Common Semantics  (commonsemantics.org)
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

import org.commonsemantics.grails.users.model.Role
import org.commonsemantics.grails.users.utils.DefaultUsersRoles

/**
 * @author Paolo Ciccarese <paolo.ciccarese@gmail.com>
 */
class UsersRolesService {
	
	def grailsApplication;
	
	def register() {
		def enumeration = null;
		def enumerationClass = grailsApplication.config.org.commonsemantics.grails.users.roles
		if(enumerationClass!=null && enumerationClass.size()==1) {
			log.info "Selected enumeration roles " + enumerationClass
			enumeration = this.getClass().classLoader.findClass(enumerationClass)
		} else {
			log.info "Selected default enumeration roles"
			enumeration = DefaultUsersRoles;
		}
		
		enumeration.values().each {
			if(!Role.findByAuthority(it.value())) {
				new Role(authority: it.value(), ranking: it.ranking(), label: it.label(), description: it.description()).save(failOnError: true)
				log.info "Initialized: " + it.value()
			} else {
				log.info "Found: " + it.value()
			}
		}
	}
}