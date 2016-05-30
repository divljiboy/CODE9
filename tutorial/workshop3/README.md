Workshop3: Spring Data & Security
==============

----

Persistence: Spring Data
=============================
- JPA
- Spring Data JPA
- Spring Security

----

Java Persistence API (JPA)
=============================
![Entity Manager](https://gitlab.levi9.com/files/note/59/image1.png)

- Lightweight, POJO-based framework
- Consists of
	- JPA API
	- JP QL
	- Criteria API
	- ORM metadata (annotations, XML, configuration by exception)
- Basic concepts
	- Entity and EntityManager
	- Persistability
	- Identity
	- Transactionality
- Relationships
- Transaction Management
- Detachment and merging
- Spring JPA support is part of spring-orm
- Three options for JPA setup in Spring:
	- LocalEntityManagerFactoryBean
	- JNDI
	- LocalContainerEntityManagerFactoryBean


----

Lab11: Implement JDBC data access layer (45 min)
============================================================

What you will learn?
--------------------
- Configure and start MySQL database
- Connect your application to MySQL database
- Implement data access layer with plain SQL

Specific subjects you will gain experience with:
------------------------------------------------
- Datasource configuration
- Spring JDBC Template

Instructions
------------
- Start MySQL Windows process
- Connect using SQLYog application (community edition) [username: 'root', password: '']
- Create database code9
- Download and execute [code9-w3-l1.sql](https://gitlab.levi9.com/files/note/62/code9-w3-l1.sql) against code9 db
- Add maven dependencies to mysql-connector, spring-jdbc, spring-tx, javax.transaction-api, commons-lang3 
- Create _persistence-configuration.xml_ inside _ctx_ folder and define [DataSource](http://docs.spring.io/spring/docs/4.0.2.RELEASE/spring-framework-reference/htmlsingle/#jdbc-datasource) and [TransactionManager](http://docs.spring.io/spring/docs/4.0.2.RELEASE/spring-framework-reference/htmlsingle/#jdbc-DataSourceTransactionManager).
- Create rs.code9.taster.repository and rs.code9.taster.repository.jdbc packages
- Create CRUD interface for Category entity
- Create CategoryRepositoryImpl class which uses [JdbcTemplate](http://docs.spring.io/spring/docs/4.0.2.RELEASE/spring-framework-reference/htmlsingle/#dao-annotations) for data access.  
- Create  rs.code9.taster.service.impl package and implement Category service. Use repository you just implemented.
- Update component scan to scan repository package and service.impl.

----


Lab12: Spring Data JPA
=============================

----

- Part of Spring Data project
	- JPA
	- Apache Hadoop
	- GemFire
	- Redis
	- MongoDB
	- Neo4j
- Motivation – avoid boilerplate code!
- Repository
- Querying
	- Naming conventions

		```
			List<User> findByLastname(String lastName);
		```

	- @Query annotation

		```@Query(“select from User u where u.lastName = :lastName”)
		   List<User> findByLastname(String lastName);
		```

	- Querydsl predicates support
		```List<User> result = query.from(user)
		    .where(user.lastName.equals(lastName)).list(user); 
		```
- Paging support
- Configuration via jpa namespace
	
	```<jpa:repositories base-package=“rs.code9.badminton.repository”/>```

----

Configure Spring Data JPA (45 min)
=============================

----

What you will learn?
--------------------
- Add and configure JPA and Hibernate
- How to configure Spring Data JPA
- How to implement Spring Data Repositories
- How to work with transactions


Specific subjects you will gain experience with:
--------------------
- JPA
- Spring JPA support
- Spring Data JPA
- Repositories
- Spring’s declarative transaction management


Instructions:
-------------

- Add hibernate-core and hibernate-entity-manager dependencies.
- Define [LocalContainerEntityManagerFactoryBean](http://docs.spring.io/spring/docs/4.0.2.RELEASE/spring-framework-reference/htmlsingle/#orm-jpa-setup-lcemfb) and set packagesToScan property to your model repository.
- Use JPA2 annotations to map entities to tables
- Recreate code9 database (leave it empty)
- Start your application
- Add spring-data-jpa dependency
- Delete jdbc repository package
- Refactor repository interfaces to extend spring's Repository
- Add <jpa:repositories base-package="rs.code9.taster.repository"/> to persistence-configuration.xml
- Add transaction boundaries to the service layer (`@Transactional`)
- Try your application

	
Spring Security
=============================

----
![Spring Security - Filter Chain](https://gitlab.levi9.com/files/note/60/image4.png)
![Spring Security](https://gitlab.levi9.com/files/note/61/image5.png)

- Provides declarative security for Spring-based applications
- Handles authentication and authorization
- Takes full advantage of DI and AOP
- Fundamentals
	- Principal – user that performs the action
	- Authentication – confirming truth of credentials
	- Authorization – define access policy for principal
- Supports: form-based, HTTP, X.509, LDAP,…
- Configuration
	- Filter in web.xml

		    <filter>
			    <filter-name>springSecurityFilterChain</filter-name>
			    <filter-class>
			        org.springframework.web.filter.DelegatingFilterProxy
			    </filter-class>
			</filter>
			
			<filter-mapping>
			    <filter-name>springSecurityFilterChain</filter-name>
			    <url-pattern>/*</url-pattern>
			</filter-mapping>

	- Security namespace
	
		    <http auto-config='true'>
		        <intercept-url pattern="/**" access="ROLE_USER,ROLE_ADMIN" />
		        <remember-me key="Levi9Code9"/>
		    </http>
			
- AuthenticationManager and AuthenticationProvider
- Web Authorization


----

Lab13: Spring Security - Configuration (30 min)
=============================

----

What you will learn?
--------------------
- How to setup basic spring security context to protect your application
- How to login/logout users



Specific subjects you will gain experience with:
--------------------
- Spring Security configuration



Instructions:
-------------

- Add Spring Security dependency
- Configure filter chain in web.xml
- Add and configure spring-security.xml
- Create login page
- Setup logout link
- Add remember me



Lab14: Spring Security - Authorization (30 min)
=============================

----

What you will learn?
--------------------
- Authorization principles 
- How to implement access control in Spring Security.



Specific subjects you will gain experience with:
--------------------
- Spring Security Authorization



Instructions:
-------------

- Add Spring Security Taglib dependency
- Add authorize tag to hide parts of page
- Add authentication tag to display user data
- Test current setup

Code snippets
-------------

Maven dependency

    <dependency>
        <groupId>org.springframework.security</groupId>
        <artifactId>spring-security-taglibs</artifactId>
    </dependency>

Tags

    <sec:authorize access="hasRole('ROLE_ADMIN')">
	<sec:authentication property="principal.username"/>


Lab15: Spring Security - Method level security (30 min)
=============================

----

What you will learn?
--------------------
- How to implemented method level security



Specific subjects you will gain experience with:
--------------------
- Pre / Post and Secured annotations



Instructions:
-------------

- Modify application security configuration, add global method security.
- PreAuthorize / PostAuthorize and Secured annotations
- Annotate methods to be secured
- Test the application

Code snippets
-------------

Spring security configuration

    <global-method-security pre-post-annotations="enabled" />

Method

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public Category save(Category category) {