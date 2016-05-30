define([ 
	'jquery',
    'underscore',
    'backbone',
    
    'views/headerView',
    'views/categories/categoriesView',
    'views/questions/questionsView',
    'views/testTemplates/testTemplatesView',
    'views/fixedTestTemplates/fixedTestTemplatesView',
    
    'backboneRouteFilter'
], function($, _, Backbone,
		HeaderView,
		CategoriesView, QuestionsView, TestTemplatesView, FixedTestTemplatesView) {
	
	 var Router = Backbone.Router.extend({
		currentView: null,
		headerView: null,
		
		/**
		 * Route definitions
		 */
		routes: {
			'categories': 'categoriesAction',
			'questions': 'questionsAction',
			'categories/:categoryId/questions': 'questionsByCategoryIdAction', 
			'testTemplates': 'testTemplatesAction',
			'fixedTestTemplates' : 'fixedTestTemplatesAction',

			'*path':  'defaultRoute',
		},
		initialize: function(options) {
			
		},
		
		/**
		 * Actions to be done before route is processed
		 */
		before: function(route, params) {
			// Call close on previous views to unload them/do clean up
			if (this.currentView
					&& this.currentView.close) {
				this.currentView.close();
			}
			
			if (!this.headerView) {
				this.headerView = new HeaderView();
			}
		},
		
		/**
		 * Actions to be done after route is processed
		 */
		after: function(route, params) {
			
		},
		
		/**
		 * Handle unsupported routes
		 */
		defaultRoute: function(path) {
			Backbone.history.navigate('categories', true);
		},
		
		/**
		 * View actions
		 */
		categoriesAction: function() {
			this.currentView = new CategoriesView();	
		},

		questionsAction: function() {
			this.currentView = new QuestionsView();	
		},
		
		questionsByCategoryIdAction: function(categoryId) {
			this.currentView = new QuestionsView({categoryId : categoryId});
		},
		
		testTemplatesAction: function() {
			this.currentView = new TestTemplatesView();	
		},
		
		fixedTestTemplatesAction: function() {
			this.currentView = new FixedTestTemplatesView();
		}
	});
	 
	/**
	 * Instance of the router will be created as soon as it is required as dependency for the first time
	 */
	return new Router;
});
