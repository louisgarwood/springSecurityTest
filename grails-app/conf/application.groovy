import grails.plugin.springsecurity.SecurityConfigType

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'org.cisecurity.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'org.cisecurity.UserRole'
grails.plugin.springsecurity.authority.className = 'org.cisecurity.Role'
grails.plugin.springsecurity.requestMap.className = 'org.cisecurity.Requestmap'
grails.plugin.springsecurity.securityConfigType = SecurityConfigType.Requestmap


grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]

