define([ 
    'jquery',
    'underscore',
    'backbone',
    
    'views/abstractEpoxyView',
    
    'util',
    
    'i18n!nls/translations',
    
    'text!template/questionItemSubtemplate.html',
], function($, _, Backbone,
		AbstractEpoxyView,
		Util,
		Translations,
		QuestionItemTemplate) {

	var QuestionItemSubview = AbstractEpoxyView.extend({
		tagName: 'tr',
		templateHTML: _.template(QuestionItemTemplate),
		removeCallback: null,
		editCallback: null,
		events: {
			'click .remove': 'removeQuestion',
		},
		
		initialize: function(options) {
			this.removeCallback = options.removeCallback;
		},
		render: function() {
			// Set page content
			this.$el.html(this.templateHTML({
				t: Translations,
			}));
			
			// Bind model to view
			this.applyBindings();
			
			return this;
		},
		
		/**
		 * Remove question action
		 */
		removeQuestion: function() {
			this.removeCallback();
		},

	});
	
	return QuestionItemSubview;
});