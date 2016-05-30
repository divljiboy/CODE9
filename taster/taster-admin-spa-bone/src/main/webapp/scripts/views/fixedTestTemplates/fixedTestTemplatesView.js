define([ 
    'jquery',
    'underscore',
    'backbone',
    
    'views/abstractView',
    
    'util',
    
    'collections/fixedTestTemplates',
    
    'views/fixedTestTemplates/fixedTestTemplateAddEditView',
    'views/fixedTestTemplates/fixedTestTemplateItemView',
    
    'views/pagingView',
    
    'i18n!nls/translations',
    
    'text!template/fixedTestTemplates.html'
], function($, _, Backbone,
		AbstractView,
		Util,
		FixedTestTemplatesCollection,
		AddEditFixedTemplateView,
		FixedTestTemplateItemView, PagingView,
		Translations,
		FixedTestTemplatesTemplate) {

	var FixedTestTemplatesView = AbstractView.extend({
		el: '#content',
		templateHTML: _.template(FixedTestTemplatesTemplate),
		paging: null,
		events: {
			'click .add': 'addTemplate',
			'click .all': 'switchToAll',
			'click .random': 'switchToRandom',
		},
		initialize: function(options) {
			var self = this;
			
			this.render();

			this.collection = new FixedTestTemplatesCollection();
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
			
			var fixedTestTemplateItemView = new FixedTestTemplateItemView({
				model: template,
                deleteCallback: function () {
                    self.collection.fetch();
                },
			    saveCallback: function() {
			    	self.collection.fetch();
			    }
			});
			this.$('.templatesList').append(fixedTestTemplateItemView.render().el);
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
		 * New template
		 */
		addTemplate: function() {
			var self = this;
			
			new AddEditFixedTemplateView({
				saveCallback: function(newTemplate) {
					self.collection.add(newTemplate);
					self.refreshTemplates();
				}
			});
		},
		
		/**
		 * Clear templates
		 */
		clearTemplates: function() {
			this.$('.templatesList').empty();
		},
		
		/**
		 * Switch to all templates view
		 */
		switchToAll: function() {
			Backbone.history.navigate('/testTemplates', {trigger:true});
		},
	});
	
	return FixedTestTemplatesView;
});