Workshop1: Introduction to Maven, Eclipse, Spring
=================================================

----

Basic concepts
--------------


*	Java programming language/platform
  
*	[Java Servlet](https://jcp.org/en/jsr/detail?id=340) and [JSP Specifications](https://jcp.org/en/jsr/detail?id=245)
  
*	[Spring](http://spring.io/docs)

*	[Maven](http://maven.apache.org/)

*	[Eclipse](https://www.eclipse.org/)

----
 
What is a web application?
--------------------------


Classical web - server side generated HTML:
![Server side generated HTML](https://gitlab.levi9.com/d.gajic/code9/raw/master/img/web-centric.png)


Data centric approach - server side generated data:
![Data Centric](https://gitlab.levi9.com/d.gajic/code9/raw/master/img/data-centric.png)

----


Dependency injection
--------------------


### The problem
![DI](https://gitlab.levi9.com/d.gajic/code9/raw/master/img/di.png)

*	dependent consumer, dependency and injector/container

### Example
```java
public interface ScoreService {
	
	int score(TestInstance test);
}

public interface TestService {
	
	TestInstance create();
	
	void save(TestInstance test);
}

public class TestServiceImpl implements TestService {

	...

	@Override
	public void save(TestInstance test) {
		int score = scoreService.score(test);
		...
	}
}
```

### Coupling

```java
public class TestServiceImpl implements TestService {

	private ScoreService scoreService = new ScoreServiceImpl();

	@Override
	public void save(TestInstance test) {
		int score = scoreService.score(test);
		...
	}
}

public class MyApplication {
    public static void main(String[] args) {
    	TestService testService = new TestServiceImpl();
    	TestInstance test = testService.create();
    	...
    	testService.save(test);
    }
}
```

### Manually injected

```java
public class TestServiceImpl implements TestService {

	private final ScoreService scoreService;
	
	public TestServiceImpl(ScoreService scoreService) {
		this.scoreService = scoreService;
	}

	@Override
	public void save(TestInstance test) {
		int score = scoreService.score(test);
		...
	}
} 

public class MyApplication {
    public static void main(String[] args) {
    	ScoreService scoreService = new ScoreServiceImpl();
    	TestService testService = new TestServiceImpl(scoreService);
    	TestInstance test = testService.create();
    	...
    	testService.save(test);
    }
}
```

### Container automated DI

```java
@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private ScoreService scoreService;	

	@Override
	public void save(TestInstance test) {
		int score = scoreService.score(test);
		...
	}
} 

public class MyApplication {
    public static void main(String[] args) {
    	TestService testService = new TestServiceImpl();
    	TestInstance test = testService.create();
    	...
    	testService.save(test);
    }
}
```

### [Reading: Inversion of Control Containers and the Dependency Injection pattern](http://martinfowler.com/articles/injection.html)

----

Spring framework
----------------


![Spring runtime](https://gitlab.levi9.com/d.gajic/code9/raw/master/img/spring-overview.png) 

----

<a name="maven"></a>Maven
-------------------------


*	[Build tool](http://maven.apache.org/users/index.html)

*	[Convention over configuration](http://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html)

*	[POM](http://maven.apache.org/pom.html)

*	[Dependency management](http://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html)

*	[Build life-cycle](http://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html)

*	[IDE integration (m2e)](https://www.eclipse.org/m2e/)

----

High Level Architecture
-----------------------


![HLA](https://gitlab.levi9.com/d.gajic/code9/raw/master/img/hla.png)

----


  
Lab1: Hello World! (15 min)
===========================

----

What you will learn?
--------------------

1. How to start web project

2. How to use maven archetype

3. What it' in there
![WAR](https://gitlab.levi9.com/d.gajic/code9/raw/master/img/war.png)

4. How to deploy and see results


Specific subjects you will gain experience with
-----------------------------------------------

1. [Eclipse and WTP](http://www.eclipse.org/webtools/)

2. [Maven](#maven) 


Instructions
------------

1. Use new project wizard to create Maven project from maven-archetype-webapp

2. Define new server (Apache Tomcat 7) using WTP Server Tools

3. Start the application and navigate browser to [http://localhost:8080/lab1/](http://localhost:8080/lab1/)

---------------------------------------------------------------------------------------------------



Lab2: Create model class (10 min)
=================================

----

What you will learn?
--------------------

1. [What is POJO?](http://www.martinfowler.com/bliki/POJO.html)

2. How to model a category 

3. How to use eclipse to generate source code


Specific subjects you will gain experience with
-----------------------------------------------

1. Organizing source packages 

2. Building domain model 


Instructions
------------

1. Create Category class in the appropriate package

2. Generate getters/setters


Reading
-------

[Anemic Domain Model](http://www.martinfowler.com/bliki/AnemicDomainModel.html)

---------------------------------------------------------------------------------------------------



Lab3: Create service class (20 min)
===================================

----

What you will learn?
--------------------

1. What is a service class

2. How to use interfaces

3. What is CRUD

4. What are naming conventions

4. How to configure maven plugins 


Specific subjects you will gain experience with
-----------------------------------------------

1. Programming to interfaces and separation of concerns

2. [Maven plugins](http://maven.apache.org/guides/mini/guide-configuring-plugins.html)


Instructions
------------

1. Create CategoryService interface

2. Configure maven-compiler plugin for Java 7

3. Create in-memory implementation for CategoryService

---------------------------------------------------------------------------------------------------



Lab4: Test service class (15 min)
=================================

----

What you will learn?
--------------------

1. How to write JUnit test

2. How to run JUnit test from within Eclipse

3. How to run tests as part of Maven build lifecycle
 

Specific subjects you will gain experience with
-----------------------------------------------

1. [JUnit](http://junit.org/)

2. [Maven dependency management](https://github.com/junit-team/junit/wiki/Download-and-Install)


Instructions
------------

1. Add JUnit dependency

2. Create InMemoryCategoryServiceTest JUnit test using Eclipse wizard

3. Prepare input data using [test fixtures](https://github.com/junit-team/junit/wiki/Test-fixtures)

4. Write test for each of the methods from InMemoryCategoryService; check using [assertions](https://github.com/junit-team/junit/wiki/Assertions) 

---------------------------------------------------------------------------------------------------



Lab5: MVC - add Spring (30 min)
===============================

----

What you will learn?
--------------------

1. How to configure Spring dependencies

2. How to setup Spring MVC

3. How to load Spring configuration

4. How to configure logging via slf4j
 

Specific subjects you will gain experience with
-----------------------------------------------

1. MVC 
![MVC](https://gitlab.levi9.com/d.gajic/code9/raw/master/img/dispatcher.png)

2. [Maven dependency management](http://docs.spring.io/spring/docs/4.0.2.RELEASE/spring-framework-reference/htmlsingle/#overview-maven-dependency-management)

3. [Spring configuration via XML context files](http://docs.spring.io/spring/docs/4.0.2.RELEASE/spring-framework-reference/htmlsingle/#mvc-config)

4. [Logging via slf4j facade](http://www.slf4j.org/)


Instructions
------------

1. Add spring dependencies to project POM

2. Create Spring context file, add bean and MVC namespaces

3. Configure Spring MVC, [view resolver](http://docs.spring.io/spring/docs/4.0.2.RELEASE/spring-framework-reference/htmlsingle/#view-jsp-resolver) and [simple view controller](http://docs.spring.io/spring/docs/4.0.2.RELEASE/spring-framework-reference/htmlsingle/#mvc-config-view-controller)

4. Create jsp home page

5. [Configure Spring context loading via web.xml event listener](http://docs.spring.io/spring/docs/4.0.2.RELEASE/spring-framework-reference/htmlsingle/#context-create)

6. Upgrade web.xml to servlet 3.0 spec

7. Configure Spring’s MVC Dispatcher Servlet (Front controller)  

8. [Configure logging](http://docs.spring.io/spring/docs/4.0.2.RELEASE/spring-framework-reference/htmlsingle/#overview-logging)


Reading
-------

[Spring Framework Reference Documentation](http://docs.spring.io/spring/docs/4.0.2.RELEASE/spring-framework-reference/htmlsingle/)
 
---------------------------------------------------------------------------------------------------



Lab6: CRUD (30 min)
=====================

----

What you will learn?
--------------------

1. How to write controller

2. How to do request mapping

3. How to use Dependency Injection

4. How to write view using JSP/JSTL/Spring forms


Specific subjects you will gain experience with
-----------------------------------------------

1. [JSP](https://jcp.org/en/jsr/detail?id=245)

2. [JSTL](https://jstl.java.net/download.html)

3. [Spring forms taglib](http://docs.spring.io/spring/docs/4.0.2.RELEASE/spring-framework-reference/htmlsingle/#view-jsp-formtaglib)

4. [Spring MVC](http://docs.spring.io/spring/docs/4.0.2.RELEASE/spring-framework-reference/htmlsingle/#mvc-introduction)

5. [Spring IoC](http://docs.spring.io/spring/docs/4.0.2.RELEASE/spring-framework-reference/htmlsingle/#beans)


Instructions
------------

1. Add servlet API and JSTL dependencies

2. InMemoryCategoryService as spring managed bean (@Service, context:component-scan)

3. Create CategoryController

4. Inject CategoryService

5. Define request mappings (@RequestMapping)

6. Configure Spring MVC to use annotations (<mvc:annotation-driven/>) 

7. Add views

 

