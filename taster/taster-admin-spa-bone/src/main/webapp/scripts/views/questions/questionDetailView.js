define([ 
        'jquery',
        'underscore',
        'backbone',
        'libs/bootbox/bootbox.min',

        'views/abstractView',

        'util',

        'constants',

        'models/question',

        'collections/categories',
        'collections/answersByQuestionId',

        'views/questions/questionItemView',
        'views/answers/answersView',

        'views/pagingView',

        'i18n!nls/translations',

        'text!template/questionDetail.html'
        ], function($, _, Backbone, Bootbox, 
        		AbstractView,
        		Util,
        		Constants,
        		QuestionModel,
        		CategoriesCollection,
        		AnswersByQuestionIdCollection,
        		QuestionItemView, 
        		AnswersView,
        		PagingView,
        		Translations,
        		QuestionDetailTemplate) {

	var QuestionsView = AbstractView.extend({
		el: '#content',
		templateHTML: _.template(QuestionDetailTemplate),
		tags : [],
		model : QuestionModel,
		events: {
			'click .edit-answers': 'editAnswers',
			'click .remove-tags': 'removeTags',
			'click .add-tag' : 'addTag',
			'click .cancel': 'cancel',
			'click .submit': 'submit'
		},

		initialize: function(options) {
			var self = this;

			this.saveCallback = options.saveCallback;

			//Create new model or use existing one (if editing)
			if (options.id) {
				this.model = new QuestionModel({'id' : options.id});
				this.model.fetch();
				this.answersCollection = new AnswersByQuestionIdCollection({'questionId' : options.id});
				this.answersCollection.fetch();
				this.unsavedModel = false;
				
			} else { 
				this.model = new QuestionModel({});
				this.answersCollection = new AnswersByQuestionIdCollection({});
				this.answersCollection.reset();
				this.unsavedModel = true;
			}

			this.tags = (options.id ? this.model.get('tags') : []);

			//Collection of categories for populating drop-down
			this.categoriesCollection = new CategoriesCollection();
			this.categoriesCollection.fetch();

			this.render();

		},

		render: function() {
			// Set page content
			this.$el.html(this.templateHTML({
				t: Translations,
				categoriesCollection : this.categoriesCollection,
				answersCollection : this.answersCollection,
			}));

			this.$('#category-select').val(this.model.get('categoryId'));
			this.updateTagCheckboxes();
			this.updateAnswers();

			this.applyBindings();
			return this;
		},

		/**
		* Add tag action
		*/
		addTag : function() {
			var self = this;
			var tag = self.$(".tag-input");
			if (tag.val() && ($.inArray(tag.val(), self.tags) === -1)) {
				self.tags.push(tag.val());		
				self.updateTagCheckboxes();
			}
			tag.val("");
			tag.focus();
		},

		/**
		* Update tag checkboxes tag action
		*/		
		updateTagCheckboxes : function() {
			var self = this;

			var checkboxList = "";
			for (var i = 0; i < this.tags.length; i++) {
				checkboxList += '<input type="checkbox" class="tagCheckbox" value="' + this.tags[i] + '"> ' + this.tags[i] + '</input><br/>';
			}

			self.$(".tag-checkboxes").html(checkboxList);
		},

		/**
		* Remove selected tags action
		*/
		removeTags : function() {
			var self = this;

			$(".tagCheckbox").each( function( index, element ){
				if( $( element ).prop('checked') ) {
					self.tags = _.without(self.tags, $(element).val());
				}
			});

			this.updateTagCheckboxes();
		},

		/**
		* Submit button action
		*/
		submit : function() {
			var self = this;
			self.model.save({"categoryName": $("#category-select option:selected").text(), "categoryId": self.$('#category-select option:selected').val(), "tags": self.tags}, {
				success: function() {
					this.unsavedModel = false;
					self.cancel();
				}
			});
		},

		/**
		* Cancel button action
		*/
		cancel : function() {
			Backbone.history.navigate('/questions', {trigger:true});
			window.location.reload();
		},

		/**
		* Update correct and incorrect labels
		*/
		updateAnswers : function() {
			var correctCount = 0, incorrectCount = 0;
			var self = this;
			if (self.answersCollection.length > 0) {
				self.answersCollection.each(function (element) {
					if (element.get('correct'))
						correctCount++;
					else 
						incorrectCount++;
				});
			}
			self.$( ".correct" ).text(correctCount);
			self.$( ".incorrect" ).text(incorrectCount);
		},

		/**
		* Edit answers action
		*/
		editAnswers : function() {
			var self = this;

			if (this.unsavedModel) {
				Bootbox.confirm({message: Translations.confirmationMessage, title: Translations.confirmationTitle, "callback" : function(result) {
					if (result) {
						self.model.save({"categoryName": $("#category-select option:selected").text(), "categoryId": self.$('#category-select option:selected').val(), "tags": self.tags}, {
							success: function() {
								self.unsavedModel = false;
								self.openAnswersView();
							}
						});
					}		  
				}});
			} else {
				self.openAnswersView();
			}
		},
		
		/**
		* Create new answers view
		*/
		openAnswersView : function() {
			var self = this;
			
			new AnswersView({
				questionModel : self.model,
				collection : self.answersCollection,				
				saveCallback : function(answers) {
					self.answersCollection = answers;
					self.updateAnswers();
				}
			});
		}
	});

	return QuestionsView;
});;;