define([ 
	'jquery',
	'backbone',
	
	'security',
	
	'router',
	
	'config'
], function($, Backbone,
		Security,
		Router,
		Config) {
	
	// Setup AJAX
	Config.setupAjax();

	var initialize = function() {
		// Check if access_token parameter is present and parse it
		Security.parseToken(document.location.hash);
		
		// Check if already authorized
		Security.checkIfAuthorized(function () {
			// Entry point
			Backbone.history.start({
				root: 'categories'
			});
		});
	};
	
	return {
		initialize: initialize 
	};
});
