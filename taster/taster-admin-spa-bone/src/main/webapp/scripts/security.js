/**
 * OAuth2 Implicit workflow support.
 * 
 * Query parameters:
 * 	response_type - Required. Must be set to token .
 * 	client_id - Required. The client identifier as assigned by the authorization server, when the client was registered.
 * 	redirect_uri - Optional. The redirect URI registered by the client.
 * 	scope - Optional. The possible scope of the request.
 * 	state - Optional (recommended). Any client state that needs to be passed on to the client request URI.
 */
define([ 
	'jquery',
	'underscore',
	'backbone',
	
	'constants'
], function($, _, Backbone, Constants) {
	
	/**
	 * Security AJAX setup.
	 * 
	 * By default all requests have Authorization header added.
	 */
	$.ajaxSetup({
		beforeSend : function(request) {
			request.setRequestHeader('Authorization', 'Bearer ' + Security.getLocalToken());
		}
	});

	/**
	 * Handle unauthorized errors.
	 * 
	 * In case of UNAUTHORIZED (401) try reauthorization.
	 */
	$(document).ajaxError(function (e, jqXHR, ajaxSettings, errorThrown) {
		if (jqXHR.status == '401') {
			jqXHR.handeled = true;
			// Use current URL as redirect URI so users is brought back to the page he originally requested.
			Security.authorize(window.location.href);
		}
	});
	
	var Security = {
		tokenStorageParam : 'accessToken',
		clientId: 'levi9-taster-spa-client',
		redirectUri: '/taster-admin-spa-bone',
			
		/**
		 * Parse redirect URL and extract access token if it exists.
		 */
		parseToken: function(href) {
			var regex = new RegExp("[^&#]*access_token=([^&#]*)");
			
			if (regex.test(href)) {
				var accessToken = regex.exec(href)[1];
				localStorage.setItem(this.tokenStorageParam, accessToken);
			}
		},
		
		/**
		 * Get the current local value of security access token.
		 */
		getLocalToken: function() {
			return localStorage[this.tokenStorageParam];
		},
		
		/**
		 * Get the current value of security access token.
		 */
		getToken: function(successCallback, failedCallback) {
			$.ajax({
				url: Constants.apiRootUrl + "/token",
				type: 'GET',
				async: false,
				global: false
			})
			.done(function(data) {
				successCallback(data);
			})
			.fail(function(jqXHR, textStatus, errorThrown) {
				jqXHR.handeled = true;
				failedCallback();
			});
		},
		
		/**
		 * Authorize.
		 */
		authorize: function(redirectUri) {
			var self = this;
			
			var queryParameters = $.param({
				response_type: 'token',
				client_id: self.clientId,
				redirect_uri: redirectUri,
				scope: 'api'
			});
			
			window.location = Constants.oauthRootUrl + '/authorize?' + queryParameters;
		},
		
		/**
		 * Check if already authorized by checking if token is available and valid.
		 * If not authorized, authentication page will be displayed.
		 */
		checkIfAuthorized: function(successCallback) {
			var self = this;
			
			this.getToken(
				function () {
					if (successCallback) {
						successCallback();
					}
				},
				function () {
					// Not authorized, trigger new authorization process
					self.authorize(window.location.href);
				}
			);
		},
		
		/**
		 * Logout.
		 */
		logout: function() {
			var self = this;
			
			$.ajax({
				url: Constants.apiRootUrl + "/token",
				type: 'DELETE',
				async: false
			}).done(function(response) {
				localStorage.removeItem(self.tokenStorageParam);
				self.authorize(self.redirectUri);
			});
		}
	};
		
	return Security;
	
});
