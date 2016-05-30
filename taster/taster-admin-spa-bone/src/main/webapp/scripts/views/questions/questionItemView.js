define([ 
    'jquery',
    'underscore',
    'backbone',
    
    'views/abstractEpoxyView',
    
    'util',
    
    'views/questions/addEditQuestionView',
    
    'i18n!nls/translations',
    
    'text!template/questionItem.html',
], function($, _, Backbone,
		AbstractEpoxyView,
		Util,
		AddEditQuestionView,
		Translations,
		QuestionItemTemplate) {

	var QuestionItemView = AbstractEpoxyView.extend({
		tagName: 'tr',
		templateHTML: _.template(QuestionItemTemplate),
		deleteCallback: null,
		editCallback: null,
		events: {
			'click .delete': 'deleteQuestion',
			'click .edit': 'editQuestion'
		},
		
		initialize: function(options) {
			this.deleteCallback = options.deleteCallback;
			this.editCallback = options.editCallback;
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
		 * Delete question action
		 */
		deleteQuestion: function() {
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
		 * Edit question action
		 */
		editQuestion: function() {
			var self = this;
			
			new AddEditQuestionView({
				model: self.model,
				saveCallback: function(newQuestion) {
					if (self.editCallback) {
						self.editCallback();
					}
				}
			});
		}
	});
	
	return QuestionItemView;
});