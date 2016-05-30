define([ 
    'jquery',
    'underscore',
    'backbone',
    
    'views/fixedTestTemplates/fixedTestTemplateAddEditView',
    'views/abstractEpoxyView',
    
    'util',
    
    'i18n!nls/translations',
    
    'text!template/fixedTestTemplateItem.html',
], function($, _, Backbone,
		AddEditFixedTemplateView,
		AbstractEpoxyView,
		Util,
		Translations,
		FixedTestTemplateItemTemplate) {

	var TestTemplateItemView = AbstractEpoxyView.extend({
		tagName: 'tr',
		templateHTML: _.template(FixedTestTemplateItemTemplate),
		deleteCallback: null,
		saveCallback: null,
		events: {
			'click .delete': 'deleteTemplate',
			'click .edit': 'editTemplate'
		},
		initialize: function(options) {
			this.deleteCallback = options.deleteCallback;
			this.saveCallback = options.saveCallback;
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
		 * Delete template action
		 */
		deleteTemplate: function() {
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
		 * Edit template action
		 */
		editTemplate: function() {
			var self = this;
			
			new AddEditFixedTemplateView({
				model: self.model,
				saveCallback: function() {
					self.saveCallback();
					
				}
			});
		}
		
	});
	
	return TestTemplateItemView;
});