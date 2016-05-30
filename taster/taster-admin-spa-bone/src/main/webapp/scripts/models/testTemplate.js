define([ 
	'jquery', 
	'backbone',
	
	'constants', 
], function($, Backbone,
		Constants) {

	var TestTemplateModel = Backbone.Model.extend({
		idAttribute: "id",
		defaults : {
			id : null,
			name : null,
			type : null,
		},
		initialize : function(attributes, options) {		
			this.id = attributes.id;
			this.name = attributes.name;
			this.type = attributes.type;
		},
		urlRoot : function() {
			return Constants.apiRootUrl + '/testTemplates';
		}
	});
	
	return TestTemplateModel;
});