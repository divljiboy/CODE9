define([ 
    	'jquery',
    	'backbone',
    	
    	'constants', 
    	
    	'collections/abstractCollection', 
    	'models/testTemplate', 
    	
    	'backbonePaginator'
], function($, Backbone, 
		Constants, 
		AbstractCollection, TestTemplateModel) {

	var TestTemplatesCollection = AbstractCollection.extend({
		model: TestTemplateModel,
		url: function() {
			return Constants.apiRootUrl + '/testTemplates';
		}
	});
	
	return TestTemplatesCollection;
});
