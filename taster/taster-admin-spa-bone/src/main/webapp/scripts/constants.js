define([
	'jquery',
	'backbone',
], function ($, Backbone) {
    var Constants = {
    	// REST API URL
        apiRootUrl: '/taster-rest-api/api',
        oauthRootUrl: '/taster-auth/oauth',
        
        // Paging
        itemsPerPage: 10
    };

    return Constants;
});
