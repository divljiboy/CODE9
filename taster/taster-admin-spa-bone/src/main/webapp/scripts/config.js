define([ 
	'jquery',
	'backbone',

	'util'
], function($, Backbone, 
		Util) {
	
	/**
	 * Require JS custom pre config.
	 * Can be used to initialize some dependency before dependency context is initialized.
	 */
	require.config({
		// Pre-initialization
	});
	
	var Config = {
			
		/**
		 * Setup default AJAX options
		 */
		setupAjax: function() {
			
			/**
			 * General AJAX setup.
			 * 
			 * By default all requests are synchronous and cache is turned off.
			 */
			$.ajaxSetup({
				async : false,
				cache : false,
				retryMax : 2
			});
			
			/** 
			 * AJAX default error handler.
			 */
			$(document).ajaxError(function (e, jqXHR, ajaxSettings, errorThrown) {
				// Invalid input field values
				if (jqXHR.status == 406) {
					var jsonErrorObj = jQuery.parseJSON(jqXHR.responseText);

					var unshownMessages = new Array();
					for (var key in jsonErrorObj) {
						var errorMessage = jsonErrorObj[key];
						
						// Get just key for nested fields
						if(key.lastIndexOf(".") != -1) {
							key = key.substring(key.lastIndexOf(".") + 1);
						}
						
						var field = $('.' + key);
						if(field.length == 0) {
							// Not found by class? Try by name
							field = $('[name=' + key + ']');
						}
						
						if (field.length > 0) {
							field.addClass('error-field');
							field.addClass('has-error');
							field.attr("title", errorMessage);
						} else {
							// Field not found
							if (errorMessage) {
								unshownMessages.push("• " + errorMessage + " - " + key);
							}
						}
					}
					// Handle unshown messages
					if (unshownMessages.length > 0) {
						var error = "";
						for (var i = 0; i < unshownMessages.length; i++) {
							error += unshownMessages[i];
							error += '<br/>';
						}
						Util.showGeneralErrorDialog(error);
					}
					
					jqXHR.handled = true;
				}
				// Global error
				if (!jqXHR.handled) {
					Util.showGeneralErrorDialog(jqXHR.responseText);
				}
			});
		}
	};
	
	
	return Config;
	
});
