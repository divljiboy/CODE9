define([ 
    'jquery',
    'underscore',
    'backbone',
    
    'views/abstractView',
    
    'util',
    
    'collections/testTemplates',
    
    'views/testTemplates/testTemplateItemView',
    'views/pagingView',
    
    'i18n!nls/translations',
    
    'text!template/testTemplates.html'
], function($, _, Backbone,
		AbstractView,
		Util,
		TestTemplatesCollection,
		TestTemplateItemView, PagingView,
		Translations,
		TestTemplatesTemplate) {

	var TestTemplatesView = AbstractView.extend({
		el: '#content',
		templateHTML: _.template(TestTemplatesTemplate),
		paging: null,
		events: {
			'click .fixed': 'switchToFixed',
			'click .random': 'switchToRandom',
		},
		initialize: function(options) {
			var self = this;
			
			this.render();

			// Fetch templates
			this.collection = new TestTemplatesCollection();
			// Bind add and reset events so collection changes are rendered automatically
			this.collection.bind('changed', this.refreshTemplates, this);
			this.collection.fetch();
			
			// Paging
			this.paging = new PagingView({collection : self.collection});
			this.$el.append(this.paging.render().$el);
		},
		render: function() {
			// Set page content
			this.$el.html(this.templateHTML({
				t: Translations
			}));
			
			return this;
		},
		
		/**
		 * Append single template
		 */
		appendTemplate: function(template) {
			var self = this;
			
			var testTemplateItemView = new TestTemplateItemView({
				model: template,
                deleteCallback: function () {
                    self.collection.fetch();
                }
			});
			this.$('.templatesList').append(testTemplateItemView.render().el);
		},
		
		/**
		 * Render/refresh templates
		 */
		refreshTemplates: function() {
			var self = this;
			
			this.clearTemplates();
			this.collection.each(function(template) {
				self.appendTemplate(template);
			});
		},
		
		/**
		 * Clear templates
		 */
		clearTemplates: function() {
			this.$('.templatesList').empty();
		},
		
		switchToFixed: function() {
			Backbone.history.navigate("/fixedTestTemplates", {trigger:true});
		}
	});
	
	return TestTemplatesView;
});