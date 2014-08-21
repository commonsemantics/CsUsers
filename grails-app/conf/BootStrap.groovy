import org.commonsemantics.grails.agents.model.Person
import org.commonsemantics.grails.agents.model.Software
import org.commonsemantics.grails.users.model.ProfilePrivacy
import org.commonsemantics.grails.users.model.Role
import org.commonsemantics.grails.users.model.User
import org.commonsemantics.grails.users.model.UserRole
import org.commonsemantics.grails.users.utils.DefaultUsersProfilePrivacy
import org.commonsemantics.grails.users.utils.DefaultUsersRoles

class BootStrap {

	def grailsApplication
	def usersInitializationService

    def init = { servletContext ->
		
		demarcation(
			' COMMON SEMANTICS: USERS (v.' +
			grailsApplication.metadata['app.version'] + ", b." +
			grailsApplication.metadata['app.build'] + ")");
		separator();
		log.info  ' By Paolo Ciccarese (http://paolociccarese.info/)'
		log.info  ' Copyright 2014 Common Semantics'
		separator();
		log.info  ' Released under the Apache License, Version 2.0'
		log.info  ' url:http://www.apache.org/licenses/LICENSE-2.0'
		demarcation('>> Bootstrapping....');	
		demarcation('>> INITIALIZING DEFAULTS ENUMERATIONS');
		
		// USERS
		// ------
		separator('** Users Roles');
		usersInitializationService.initializeRoles();
		separator('** Users Profile Privacy');
		usersInitializationService.initializeProfilePrivacy();

		// ENTITIES
		// --------
		demarcation('>> INITIALIZING DEFAULTS ENTITIES');
		log.info  '** Users'
		
		def person = Person.findByEmail('paolo.ciccarese@gmail.com');
		if(person==null) {
			person = new Person(
				firstName: 'Jack', 
				lastName: 'White',
				displayName: 'Dr. White',
				email:'paolo.ciccarese@gmail.com'
			).save(failOnError: true);
		}
		
		
		def password = 'password'
		def adminUsername = 'admin'
		log.info  "Initializing: " + adminUsername
		def admin = User.findByUsername(adminUsername);
		if(admin==null) {
			admin = new User(username: adminUsername,
				password: password, person: person, firstName: 'Jack', lastName: 'White',
				profilePrivacy:  ProfilePrivacy.findByValue(DefaultUsersProfilePrivacy.PRIVATE.value()),
				displayName: 'Dr. White', enabled: true, email:'paolo.ciccarese@gmail.com').save(failOnError: true)
			log.warn  "CHANGE PASSWORD for: " + adminUsername + "!!!"
		}
		UserRole.create admin, Role.findByAuthority(DefaultUsersRoles.USER.value())
		UserRole.create admin, Role.findByAuthority(DefaultUsersRoles.MANAGER.value())
		UserRole.create admin, Role.findByAuthority(DefaultUsersRoles.ADMIN.value())
	
		
		log.info  '** Software '
		def name = 'Software Test';		
		log.info  "Initializing: " + name
		def software = Software.findByName(name);
		if(software==null) {
			software = new Software(
				ver: '1.0',
				name: name,
				displayName: 'Software Test display',
				description: 'Software Test description'
			).save(failOnError: true);
		}
		
		demarcation(">> Bootstrapping completed!")
		separator()
    }
	
	private demarcation() {
		log.info '========================================================================';
	}
	private demarcation(message) {
		demarcation();
		log.info  message
	}
	private separator() {
		log.info '------------------------------------------------------------------------';
	}
	private separator(message) {
		separator();
		log.info  message
	}
	private display(message) {
		log.info message
	}
	
    def destroy = {
    }
}
