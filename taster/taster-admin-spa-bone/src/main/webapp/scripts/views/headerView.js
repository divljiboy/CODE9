define([ 
    'jquery',
    'underscore',
    'backbone',
    
    'views/abstractView',
    
    'security',
    'util',
    
    'i18n!nls/translations',
    
    'text!template/header.html',
], function($, _, Backbone,
		AbstractView,
		Security, Util,
		Translations,
		HeaderTemplate) {

	var HeaderView = AbstractView.extend({
		el: '#header',
		templateHTML: _.template(HeaderTemplate),
		events: {
			'click .logout' : 'logout',
			'click .categories' : 'categories',
			'click .questions' : 'questions',
			'click .testTemplates' : 'testTemplates',
		},
		initialize: function(options) {
			this.render();
		},
		render: function() {
			// Set page content
			this.$el.html(this.templateHTML({
				t : Translations
			}));
			
			return this;
		},
		logout: function() {
			Security.logout();
		},
		categories: function() {
			Backbone.history.navigate('categories', true);
		},
		questions: function() {
			Backbone.history.navigate('questions', true);
		},
		testTemplates: function() {
			Backbone.history.navigate('testTemplates', true);
		}
	});
	
	return HeaderView;
});