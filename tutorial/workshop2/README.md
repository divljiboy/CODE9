Workshop2: Web
==============

----

Homework (30 min)
-----------------

* Discuss homework implementation

* equals() and hashCode() implementation

* Converters

----



Lab7: Spring Web Forms & Validation (60 min)
============================================

----

What you will learn?
--------------------

1. What is data binding

2. How to perform data [validation](http://docs.spring.io/spring/docs/4.0.2.RELEASE/spring-framework-reference/htmlsingle/#validation-beanvalidation)

3. How to display error messages


Specific subjects you will gain experience with
-----------------------------------------------

1. Bean Validation [JSR-303/JSR-349](http://beanvalidation.org/)

2. [Spring form tag library](http://docs.spring.io/spring/docs/4.0.2.RELEASE/spring-framework-reference/htmlsingle/#view-jsp-formtaglib)

3. Usage of [DTOs](http://martinfowler.com/eaaCatalog/dataTransferObject.html) agains domain objects


Instructions
------------

1. Add Maven dependencies for JSR-349 and [Hibernate validator](http://hibernate.org/validator/documentation/)

2. [Add validation annotations](http://docs.jboss.org/hibernate/validator/5.1/reference/en-US/html/chapter-bean-constraints.html#table-spec-constraints)

3. [Configure JSR-349 Validator for use by Spring MVC](http://docs.spring.io/spring/docs/4.0.2.RELEASE/spring-framework-reference/htmlsingle/#validation-mvc-jsr303)

4. [Add form:error validation messages](http://docs.spring.io/spring/docs/4.0.2.RELEASE/spring-framework-reference/htmlsingle/#view-jsp-formtaglib-errorstag)

5. Add styling for error messages

----



Lab8: Tiles/JSP/JSTL (60 min)
=============================

----

What you will learn?
--------------------

1. How to decouple application context configuration files

2. What is standard layout

    ![LAYOUT](https://dl.dropboxusercontent.com/u/11650317/layout.png)


Specific subjects you will gain experience with
-----------------------------------------------

1. [Composite View pattern](https://tiles.apache.org/framework/tutorial/pattern.html) and [View Helper pattern](http://www.oracle.com/technetwork/java/viewhelper-139885.html)

2. [Apache Tiles](http://tiles.apache.org/)


Instructions
------------

1. Move Spring core configuration to bean-config.xml and Spring MVC configuration to mvc-config.xml; remove unneeded namespaces

2. Update application-config.xml to include other configuration files as classpath resources

3. Add tiles dependency

4. Add tiles configuration XML under /src/main/webapp/WEB-INF/tiles-def

5. [Add tiles configurer and view resolver beans](http://docs.spring.io/spring/docs/4.0.2.RELEASE/spring-framework-reference/htmlsingle/#view-tiles)
    - org.springframework.web.servlet.view.tiles3.TilesConfigurer
    - org.springframework.web.servlet.view.tiles3.TilesViewResolver

6. [Create main page layout](http://tiles.apache.org/framework/tutorial/basic/concepts.html)
    - Configure main page layout definition in tiles-def/tiles.xml
    - Create mainLayout.jsp

7. Refactor existing pages
    - Configure tiles definition for each page
    - Refactor JSP files for each page to conform tiles layout definition

8. Redirect from index.jsp to home view

9. Add 404 and exception error pages

----



Lab9: HTML5/CSS3/jQuery (30 min)
================================

----

What you will learn?
--------------------

1. How to use CSS to control the style and layout


Specific subjects you will gain experience with
-----------------------------------------------

1. [CSS](http://www.w3schools.com/css/)

2. [jQuery](http://jquery.com/)


Instructions
------------

1. Add static resources - CSS, JavaScript, Images

2. [Configure static resource resolver](http://docs.spring.io/spring/docs/4.0.2.RELEASE/spring-framework-reference/htmlsingle/#mvc-config-static-resources)

3. Use jQuery to set initial focus on text input elements when any page loads

4. Refactor each page to new design

5. Add favicon http://www.levi9.com/wp-content/themes/levi9/favicon.ico

----



Lab10: Internationalization (30 min)
====================================

----

What you will learn?
--------------------

1. [i18n vs l10n](https://www.w3.org/International/questions/qa-i18n.en)


Specific subjects you will gain experience with
-----------------------------------------------

1. [Spring support for i18n using MessageSource](http://docs.spring.io/spring/docs/4.0.2.RELEASE/spring-framework-reference/htmlsingle/#context-functionality-messagesource)


Instructions
------------

1. Add message resource bundle configuration and replace hard-coded labels with messages

2. Add i18 resolver

3. [Add i18n change interceptor](http://docs.spring.io/spring/docs/4.0.2.RELEASE/spring-framework-reference/htmlsingle/#mvc-localeresolver) 

----


Additional exercises, homework:
===============================

1. Re-work controllers which use domain models instead of DTOs

2. Use [Spring MVC test support](http://docs.spring.io/spring/docs/4.0.2.RELEASE/spring-framework-reference/htmlsingle/#spring-mvc-test-framework) for testing MVC
