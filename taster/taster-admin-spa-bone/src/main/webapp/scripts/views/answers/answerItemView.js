define([ 
    'jquery',
    'underscore',
    'backbone',
    
    'views/abstractEpoxyView',
    'views/answers/answerEditView',
    
    'util',
    
    'i18n!nls/translations',
    
    'text!template/answerItem.html',
], function($, _, Backbone,
		AbstractEpoxyView,
		AnswerEditView,
		Util,
		Translations,
		AnswerItemTemplate) {

	var AnswerItemView = AbstractEpoxyView.extend({
		tagName: 'tr',
		templateHTML: _.template(AnswerItemTemplate),
		deleteCallback: null,
		events: {
			'click .delete': 'deleteAnswer',
			'click .edit': 'editAnswer'
		},
		
		initialize: function(options) {
			this.deleteCallback = options.deleteCallback;
		},
		render: function() {
			this.$el.html(this.templateHTML({
				t: Translations,
			}));
			
			this.applyBindings();
			
			return this;
		},
		
		/**
		 * Delete answer action
		 */
		deleteAnswer: function() {
			var self = this;
			
			self.deleteCallback();
		},
		
		/**
		 * Edit answer action
		 */
		editAnswer: function() {
			var self = this;
			
			new AnswerEditView({
				model: self.model,
				saveCallback: function(newAnswer) {
					self.model = newAnswer;
				}
			});
		}
	});
	
	return AnswerItemView;
});