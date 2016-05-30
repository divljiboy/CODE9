define([ 
    	'jquery',
    	'backbone',
    	
    	'constants', 
    	
    	'collections/abstractCollection', 
    	'models/question', 
    	
    	'backbonePaginator'
], function($, Backbone, 
		Constants, 
		AbstractCollection, QuestionModel) {

	var questionsByFixedTestTemplateIdCollection = AbstractCollection.extend({
		id : null,
		model: QuestionModel,
		initialize: function(options) {
			this.id = options.id;
		},
		url: function() {
			return Constants.apiRootUrl + '/fixedTestTemplates/' + this.id + '/questions';
		},
	});
	
	return questionsByFixedTestTemplateIdCollection;
});
