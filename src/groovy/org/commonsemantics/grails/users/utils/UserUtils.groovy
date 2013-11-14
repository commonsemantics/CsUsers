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

import org.commonsemantics.grails.users.model.Role
import org.commonsemantics.grails.users.model.User
import org.commonsemantics.grails.users.model.UserRole

/**
* @author Paolo Ciccarese <paolo.ciccarese@gmail.com>
*/
class UserUtils {

	static String getStatusLabel(User user) {
		if(user.isEnabled()) {
			 if(user.isAccountLocked()) return UserStatus.LOCKED_USER.value();
			 else return UserStatus.ACTIVE_USER.value();
		} else {
			return UserStatus.DISABLED_USER.value();
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

	static boolean isFieldMandatory(def grailsApplication, def fieldName) {
		println 'field: ' + fieldName
		def mandatoryByConfiguration =  Eval.me(grailsApplication.config.org.commonsemantics.grails.users.model.fields.mandatory);
		if(fieldName in User.mandatory || fieldName in mandatoryByConfiguration) {
			println 'mandatory: ' + fieldName;
			return true;
		}
		return false;
	}
}