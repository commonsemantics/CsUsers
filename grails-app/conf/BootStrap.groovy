import org.commonsemantics.grails.agents.model.Person;
import org.commonsemantics.grails.users.model.Role
import org.commonsemantics.grails.users.model.User
import org.commonsemantics.grails.users.model.UserRole
import org.commonsemantics.grails.users.utils.DefaultUsersRoles

class BootStrap {

	def grailsApplication
	
    def init = { servletContext ->
		
		separator();
		log.info  '>> INITIALIZING DEFAULTS'
		separator();
		log.info  '** Users Roles'
		
		DefaultUsersRoles.values().each {
			log.info  '** ' + it.value()
			if(!Role.findByAuthority(it.value())) {
				new Role(authority: it.value(), ranking: it.ranking(), label: it.label(), description: it.description()).save(failOnError: true)
				log.info "Initialized: " + it.value()
			}
		}
		
		separator();
		log.info  '>> USERS'
		separator();
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
				displayName: 'Dr. White', enabled: true, email:'paolo.ciccarese@gmail.com').save(failOnError: true)
			log.warn  "CHANGE PASSWORD for: " + adminUsername + "!!!"
		}
		UserRole.create admin, Role.findByAuthority(DefaultUsersRoles.USER.value())
		UserRole.create admin, Role.findByAuthority(DefaultUsersRoles.MANAGER.value())
		UserRole.create admin, Role.findByAuthority(DefaultUsersRoles.ADMIN.value())
	
		
		separator();

    }
	def separator = {
		log.info  '------------------------------------------------------------------------';
	}
    def destroy = {
    }
}
