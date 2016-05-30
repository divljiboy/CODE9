define([ 
	'jquery', 
	'backbone',
	
	'constants', 
], function($, Backbone,
		Constants) {

	var QuestionModel = Backbone.Model.extend({
		idAttribute: "id",
		defaults : {
			id : null,
			content : null,
			tags : [],
			categoryId : null,
			categoryName : null,
			answers : null,
		},
		initialize : function(attributes, options) {		
		},
		urlRoot : function() {
			return Constants.apiRootUrl + '/questions';
		}
	});
	
	return QuestionModel;
});