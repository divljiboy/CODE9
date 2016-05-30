Workshop4: Turn the application into SPA: REST & Backbone
=========================================================



----

Lab16: Multi-module maven project
=================================

----

What you will learn?
--------------------

1. How to organize complex projects



Specific subjects you will gain experience with
-----------------------------------------------

1. [Multi-module maven projects](http://books.sonatype.com/mvnex-book/reference/multimodule.html) 


Instructions
------------

1. Create new maven project (skip archetype selection)
2. Change packing type to pom
3. Add core module (lab16-end-core) with packaging = jar
4. Add web module (lab16-end-web) with packaging = war
5. Redistribute packages to corresponding module
6. Move dependencies to dependency management section of the parent pom
7. Set correct dependencies of submodules
8. Set build/final name
9. Move configuration
10. Move webapps folder content

-----------------------------------------------


Lab17: REST-API
=================================

----

A RESTful web service (also called a RESTful web API) is a web service implemented using HTTP and the principles of REST.  It is a collection of resources, with the following defined aspects:
1. the base URI for the web service
2. the Internet media type of the data supported by the web service (JSON and/or XML)
3. the set of operations supported by the web service using HTTP methods (e.g., GET, PUT, POST, or DELETE)

#### Vocabulary re-use principle

HTTP has a rich vocabulary of methods:

- GET
- POST
- PUT
- DELETE

The vocabulary re-use principle means that API will be designed in the way we need only two urls and the CRUD operations can be achieved via different HTTP methods:

- one for collections: /categories
- one for element: /categories/{ID}, where {ID} represents an identifier for the catgory such as 2

What you will learn?
--------------------

1. [What is REST](http://martinfowler.com/articles/richardsonMaturityModel.html)
2. [How to expose data via REST/JSON using Spring MVC](https://spring.io/guides/tutorials/rest/)

Specific subjects you will gain experience with
-----------------------------------------------

1. [Spring REST support](http://docs.spring.io/spring/docs/4.0.3.RELEASE/spring-framework-reference/htmlsingle/#mvc-ann-restcontroller)
2. [How to test REST API using browser](https://chrome.google.com/webstore/detail/advanced-rest-client/hgmloofddffdnphfgcellkdfbfbjeloo/reviews?hl=en-US&utm_source=ARC)

Instructions
------------

1. Add maven submodule for REST (skip archetype selection)
2. Start Spring MVC dispatcher (web.xml)
3. Configure Spring context
4. Write category resource class
5. Write controller (use @RestController) annotation
6. Add jackson dependencies 

----



Single page applications(SPA)
=============================

What are the reasons for making SPA?

Reasons:
1. _"My manager/architect/team lead told me so"_
2. _"I want to follow the hype"_ and/or _"I want to be trendy"_

or some really "good" reasons

* _Rich interactions_ - I want to be able to react on client side events, not on server side
* _Responsiveness_ - do not reload whole page, if you need "new" info on just one part
* and final one that I will just give you a glimpse of it at the end - re-use on multi-platform environments


Some of the examples SPA:

![SPA 1. - ToDo](https://gitlab.levi9.com/d.gajic/code9/raw/master/img/todos.jpg)

![SPA 2. - DocumentCloud](https://gitlab.levi9.com/d.gajic/code9/raw/master/img/dc-workspace.jpg)

![SPA 3. - USA Today](https://gitlab.levi9.com/d.gajic/code9/raw/master/img/usa-today.jpg)

![SPA 4. - Foursquare](https://gitlab.levi9.com/d.gajic/code9/raw/master/img/foursquare.jpg)

The problem
-----------

- **"Spaghetti code"**

Writing Web application from scratch on client side, usually means that there are a lot of DOM manipulations based on _jQuery_.
For any mid-size or complex application this will easily end up in writing **spaghetti** code.

![Example 1. - simple modal dialog](https://gitlab.levi9.com/d.gajic/code9/raw/master/img/dialog-modal.png)

- **"Good programming model"**

We always strive to achieve "best practice" in organization & structure the code so multiple developers can simultaniously work on single code base.

JavaScript MVC frameworks overview
----------------------------------

![JavaScript MVC frameworks](https://gitlab.levi9.com/d.gajic/code9/raw/master/img/js-mvc-frameworks.jpg)

----


BackboneJS
----------

----

When working with web application that use lot of JavaScript, among first things you will learn is that you will stop tying data from server into DOM. Easily you can end up of having "endless" jQuery selectors, callbacks trying to sync state between data, HTML, UI and your JS logic.

Promise of BackboneJS as MV* framework is to separate concerns.

Data coming from server will be treated as _Model's_.
Model can be created, validated, destroyed and saved to server.

Representation of models and handling user events, responsibility "lies in hands" of BackboneJS _View's_.

Just please keep in mind, _View_ might not necessary be whole screen of your browser. It can be any visual element, ul, p, div, ...

And finally, we have a _Collection_ as group of models.

Lab18 - BackboneJS (10 min)
================================

----

What you will learn?
--------------------

- What is Responsive Layout? How to use Twitter Bootstrap to have responsive CSS framework out of the box?
- What are "hard" dependencies of BackboneJS?
- How to make "wire-up" BackboneJS


Specific subjects you will gain experience with
-----------------------------------------------

- HTML5
- CSS3
- JS
- Twitter Bootstrap 3
- UnderscoreJS
- BackboneJS

Instructions
------------

1. Open project lab18-begin, and look structure of the project. There are following folders: css, font, js and file index.html.
2. Open index.html and inspect organization of code in index.html. Explain CSS, JS
3. Add underscore.js & backbone.js
4. How to test (e.g. underscore)?

FF browser, open Console (hit F12) in console enter following lines

```js
var testArray = [1, 3, 5, 7];

_.each(testArray, function(data) { console.log(data) });
```

---------------------------------------------------------------------------------------------------


Lab19 - Working with BackboneJS (1h 30min)
==========================================

----


What you will learn?
--------------------

- Key concepts/parts of BackboneJS (View, Model, Collection, Router)
- How to create template
- How to make static (no-data) View
- How to make model and collection (of models)
- Explain Router
- Fake Backbone Server (contract first development)


Specific subjects you will gain experience with
-----------------------------------------------

- [UnderscoreJS](http://underscorejs.org/)
- [BackboneJS](http://backbonejs.org/)

Instructions
------------

1. Open index.html file and define mainArea. You will need to add following div as 'container' for content
    
    ```html
    <div class="container" id="mainArea">
    
    </div>
    ```
2. Don't pollute global name space in JavaScript. Create app.js in /js folder and define Code9 namespace. While here also define target servlet for client application (make it configurable).
    
    ```js
    Code9 = window.Code9 || {};
    
    Code9.Config = {
      applicationServletPath: ''
    };
    ```
3. Create template that will represent _headerView_ in index.html
    
    ```html
    <!-- Here comes the template -->
    <script type="text/template" id="tpl-header">
      <div class="navbar navbar-fixed-top navbar-inverse navbar-default">
        <div class="container">
          <div class="navbar-header">
            <button class="navbar-toggle" data-target=".navbar-collapse" data-toggle="collapse" type="button">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Code9</a>
          </div>
          <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
              <li class="active"><a href="#home">Home</a></li>
              <li><a href="#about">About</a></li>
            </ul>
          </div>
        </div>
      </div>
    </script>
    ```
    NOTE: &lt;script type="text/template"&gt;...&lt;/script&gt; is hidden from the browser.
4. Create headerView.js file in js/views folder
    
    ```js
    Code9.HeaderView = Backbone.View.extend({
        el: $('#mainArea'),
        template: _.template($('#tpl-header').html()),
        render: function() {
            $(this.el).html(this.template());
            return this;
        }
    });
    ```
5. Having sole headerView.js, without wiring it in index.html doesn't mean much, so let's wire it now add headerView.js in index.html
    
    ```html
        <script src="js/views/headerView.js"></script>
    ```
6. View can be complete page, but also can be parts of pages.
    
    ![Code9-Taster1](https://gitlab.levi9.com/d.gajic/code9/raw/master/img/code9-taster-001.png)
    
    ![Code9-Taster2](https://gitlab.levi9.com/d.gajic/code9/raw/master/img/code9-taster-002.png)
    
    ![Code9-Taster3](https://gitlab.levi9.com/d.gajic/code9/raw/master/img/code9-taster-003.png)
    
    ![Code9-Taster4](https://gitlab.levi9.com/d.gajic/code9/raw/master/img/code9-taster-004.png)
7. Now let's create complete page as composite view of header + content. Add template for _indexPage_.
    
    ```html
      <script type="text/template" id="tpl-page-index">
        <div class="container page-content page-header">
          <h1>Taster</h1>
        </div>
      </script>
    ```
8. Also we will need indexView.js. Create file indexView.js in /js/views/ folder
    
    ```js
    Code9.IndexView = Backbone.View.extend({
      el: $('#mainArea'),
    
      template: _.template($("#tpl-page-index").html()),
    
        initialize: function() {
            Code9.headerView = new Code9.HeaderView();
            Code9.headerView.render();
        },
    
        render: function() {
          $(this.el).append(this.template());
          return this;
        }
    });
    ```
    Do not forget to include indexView.js in list of JavaScript files in index.html
    
    ```html
        <script src="js/views/indexView.js"></script>
    ```
9. Router will "take part" of controller and determine view to render based on URL.
    
    ```js
      Code9.AppRouter = Backbone.Router.extend({
        routes: {
          "": "index",
          "home": "home",
          "about": "about"
        },
        index: function() {
          Code9.indexView = new Code9.IndexView();
          Code9.indexView.render();
        },
        home: function() {
          Code9.homeView = new Code9.HomeView();
          Code9.homeView.render();
        },
        about: function() {
          Code9.aboutView = new Code9.AboutView();
          Code9.aboutView.render();
        }
    });
    
    Code9.appRouter = new Code9.AppRouter();
    
    Backbone.history.start();
    ```
10. About page and Homepage is also _static_ page and should be easy to add, following directions.
11. To "describe" data in BackboneJS, there is concept called Model. Create file categoryModel.js in /js/models/ folder.
    
    ```js
    Code9.Category = Backbone.Model.extend({
        urlRoot: Code9.Config.applicationServletPath + 'categories/',
        defaults: {
            name: 'unknown'
        }
    });
    ```
    Here you can see full usage of Code9.Config.
    Also add this JS file in index.html
    
    ```html
      <script src="js/models/categoryModel.js"></script>
    ```
12. Now let's make usage of this Model. Let's create detail view for single category in /js/views/categoryDetailView.js
    
    ```js
    Code9.CategoryDetailView = Backbone.View.extend({
        template: _.template($("#tpl-category-detail").html()),
    
        initialize: function() {
            headerView = new Code9.HeaderView();
            headerView.render();
        },
    
        render: function() {
            $(this.el).html(this.template(this.model.toJSON()));
            return this;
        },
    
        events: {
          "click #btnBackToCategories": "backToCategories"
        },
    
        backToCategories: function() {
          Code9.appRouter.navigate('categories', true);
        }
    });
    ```
    Also template for this page is
    
    ```html
      <script type="text/template" id="tpl-category-detail">
          <div class="container page-content">
              <div class="category-info">
                  <h2>Category: <%= name %></h2>
                  <div id="horizontal-form">
                      <form class="form-horizontal">
                           <div class="form-group">
                              <label for="categoryId" class="col-sm-2 control-label">#ID:</label>
                              <div class="col-sm-10">
                                  <input id="categoryId" type="text" class="form-control" value="<%= id %>" disabled>
                              </div>
                          </div>
                          <div class="form-group">
                              <label for="categoryName" class="col-sm-2 control-label">Name:</label>
                              <div class="col-sm-10">
                                  <input id="categoryName" type="text" class="form-control" value="<%= name %>" disabled>
                              </div>
                          </div>
                      </form>
                  </div>
              </div>
              <div class="action-buttons">
                  <button class="btn btn-info" type="button" id="btnBackToCategories">
                      <span class="glyphicon glyphicon-arrow-left"></span>
                      Back
                  </button>
              </div>
          </div>
      </script>
    ```
    NOTES:
    - Model data will be rendered in template on already predefined places using brackets like this <%=   %>. This is also one of the "cool" stuff that comes from underscoreJS. If needed template engine can be switched for some other implementations
    - BackboneJS will "inject" data in template using this line:
      
      ```js
      $(this.el).html(this.template(this.model.toJSON()));
      ```
13. Now let's create view that will contain list of categories. BackboneJS won't suffice, since we will now have _collection_ of models. Create categoriesCollection.js in /js/collections/categoriesCollection.js
    
    ```js
    Code9.Categories = Backbone.Collection.extend({
        model: Code9.Category,
        url: Code9.Config.applicationServletPath + "categories/"
    });
    ```
    HTML templates to render this collection are:
    
    ```html
      <script type="text/template" id="tpl-categories">
          <div class="container page-content">
              <div class="table-responsive">
                  <table class="table table-bordered">
                      <col style="width:10%">
                      <col style="width:70%">
                      <col style="width:10%">
                      <col style="width:10%">
                      <thead>
                          <tr>
                              <th>#</th>
                              <th>Category name</th>
                              <th>&nbsp;</th>
                              <th>&nbsp;</th>
                          </tr>
                      </thead>
                      <tbody>
    
                      </tbody>
                  </table>
              </div>
    
              <div class="row">
                  <div class="col-md-6">
                      <button type="button" class="btn btn-primary" id="btnAddNewCategory">
                          <span class="glyphicon glyphicon-plus"></span>
                          Add category
                      </button>
                  </div>
              </div>
          </div>
      </script>
    ```
    and how to render singleItem in collection
    
    ```html
        <script type="text/template" id="tpl-category">
            <tr>
                <td><%= id %></td>
                <td><%= name %></td>
                <td>
                    <button type="button" class="btn btn-default btn-sm btn-view" data-id="<%= id %>">
                        <span class="glyphicon glyphicon-eye-open"></span>
                        View
                    </button>
                </td>
                <td>
                    <button id=type="button" class="btn btn-default btn-sm btn-delete" data-id="<%= id %>">
                        <span class="glyphicon glyphicon-remove"></span>
                        Delete
                    </button>
                </td>
            </tr>
        </script>
    ```
    As usual, besides templates, we will need views to handle this Collection:
    
    ```js
    Code9.CategoriesListView = Backbone.View.extend({
        template: _.template($("#tpl-categories").html()),
    
        initialize: function() {
            Code9.headerView = new Code9.HeaderView();
            Code9.headerView.render();
    
            _.bindAll(this, 'render', 'renderOne');
        },
    
        renderOne: function(model) {
            var categoryView = new Code9.CategoryView({model: model});
            this.$("tbody").append(categoryView.render().$el);
        },
    
        render: function() {
            var $categories, collection = this.collection;
            $(this.el).append(this.template({}));
            $categories = this.$("tbody");
            this.collection.each(this.renderOne, this);
            return this;
        },
    
        events: {
            "click #btnAddNewCategory": "addNewCategory"
        },
    
        addNewCategory: function() {
            Code9.appRouter.navigate('add-new-category', true);
        }
    });
    ```
    and single item view handler
    
    ```js
    Code9.CategoryView = Backbone.View.extend({
    
        render: function() {
            var html = _.template($("#tpl-category").html(), this.model.toJSON());
            this.setElement($(html));
            return this;
        },
    
        events: {
            "click button.btn-delete": "deleteCategory",
            "click button.btn-view": "viewCategory"
        },
    
        deleteCategory: function() {
            var categoryToRemove = this.model.get("id");
            this.model.destroy();
            this.remove();
        },
    
        viewCategory: function(ev) {
            Code9.appRouter.navigate('category/' + $(ev.target).data("id"), true);
        }
    
    });
    
    ```
14. Implementing create and delete operations. Create /js/views/addCategoryView.js
    
    ```js
    Code9.NewCategoryView = Backbone.View.extend({
        el: $('#mainArea'),
    
        template: _.template($("#tpl-add-category").html()),
    
        initialize: function() {
            Code9.headerView = new Code9.HeaderView();
            Code9.headerView.render();
        },
    
        render: function() {
            $(this.el).append(this.template());
            return this;
        },
    
        events: {
            "click #btnSubmitCategory": "submitCategory",
            "click #btnCancelCategory": "cancelCategory"
        },
    
        submitCategory: function() {
          var self = this;
            var categoryName = $('input[name="categoryName"]').val();
            var newCategory = new Code9.Category({name: categoryName});
    
            newCategory.save(null, {
                success: function(model, response, options) {
                    self.unset();
                    Code9.appRouter.navigate('categories', true);
                },
                error: function(model, response, options) {
                    console.log("-+- error -+-");
                }
            });
        },
    
        cancelCategory: function() {
            Code9.appRouter.navigate('categories', true);
        }
    });
    ```
    HTML template:
    
    ```html
    <script type="text/template" id="tpl-add-category">
        <div class="container page-content">
            <h2>Add new category</h2>
    
            <div class="row v-form-margin">
                <div class="col-md-2">
                    Name:*
                </div>
                <div class="col-md-6">
                    <input type="text" name="categoryName">
                </div>
            </div>
            <div class="row v-form-margin">
                <div class="col-md-6 pull-right">
                    <button type="button" class="btn btn-success" id="btnSubmitCategory">
                        <span class="glyphicon glyphicon-ok"></span>
                        Submit
                    </button>
                    <button type="button" class="btn btn-danger" id="btnCancelCategory">
                        <span class="glyphicon glyphicon-remove"></span>
                        Cancel
                    </button>
                </div>
            </div>
        </div>
    </script>
    
    ```

Lab20 - Integrate with REST
===========================================================

----

Instructions
------------

1. remove fake server, remove backbone-faux-server.js

2. change configuration of Code9.Config
    
    ```js
    Code9.Config = {
      applicationServletPath: 'http://localhost:8080/lab20-end-rest/'
    };
    ```
3. deploy both applications lab20-end-rest and lab20-end-spa
and start using it.
