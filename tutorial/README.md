## Tutorial
The purpose of this tutorial is to present how to build modern Java Web Applications using Spring framework together with standard set of libraries/frameworks we in Levi9 use in every day work.

# Introduction
This tutorial consists of code labs and comprehensive instructions and explanations. Labs are organized into workshops, where instructions are provided on the level of whole workshop (for all labs within the workshop). 

For each lab we provide two projects:
 - begin: you can start from it and implemented required
 - end: you can use this one to see one possible solution
 
# Show-case application
We will built simplified version of the [Taster application](taster/) as part of this tutorial. The taster application will be used for testing of candidates who apply for a job in Levi9.

The taster application consists of:
 - Backend: administration area where it is possible to manage tests
 - Frontend: end user area used to fill in test 
 
For full list of functionalities please take a look [here](taster/).

# Basic layout



Categories:

![Categories](https://gitlab.levi9.com/d.gajic/code9/raw/master/img/categories.png)



Add/Edit category:

![Add/Edit category](https://gitlab.levi9.com/d.gajic/code9/raw/master/img/addCategory.png)


# Workshops
The labs are organized into following workshops where each one covers set of specific subjects:

1.	[workshop1: Introduction: Maven, Eclipse, Spring](tutorial/workshop1/)
2.	[workshop2: Web: Spring Core and MVC, JSP/JSTL, Tiles, Validation, i18n](tutorial/workshop2/)
3.	[workshop3: Persistence: Spring Data & Security](tutorial/workshop3/)
4.	[workshop4: Turn the application to SPA: REST & Backbone](tutorial/workshop4/)

In the first workshop we will cover basics including tooling and spring framework. You will learn what is Java Web application, how we can build it using maven. Besides that we will also show how to setup spring MVC project and how to implement basic CRUD.

Second workshop is about front end technologies including general ones like HTML/CSS and Java/Spring related like JSP/JSTL/Spring form tags/Tiles. We will also do more examples on standard CRUD functionalities including validation and internationalization. 

In third workshop we will address data persistence using Spring Data JPA. We will also talk about securing web applications using Spring Security.

In the last workshop we will show data-centric approach where we will refactor our web layer to expose data via REST API. Instead of JSP/JSTL front-end we will build JavaScript Single Page Application which consumes REST API services.
