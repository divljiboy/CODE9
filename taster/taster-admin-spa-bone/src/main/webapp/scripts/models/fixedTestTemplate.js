define([ 
	'jquery', 
	'backbone',
	
	'constants', 
	
	'models/testTemplate',
	
	'collections/questions'
], function($, Backbone,
		Constants,
		TestTemplateModel,
		QuestionCollection) {

	var FixedTestTemplateModel = TestTemplateModel.extend({
		idAttribute: "id",
		defaults : {
			id : null,
			name : null,
			questions : null,
		},
		initialize : function(attributes, options) {		
			this.id = attributes.id;
			this.name = attributes.name;
			this.questions = attributes.questions;
			
		},
		urlRoot : function() {
			return Constants.apiRootUrl + '/fixedTestTemplates';
		}
	});
	
	return FixedTestTemplateModel;
});
