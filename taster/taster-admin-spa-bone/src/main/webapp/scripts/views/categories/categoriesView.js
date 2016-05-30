define([ 
    'jquery',
    'underscore',
    'backbone',
    
    'views/abstractView',
    
    'util',
    
    'collections/categories',
    
    'views/categories/categoryItemView',
    'views/categories/addEditCategoryView',
    'views/pagingView',
    
    'i18n!nls/translations',
    
    'text!template/categories.html'
], function($, _, Backbone,
		AbstractView,
		Util,
		CategoriesCollection,
		CategoryItemView, AddEditCategoryView, PagingView,
		Translations,
		CategoriesTemplate) {

	var CategoriesView = AbstractView.extend({
		el: '#content',
		templateHTML: _.template(CategoriesTemplate),
		paging: null,
		events: {
			'click .new-category': 'newCategory'
		},
		initialize: function(options) {
			var self = this;
			
			this.render();

			// Fetch categories
			this.collection = new CategoriesCollection();
			// Bind add and reset events so collection changes are rendered automatically
			this.collection.bind('changed', this.refreshCategories, this);
			this.collection.fetch();
			
			// Paging
			this.paging = new PagingView({collection : self.collection});
			this.$el.append(this.paging.render().$el);
		},
		render: function() {
			// Set page content
			this.$el.html(this.templateHTML({
				t: Translations
			}));
			
			return this;
		},
		
		/**
		 * Render/append new single category
		 */
		appendCategory: function(category) {
			var self = this;
			
			var categoryItemView = new CategoryItemView({
				model: category,
                deleteCallback: function () {
                    self.collection.fetch();
                }
			});
			this.$('.categoriesList').append(categoryItemView.render().el);
		},
		
		/**
		 * Render/refresh categories
		 */
		refreshCategories: function() {
			var self = this;
			
			this.clearCategories();
			this.collection.each(function(category) {
				self.appendCategory(category);
			});
		},
		
		/**
		 * Clear categories
		 */
		clearCategories: function() {
			this.$('.categoriesList').empty();
		},
		
		/**
		 * New category action
		 */
		newCategory: function(e) {
			var self = this;
			
			e.preventDefault();
			
			new AddEditCategoryView({
				saveCallback : function(newCategory) {
					self.collection.fetch();
				}
			});
		},
		
		
		/**
		 * Edit category action
		 */
		editCategory: function(category){
			var self = this;
			
			new NewCategoryView({
				model: category,
				saveCallback : function(newCategory) {
					self.collection.add(newCategory, {merge : true});  
				}
			});
		}
	});
	
	return CategoriesView;
});