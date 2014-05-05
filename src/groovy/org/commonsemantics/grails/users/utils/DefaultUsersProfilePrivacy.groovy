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
package org.commonsemantics.grails.users.utils

/**
 * These are the default entries for users roles. The entries are supposed 
 * to be sat up in the Bootstrap.groovy file as follows:
 * 
 * log.info  '** Users roles'
 * DefaultUsersPrivacy.values().each {
 *      if(!Role.findByAuthority(it.value())) {
 *           new Role(authority: it.value(), ranking: it.ranking(), label: it.label(), description: it.description()).save(failOnError: true)
 *           log.info "Initialized: " + it.value()
 *      }
 * }
 * 
 * Alternative users roles can be defined with a similar approach.
 * 
 * @author Paolo Ciccarese <paolo.ciccarese@gmail.com>
 */
enum DefaultUsersProfilePrivacy {
	PUBLIC("PROFILE_PRIVACY_PUBLIC", 
		"Public", 
		"The profile can be browsed by anybody who has access to the system."), 
	RESTRICTED("PROFILE_PRIVACY_RESTRICTED",
		"Restricted", 
		"The profile will be accessible to whoever has access to the content."),
	PRIVATE("PROFILE_PRIVACY_PRIVATE",
		"Private",
		"Only username will be accessible to whoever has access to the content."),
	ANONYMOUS("PROFILE_PRIVACY_ANONYMOUS", 
		"Anonymous", 
		"No personal data will be available, not even the username.");
	
	DefaultUsersProfilePrivacy(String value, String label, String description) { 
		this.value = value; 
		this.label = label;	
		this.description = description;
	}
	
	private String value
	private String label
	private String description
	
	public String value() { return value }
	public String label() { return label }
	public String description() { return description }
}
