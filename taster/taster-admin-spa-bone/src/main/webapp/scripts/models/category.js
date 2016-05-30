define([ 
	'jquery', 
	'backbone',
	
	'constants', 
], function($, Backbone,
		Constants) {

	var CategoryModel = Backbone.Model.extend({
		idAttribute: "id",
		defaults : {
			id : null,
			name : null
		},
		initialize : function(attributes, options) {		
			this.id = attributes.id;
		},
		urlRoot : function() {
			return Constants.apiRootUrl + '/categories';
		}
	});

	return CategoryModel;
});