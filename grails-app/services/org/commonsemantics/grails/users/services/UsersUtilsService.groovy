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

import org.commonsemantics.grails.users.model.Role
import org.commonsemantics.grails.users.model.User
import org.commonsemantics.grails.users.model.UserRole

/**
 * @author Paolo Ciccarese <paolo.ciccarese@gmail.com>
 */
class UsersUtilsService {
	
	



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
	
	def listRoles(def user, def max, def offset, def sort, def _order) {
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
	
	def getUserRoles(def user) {
		def userRoles = []
		def ur = UserRole.findAllByUser(user)
		println ur
		ur.each { userRoles.add(it.role)}
		return userRoles;
	}
	
	def getUserCircles(def user) {
		def ur = []
		/*
		def userCircles = []
		ur = UserCircle.findAllByUser(user)
		ur.each { userCircles.add(it.circle)}
		*/
		return ur;
	}
	
	def getUserCommunities(def user) {
		def ur = []
		/*
		def userCommunities = []
		ur = UserCommunity.findAllByUser(user)
		ur.each { userCommunities.add(it.community)}
		*/
		return ur;
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
	

	def listSystems(def max, def offset, def sort, def _order) {
		
		def systems = [];
		def systemsCount = [:]
		SystemApi.list().each { system ->
			systemsCount.put (system.id, UserSystemApi.findAllWhere(system: system).size())
		}
		def systemsStatus = [:]
		SystemApi.list().each { system ->
			systemsStatus.put (system.id, system.enabled)
		}
		
		if (sort == 'systemsCount') {
			systemsCount = systemsCount.sort{ a, b -> a.value <=> b.value }
			if(_order == "desc")
				systemsCount.each { groupCount ->
					systems.add SystemApi.findById(groupCount.key);
				}
			else
				systemsCount.reverseEach { groupCount ->
					systems.add SystemApi.findById(groupCount.key);
				}
		} else if (sort == 'status') {
			systemsStatus = systemsStatus.sort{ a, b -> a.value.compareTo(b.value) }
			if(_order == "desc")
				systemsStatus.each { groupStatus ->
					systems.add Group.findById(groupStatus.key);
				}
			else
				systemsStatus.reverseEach { groupStatus ->
					systems.add Group.findById(groupStatus.key);
				}
		} else {
			systems = SystemApi.withCriteria {
				maxResults(max?.toInteger())
				firstResult(offset?.toInteger())
				order(sort, _order)
			}
		}
		
		[systems, systemsCount]
	}
	

	
	def listSystemAdministrators(def system, def _max, def _offset, def sort, def _order) {
		
		def admins = UserSystemApi.findAllBySystem(system);
		[admins, admins.size()] ;
	}
	
	
	
	
	def listSystemUsers(def system, def _max, def _offset, def sort, def _order) {
		system.users
	}
	

}
