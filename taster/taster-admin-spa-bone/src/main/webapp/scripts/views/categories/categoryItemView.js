define([ 
    'jquery',
    'underscore',
    'backbone',
    
    'views/abstractEpoxyView',
    'views/categories/addEditCategoryView',
    
    'util',
    
    'i18n!nls/translations',
    
    'text!template/categoryItem.html',
], function($, _, Backbone,
		AbstractEpoxyView,
		AddEditCategoryView,
		Util,
		Translations,
		CategoryItemTemplate) {

	var CategoryItemView = AbstractEpoxyView.extend({
		tagName: 'tr',
		templateHTML: _.template(CategoryItemTemplate),
		deleteCallback: null,
		events: {
			'click .delete': 'deleteCategory',
			'click .questions': 'getQuestions',
			'click .edit': 'editCategory'
		},
		initialize: function(options) {
			this.deleteCallback = options.deleteCallback;
		},
		render: function() {
			// Set page content
			this.$el.html(this.templateHTML({
				t: Translations
			}));
			
			// Bind model to view
			this.applyBindings();
			
			return this;
		},
		
		/**
		 * Delete category action
		 */
		deleteCategory: function() {
			var self = this;
			
			this.model.destroy({
				success: function() {
					if (self.deleteCallback) {
						self.deleteCallback();
					}
				}
			});	
		},
		
		/**
		 * Get questions action
		 */
		getQuestions: function() {
			Backbone.history.navigate('categories/' + this.model.get('id') + '/questions', true);
		},
		
		/**
		 * Edit category action
		 */
		editCategory: function() {
			var self = this;
			
			new AddEditCategoryView({
				model: self.model,
				saveCallback: function(newCategory) {
					self.model = newCategory;
					self.render();
				}
			});
		}
	});
	
	return CategoryItemView;
});