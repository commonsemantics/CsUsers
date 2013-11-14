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
	
	def usersService;
	
	def index = {
		render (view:'tests')
	}
	
	def testUserDisplayLensNoUser = {
		render (view:'user-show-lens', model:[label:'CsUser.01', description:'User\'s display lens with no user definition']);
	}
	
	def testUserDisplayLens = {
		render (view:'user-show-lens', model:[label:'CsUser.02', description:'User\'s display lens', 
			roles: Role.list(), userRoles:usersService.getUserRoles(User.list()[0]), user:User.list()[0]]);
	}
	
	def testUserProfileFieldsLensNoUser =  {
		render (view:'user-profile-edit-lens', model:[label:'CsUser.03', description:'User\'s profile edit lens with no user definition']);
	}
	
	def testUserProfileFieldsLens = {
		render (view:'user-profile-edit-lens', model:[label:'CsUser.04', description:'User\'s profile edit lens', user:User.list()[0]]);
	}
	
	def testUserAccountFieldsLensNoUser =  {
		render (view:'user-account-edit-lens', model:[label:'CsUser.05', description:'User\'s account edit lens with no user definition']);
	}
	
	def testUserAccountFieldsLens =  {
		render (view:'user-account-edit-lens', model:[label:'CsUser.06', description:'User\'s account edit lens', 
			roles: Role.list(), userRoles:usersService.getUserRoles(User.list()[0]), user:User.list()[0]]);
	}
	
	def testUserEditLensNoUser =  {
		render (view:'user-edit-lens', model:[label:'CsUser.07', description:'User\'s edit lens with no user definition']);
	}
	
	def testUserEditLens =  {
		render (view:'user-edit-lens', model:[label:'CsUser.08', description:'User\'s edit lens',
			roles: Role.list(), userRoles:usersService.getUserRoles(User.list()[0]), user:User.list()[0]]);
	}
	
	def testUserEditLensWithError = {
		render (view:'user-edit-lens', model:[label:'CsUser.09', description:'User\'s edit lens with error ',
			roles: Role.list(), userRoles:usersService.getUserRoles(User.list()[0]), user:User.list()[0], msgError: 'test error message']);
	}
	
	def testUserEditLensWithLongError = {
		render (view:'user-edit-lens', model:[label:'CsUser.10', description:'User\'s edit lens with long error ',
			roles: Role.list(), userRoles:usersService.getUserRoles(User.list()[0]), user:User.list()[0], msgError: 'test error message test error message test error message test error message test error message test error message test error message test error message']);
	}
	
	def testUserEditLensWithWarning = {
		render (view:'user-edit-lens', model:[label:'CsUser.11', description:'User\'s edit lens with warning',
			roles: Role.list(), userRoles:usersService.getUserRoles(User.list()[0]), user:User.list()[0], msgWarning: 'test warning message']);
	}
	
	def testUserEditLensWithLongWarning = {
		render (view:'user-edit-lens', model:[label:'CsUser.12', description:'User\'s edit lens with long warning',
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
		
		render (view:'user-edit-lens', model:[label:'CsUser.13', description:'User\'s edit lens with field value error',
			roles: Role.list(), userRoles:usersService.getUserRoles(user), user:user]);
	}
}
