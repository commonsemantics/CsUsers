import grails.util.Metadata

// configuration for plugin testing - will not be included in the plugin zip

// Necessary for Grails 2.0 as the variable ${appName} is not available
// anymore in the log4j closure. It needs the import above.
def appName = Metadata.current.getApplicationName();

grails.config.locations = ["classpath:${appName}-config.properties", "file:./${appName}-config.properties"]

log4j = {	
	appenders {
		console name:'stdout', layout:pattern(conversionPattern: '%d{DATE} %5p %c{3} %m%n')
	}

    error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
           'org.codehaus.groovy.grails.web.pages', //  GSP
           'org.codehaus.groovy.grails.web.sitemesh', //  layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping', // URL mapping
           'org.codehaus.groovy.grails.commons', // core / classloading
           'org.codehaus.groovy.grails.plugins', // plugins
           'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'

    warn   'org.mortbay.log'
	
	info   'grails.app' 									// Necessary for Bootstrap logging
			
	debug  'grails.app.controllers.org.commonsemantics.grails.agents.controllers.TestsController',
		   'grails.app.services.org.commonsemantics.grails.agents.AgentsService',
		   'org.commonsemantics.grails.agents.utils',
		   'grails.app.controllers.org.commonsemantics.grails.users.controllers.TestsController',
		   'org.commonsemantics.grails.users.utils'
}