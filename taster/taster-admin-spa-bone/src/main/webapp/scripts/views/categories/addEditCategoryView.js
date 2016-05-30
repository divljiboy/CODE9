define([ 
    'jquery',
    'underscore',
    'backbone',
    'bootstrap-dialog',
    
    'views/abstractEpoxyView',
    
    'models/category',
    
    'util',
    
    'i18n!nls/translations',
    
    'text!template/addEditCategory.html',
], function($, _, Backbone,
		BootstrapDialog,
		AbstractEpoxyView,
		CategoryModel,
		Util,
		Translations,
		AddEditCategoryTemplate) {

	var AddEditCategoryView = AbstractEpoxyView.extend({
		templateHTML: _.template(AddEditCategoryTemplate),
		saveCallback: null,
		initialize: function(options) {
			this.saveCallback = options.saveCallback;
			// Create model or use existing one (if editing)
			this.model = options.model ? options.model.clone() : new CategoryModel({});
			
			this.render();
		},
		render: function() {
			var self = this;
			
			// Set page content to dialog modal
			this.$el = BootstrapDialog.show({
	            title: (this.model.get('id') ? Translations.categoryEdit : Translations.categoryNew),
	            message: this.templateHTML({t : Translations}).replace(/(\r\n|\n|\r|\t)/gm, ''),
	            type: BootstrapDialog.TYPE_PRIMARY,
	            closable: false,
	            draggable: true,
	            buttons: [
	                // Cancel button
	                {
		                label: Translations.cancel,
		                cssClass: 'btn-danger',
		                action: function(dialogItself) {
		                    dialogItself.close();
		                }
		            },
		            // Submit button
		            {
		                label: Translations.submit,
		                cssClass: 'btn-primary',
		                action: function(dialogItself) {
		                	self.model.save({}, {
		                		success: function() {
				                	dialogItself.close();
				                    self.saveCallback(self.model);
		                		},
		                	});
		                }
		            }
		        ]
	        }).getModal();
			
			// Bind model to view
			this.applyBindings();
			
			return this;
		}
	});
	
	return AddEditCategoryView;
});