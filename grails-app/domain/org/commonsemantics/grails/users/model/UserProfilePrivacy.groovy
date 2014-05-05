/*
 * Copyright 2014  Common Semantics (commonsemantics.org)
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

import org.apache.commons.lang.builder.HashCodeBuilder

/**
 * @author Paolo Ciccarese <paolo.ciccarese@gmail.com>
 */
class UserProfilePrivacy implements Serializable {

	User user
	ProfilePrivacy profilePrivacy

	boolean equals(other) {
		if (!(other instanceof UserProfilePrivacy)) {
			return false
		}

		other.user?.id == user?.id &&
			other.profilePrivacy?.id == profilePrivacy?.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		if (user) builder.append(user.id)
		if (profilePrivacy) builder.append(profilePrivacy.id)
		builder.toHashCode()
	}

	static UserProfilePrivacy get(long userId, long profilePrivacyId) {
		find 'from UserProfilePrivacy where user.id=:userId and profilePrivacy.id=:profilePrivacyId',
			[userId: userId, profilePrivacyId: profilePrivacyId]
	}

	static UserProfilePrivacy create(User user, ProfilePrivacy profilePrivacy, boolean flush = true) {
		new UserProfilePrivacy(user: user, profilePrivacy: profilePrivacy).save(flush: flush, insert: true)
	}

	static boolean remove(User user, ProfilePrivacy profilePrivacy, boolean flush = false) {
		UserProfilePrivacy instance = UserProfilePrivacy.findByUserAndProfilePrivacy(user, profilePrivacy)
		if (!instance) {
			return false
		}

		instance.delete(flush: flush)
		true
	}

	static void removeAll(User user) {
		executeUpdate 'DELETE FROM UserProfilePrivacy WHERE user=:user', [user: user]
	}

	static void removeAll(ProfilePrivacy profilePrivacy) {
		executeUpdate 'DELETE FROM UserProfilePrivacy WHERE profilePrivacy=:profilePrivacy', [profilePrivacy: profilePrivacy]
	}

	static mapping = {
		id composite: ['profilePrivacy', 'user']
		version false
	}
}
