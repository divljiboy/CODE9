define([ 
    'jquery',
    'underscore',
    'backbone',
    'bootstrap-dialog',
    
    'views/abstractEpoxyView',
        
    'util',
    
    'i18n!nls/translations',
    
    'text!template/answerEdit.html',
], function($, _, Backbone,
		BootstrapDialog,
		AbstractEpoxyView,
		Util,
		Translations,
		AnswerEditTemplate) {

	var AnswerEditView = AbstractEpoxyView.extend({
		templateHTML: _.template(AnswerEditTemplate),
		saveCallback: null,
		initialize: function(options) {
			this.saveCallback = options.saveCallback;
			
			this.render();
		},
		render: function() {
			var self = this;
			
			// Set page content to dialog modal
			this.$el = BootstrapDialog.show({
	            title: (Translations.answerEdit),
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
	                		dialogItself.close();
	                		self.saveCallback(self.model);
		                }
		            }
		        ]
	        }).getModal();
			
			// Bind model to view
			this.applyBindings();
			
			return this;
		}
	});
	
	return AnswerEditView;
});