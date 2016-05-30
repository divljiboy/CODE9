define([ 
    'jquery',
    'underscore',
    'backbone',
    
    'views/abstractEpoxyView',
    'models/fixedTestTemplate',
    'views/fixedTestTemplates/fixedTestTemplateAddEditView',
    'util',
    
    'i18n!nls/translations',
    
    'text!template/testTemplateItem.html',
], function($, _, Backbone,
		AbstractEpoxyView,
		FixedTestTemplateModel,
		FixedTestTemplateAddEditView,
		Util,
		Translations,
		TestTemplateItemTemplate) {

	var TestTemplateItemView = AbstractEpoxyView.extend({
		tagName: 'tr',
		templateHTML: _.template(TestTemplateItemTemplate),
		deleteCallback: null,
		events: {
			'click .delete': 'deleteTemplate',
			'click .edit': 'editTemplate'
		},
		initialize: function(options) {
			this.deleteCallback = options.deleteCallback;
		},
		
		render: function() {
			var self = this;
			
			// Set page content
			this.$el.html(this.templateHTML({
				t: Translations,
			}));
			
			// Bind model to view
			this.applyBindings();
			
			switch (self.model.get('type')) {
				case 'F' : 
					self.$('.template-type').text(Translations.testTemplatesFixed);
					break;
				case 'R' : 
					self.$('.template-type').text(Translations.testTemplatesRandom);
					break;
				default :
					self.$('.template-type').text(Translations.testTemplatesUnknown);
					break;
			} 

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
		
		
		editTemplate: function() {
			var self = this;
			
			if (self.model.get('type') === "F") {
				self.fixedTemplateModel = new FixedTestTemplateModel({'id' : self.model.get('id')});
				self.fixedTemplateModel.fetch();
				new FixedTestTemplateAddEditView({
					model: self.fixedTemplateModel,
					saveCallback: function(newTemplate) {
						if (self.saveCallback) {
							self.saveCallback();
						}
					}
				});
			} 
		}
	});
	
	return TestTemplateItemView;
});