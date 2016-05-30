define([ 
        'jquery',
        'underscore',
        'backbone',
        'bootstrap-dialog',

        'views/abstractEpoxyView',

        'util',

        'constants',

        'models/question',

        'collections/categories',
        'collections/answersByQuestionId',

        'views/questions/questionItemView',
        'views/answers/answersView',

        'views/pagingView',

        'i18n!nls/translations',

        'text!template/addEditQuestion.html'
], function($, _, Backbone, 
		BootstrapDialog,
		AbstractEpoxyView,
		Util,
		Constants,
		QuestionModel,
		CategoriesCollection,
		AnswersByQuestionIdCollection,
		QuestionItemView, 
		AnswersView,
		PagingView,
		Translations,
		AddEditQuestionTemplate) {

	var QuestionsView = AbstractEpoxyView.extend({
		templateHTML: _.template(AddEditQuestionTemplate),
		events: {
			'click .remove-tags': 'removeTags',
			'click .add-tag': 'addTag'
		},

		initialize: function(options) {
			this.model = options.model;
			this.saveCallback = options.saveCallback;
			
			// Create new model or use existing one (if editing)
			this.model = options.model ? options.model.clone(): new QuestionModel({categoryId: options.categoryId});

			if (this.model.get('id')) {
				this.answersCollection = new AnswersByQuestionIdCollection({'questionId' : this.model.get('id')});
				this.answersCollection.fetch();
			} else { 
				this.answersCollection = new AnswersByQuestionIdCollection({});
				this.answersCollection.reset();
			}
			
			// Collection of categories for populating drop-down
			this.categoriesCollection = new CategoriesCollection();
			this.categoriesCollection.fetch();

			this.render();
		},
		render: function() {
			var self = this;
			
			// Bind model to view
			this.applyBindings();
			
			// Set page content to dialog modal
			this.$el = BootstrapDialog.show({
	            title: (this.model.get('id') ? Translations.questionEdit: Translations.questionNew),
	            message: this.templateHTML({
					t: Translations,
					categoriesCollection: this.categoriesCollection
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
		                	self.model.save({
		                			'categoryId' : self.$('#category-select').val(),
		                			'categoryName' : $("#category-select option:selected" ).text(), 
		                			'answers' : self.answersCollection}, {
		                		success: function() {
				                	dialogItself.close();
				                    self.saveCallback(self.model);
		                		}
		                	});
		                }
		            }
		        ]
	        }).getModal();

			// Mark category as selected
			if (this.model.get('categoryId')) 
				this.$('#category-select').val(this.model.get('categoryId'));
			
			// Update tag checkboxes based on tag values
			this.updateTagCheckboxes();
			
			// Answers view
			this.$('.answersBody').html(new AnswersView({
				questionModel: self.model,
				collection: self.answersCollection
			}).el);

			return this;
		},

		/**
		 * Add tag action
		 */
		addTag: function() {
			var tag = this.$(".tag-input");
			
			// Check if tag value entered and already added
			if (tag.val() && ($.inArray(tag.val(), this.model.get('tags')) === -1)) {
				this.model.get('tags').push(tag.val());		
				this.updateTagCheckboxes();
			}
			
			// Reset tag input field
			tag.val("");
			tag.focus();
		},

		/**
		 * Update tag checkboxes tag action
		 */		
		updateTagCheckboxes: function() {
			var checkboxList = "";
			
			for (var i = 0; i < this.model.get('tags').length; i++) {
				checkboxList += '<input type="checkbox" class="tagCheckbox" value="' + this.model.get('tags')[i] + '"> ' + this.model.get('tags')[i] + '</input><br/>';
			}

			this.$(".tag-checkboxes").html(checkboxList);
		},

		/**
		 * Remove selected tags action
		 */
		removeTags: function() {
			var self = this;
			
			$(".tagCheckbox").each(function(index, element) {
				if ($(element).prop('checked') ) {
					self.model.set('tags', _.without(self.model.get('tags'), $(element).val()));
				}
			});

			this.updateTagCheckboxes();
		}
	});

	return QuestionsView;
});
