define([ 
    'jquery',
    'underscore',
    'backbone',
    'bootstrap-dialog',
    
    'views/abstractView',
    'views/answers/answerItemView',
    
    'models/answer',
    'collections/answersByQuestionId',
        
    'util',
    
    'i18n!nls/translations',
    
    'text!template/answers.html',
    'backboneEpoxy',
], function($, _, Backbone,
		BootstrapDialog,
		AbstractView,
		AnswerItemView,
		AnswerModel,
		AnswersByQuestionIdCollection,
		Util,
		Translations,
		AnswersTemplate) {

	var AnswersView = AbstractView.extend({
		templateHTML: _.template(AnswersTemplate),
		questionModel: null,
		saveCallback: null,
		events: {
			'click .add': 'addNewAnswer',
		},
		initialize: function(options) {
			this.questionModel = options.questionModel;
			this.collection = options.collection;
			this.render();

			// Bind events
			this.collection.on('changed reset add remove', this.refreshAnswers, this);
			this.refreshAnswers();
		},
		render: function() {
			this.$el.html(this.templateHTML({
				t: Translations
			}));
						
			return this;
		},
		
		/**
		 * Append answer action (append to table)
		 */
		appendAnswer: function(answer) {
			var self = this;
			
			var answerItemView = new AnswerItemView({
				model: answer,
				deleteCallback: function() {
					self.collection.remove(answer);
				}
			});
			this.$('.answersList').append(answerItemView.render().el);
		},
		
		/**
		 * Refresh table with answers
		 */
		refreshAnswers: function() {
			var self = this;
			
			this.clearAnswers();
			this.collection.each(function(answer) {
				self.appendAnswer(answer);
			});
		},
		
		/**
		 * Clear answers table
		 */
		clearAnswers: function() {
			this.$('.answersList').empty();
		},
		
		/**
		 * Add new answer action
		 */
		addNewAnswer: function() {
			var self = this;
			
			answer = new AnswerModel({
				'answerContent': self.$('#answer-content').val() ? self.$('#answer-content').val().trim() : null, 
				'correct': self.$('#answer-correct').is(':checked'), 
			});
			
			this.collection.add(answer);
		}
	});
	
	return AnswersView;
});