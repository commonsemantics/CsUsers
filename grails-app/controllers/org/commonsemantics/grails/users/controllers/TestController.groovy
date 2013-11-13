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

import org.commonsemantics.grails.users.model.Role
import org.commonsemantics.grails.users.model.User
import org.commonsemantics.grails.users.utils.UserUtils

/**
 * @author Paolo Ciccarese <paolo.ciccarese@gmail.com>
 */
class TestController {

	static defaultAction = "index"
	
	def index = {
		render (view:'tests')
	}
	
	def testUserDisplayLensNoUser = {
		render (view:'user-show-lens', model:[label:'CsUser.01', description:'User\'s display lens with no user definition']);
	}
	
	def testUserDisplayLens = {
		render (view:'user-show-lens', model:[label:'CsUser.02', description:'User\'s display lens with user definition', 
			roles: Role.list(), userRoles:UserUtils.getUserRoles(User.list()[0]), user:User.list()[0]]);
	}
	
	def testUserProfileFieldsLensNoUser =  {
		render (view:'user-profile-edit-lens', model:[label:'CsUser.03', description:'User\'s profile edit lens with no user definition']);
	}
	
	def testUserProfileFieldsLens = {
		render (view:'user-profile-edit-lens', model:[label:'CsUser.04', description:'User\'s profile edit lens with user definition', user:User.list()[0]]);
	}
	
	def testUserAccountFieldsLensNoUser =  {
		render (view:'user-account-edit-lens', model:[label:'CsUser.05', description:'User\'s account edit lens with no user definition']);
	}
	
	def testUserAccountFieldsLens =  {
		render (view:'user-account-edit-lens', model:[label:'CsUser.06', description:'User\'s account edit lens with user definition', 
			roles: Role.list(), userRoles:UserUtils.getUserRoles(User.list()[0]), user:User.list()[0]]);
	}
	
	def testUserEditLensNoUser =  {
		render (view:'user-edit-lens', model:[label:'CsUser.07', description:'User\'s edit lens with no user definition']);
	}
	
	def testUserEditLens =  {
		render (view:'user-edit-lens', model:[label:'CsUser.08', description:'User\'s edit lens with user definition',
			roles: Role.list(), userRoles:UserUtils.getUserRoles(User.list()[0]), user:User.list()[0], msgError: 'error', msgWarning: 'warning']);
	}
}
