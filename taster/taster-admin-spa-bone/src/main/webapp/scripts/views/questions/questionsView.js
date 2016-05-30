define([ 
    'jquery',
    'underscore',
    'backbone',
    
    'views/abstractView',
    
    'util',
    
    'collections/categories',
    'collections/questions',
    'collections/questionsByCategoryId',
    
    'views/questions/questionItemView',
    'views/questions/addEditQuestionView',
    
    'views/pagingView',
    
    'i18n!nls/translations',
    
    'text!template/questions.html'
], function($, _, Backbone,
		AbstractView,
		Util,
		CategoriesCollection,
		QuestionsCollection,
		QuestionsByCategoryIdCollection,
		QuestionItemView, AddEditQuestionView, 
		PagingView,
		Translations,
		QuestionsTemplate) {

	var QuestionsView = AbstractView.extend({
		el: '#content',
		templateHTML: _.template(QuestionsTemplate),
		categoryId: null,
		events: {
			'click .new-question': 'newQuestion'
		},
		initialize: function(options) {
			var self = this;
						
			this.render();

			// Initialize and fetch different collection if category ID is set
			if (options && options.categoryId) {
				this.collection = new QuestionsByCategoryIdCollection({categoryId: options.categoryId});
				this.categoryId = options.categoryId;
			} else {
				this.collection = new QuestionsCollection({});
			}
			
			// Bind add and reset events so collection changes are rendered automatically
			this.collection.bind('changed', this.refreshQuestions, this);
			this.collection.fetch();
			
			// Paging
			this.$el.append(new PagingView({collection: self.collection}).render().$el);
		},
		render: function() {
			// Set page content
			this.$el.html(this.templateHTML({
				t: Translations
			}));
			
			return this;
		},
		
		/**
		 * Render/append new single question
		 */
		appendQuestion: function(question) {
			var self = this;
			
			var questionItemView = new QuestionItemView({
				model: question,
				deleteCallback: function () {
					self.collection.fetch(); 
				},
				editCallback: function () {
					self.collection.fetch(); 
				}
			});
			this.$('.questionsList').append(questionItemView.render().el);
		},
		
		/**
		 * Render/refresh questions
		 */
		refreshQuestions: function() {
			var self = this;
			
			this.clearQuestions();
			this.collection.each(function(question) {
				self.appendQuestion(question);
			});
		},
		
		/**
		 * Clear questions
		 */
		clearQuestions: function() {
			this.$('.questionsList').empty();
		},
		
		/**
		 * New question action
		 */
		newQuestion: function() {
			var self = this;
			
			new AddEditQuestionView({
				categoryId: self.categoryId,
				saveCallback: function(newQuestion) {
					self.collection.fetch(); 
				}
			});
		}
	});
	
	return QuestionsView;
});