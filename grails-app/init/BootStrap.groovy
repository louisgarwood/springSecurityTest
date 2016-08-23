
import org.cisecurity.User
import org.cisecurity.Role
import org.cisecurity.UserRole
import org.cisecurity.Requestmap

class BootStrap {

    def init = { servletContext ->

    	if(!User.findByUsername('admin')){
    		def admin = new User(username: 'admin', password: 'admin', enabled: true, accountExpired: false, accountLocked: false).save(failOnError: true)
	        def user = new User(username: 'user', password: 'user', enabled: true, accountExpired: false, accountLocked: false).save(failOnError: true)

	        def roleUser = new Role(authority: 'ROLE_USER').save(failOnError: true)
	        def roleAdmin = new Role(authority: 'ROLE_ADMIN').save(failOnError: true)

	        UserRole.create(user, roleUser)
	        UserRole.create(admin, roleAdmin)

	        for (String url in [
                '/',
                '/error',
                '/index',
                '/index.gsp',
                '/**/favicon.ico',
                '/shutdown',
                '/assets/**',
                '/**/js/**',
                '/**/css/**',
                '/**/images/**',
                '/login',
                '/login.*',
                '/login/*',
                '/logout',
                '/logout.*',
                '/logout/*']) {
	            new Requestmap(url: url, configAttribute: 'permitAll').save()
	        }
	        new Requestmap(url: '/requestmap/**', configAttribute: 'ROLE_ADMIN').save()
	        new Requestmap(url: '/user/**', configAttribute: 'ROLE_ADMIN').save()
	        new Requestmap(url: '/role/**', configAttribute: 'ROLE_ADMIN').save()
	        new Requestmap(url: '/userRole/**', configAttribute: 'ROLE_ADMIN').save()
    	}
    	

    	
    }
    def destroy = {
    }
}
