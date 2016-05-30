define([ 
        'jquery',
        'underscore',
        'backbone',
        'bootstrap-dialog',
 

        'views/abstractEpoxyView',

        'util',

        'constants',

        'models/fixedTestTemplate',
        'models/question',
        
        'collections/questions',
        'collections/questionsByFixedTestTemplateId',

        'views/fixedTestTemplates/questionItemSubview',

        'views/pagingView',

        'i18n!nls/translations',

        'text!template/addEditFixedTestTemplate.html',
        
        'select2'
], function($, _, Backbone, 
		BootstrapDialog,
		AbstractEpoxyView,
		Util,
		Constants,
		FixedTestTemplateModel,
		QuestionModel,
		QuestionsCollection,
		QuestionsByFixedTestTemplateIdCollection,
		QuestionItemSubview, 
		PagingView,
		Translations,
		AddEditFixedTemplate) {

	var FixedTestTemplateAddEditView = AbstractEpoxyView.extend({
		templateHTML: _.template(AddEditFixedTemplate),
		events: {
			'click .add-question' : 'addQuestion'
		},

		initialize: function(options) {
			this.saveCallback = options.saveCallback;
			
			// Create new model or use existing one (if editing)
			this.model = options.model ? options.model.clone() : new FixedTestTemplateModel({});

			// Collection of questions for populating drop-down
			this.allQuestionsCollection = new QuestionsCollection({});
			this.allQuestionsCollection.fetch();
			
			this.templateQuestionsCollection = new QuestionsByFixedTestTemplateIdCollection({'id' : this.model.get('id')});
			if (options.model) {
				this.templateQuestionsCollection.fetch();
			} else
				this.templateQuestionsCollection.reset();

			this.render();
		},
		
		render: function() {
			var self = this;
			
			// Set page content to dialog modal
			this.$el = BootstrapDialog.show({
	            title: (this.model.get('id') ? Translations.testTemplatesEdit : Translations.testTemplatesNew),
	            message: this.templateHTML({
					t: Translations,
					allQuestionsCollection : this.allQuestionsCollection,
				}).replace(/(\r\n|\n|\r|\t)/gm, ''),
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
		                	self.model.set({'questions' : []});
		                	self.model.save({'questions' : self.templateQuestionsCollection}, {
		                		success: function() {
		                			dialogItself.close();
				                    self.saveCallback(self.model);
		                		}
		                	});
		                }
		            }
		        ]
	        }).getModal();
    		
			// Bind model to view
			this.applyBindings();

			this.refreshQuestions();
			
			//select-ify question selection drop-down
			self.$("#question-select").select2();
			
			return this;
		},
		
		/**
		 * Render/append new single question
		 */
		appendQuestion: function(question) {
			var self = this;
			var questionItemSubview = new QuestionItemSubview({
				model: question,
				removeCallback: function () {
					self.templateQuestionsCollection.remove(question);
					self.refreshQuestions();
				},
			});
			this.$('.questionsList').append(questionItemSubview.render().el);
		},
		
		/**
		 * Render/refresh questions
		 */
		refreshQuestions: function() {
			var self = this;
			
			this.clearQuestions();
			this.templateQuestionsCollection.each(function(question) {
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
		addQuestion: function() {
			var self = this;
			var question = new QuestionModel({'id' : this.$('#question-select').val()});
			question.fetch();
			var found = self.templateQuestionsCollection.findWhere({'id': question.get('id')});

			if (!found) {
				self.templateQuestionsCollection.add(question);
				self.appendQuestion(question);
			} 
		},
		
	});

	return FixedTestTemplateAddEditView;
});