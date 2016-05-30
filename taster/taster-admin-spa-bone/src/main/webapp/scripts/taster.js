require.config({
	paths: {
	    jquery: 'libs/jquery/jquery-2.1.0.min',
		underscore: 'libs/underscore/underscore-min',
		backbone: 'libs/backbone/backbone-min',
		
		text: 'libs/requirejs/text',
		i18n: 'libs/requirejs/i18n',
		
		// Adds support for before route and after route handlers
		backboneRouteFilter: 'libs/backbone/backbone.routefilter.min',
		// Model binding (declarative)
		backboneEpoxy: 'libs/backbone/backbone.epoxy.min',
		// Collection paging - supports infinite mode
		backbonePaginator: 'libs/backbone/backbone.paginator.min',
		backboneInfiniScroll: 'libs/backbone/infiniScroll',
		
		bootstrap: 'libs/bootstrap/bootstrap.min',
		'bootstrap-dialog': 'libs/bootstrap/bootstrap-dialog.min',
				
		select2: 'libs/select2/select2.min',
		
		template: '../templates'
	},
	shim: {
		underscore: {
			exports: '_'
		},
		backbone: {
			deps: ['underscore', 'jquery'],
			exports: 'Backbone'
		},
		backboneRouteFilter: ['backbone'],
		backboneInfiniScroll: ['backbone'],
		bootstrap: ['jquery'],
		'bootstrap-dialog': ['bootstrap']
	}
});
require([
// Load our app module and pass it to our definition function
'app', 
// Pre dependency initialization config
'config'], function(App) {
	App.initialize();
});
