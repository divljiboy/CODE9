define([ 
    'jquery',
    'underscore',
    'backbone',
    
    'views/abstractView',
    
    'util',
    
    'i18n!nls/translations',
    
    'text!template/paging.html'
], function($, _, Backbone,
		AbstractView,
		Util,
		Translations,
		PagingTemplate) {

	var PagingView = AbstractView.extend({
		templateHTML: _.template(PagingTemplate),
		events: {
			'click a.previous': 'previous',
		    'click a.next': 'next'
		},
		initialize: function(options) {
			this.collection = options.collection;
			
			this.collection.bind('changed', this.render, this);
		},
		render: function() {
			var self = this;
			
			// Set page content
			this.$el.html(this.templateHTML({
				collection: self.collection,
				t: Translations
			}));
			
			return this;
		},
		previous: function(e) {
			e.preventDefault();
		    this.collection.getPreviousPage();
		    this.render();
		},
		next: function(e) {
			e.preventDefault();
		    this.collection.getNextPage();
		    this.render();
		}
	});
	
	return PagingView;
});