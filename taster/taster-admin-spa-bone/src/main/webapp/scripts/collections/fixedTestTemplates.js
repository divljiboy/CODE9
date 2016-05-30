define([ 
    	'jquery',
    	'backbone',
    	
    	'constants', 
    	
    	'collections/abstractCollection', 
    	'models/fixedTestTemplate', 
    	
    	'backbonePaginator'
], function($, Backbone, 
		Constants, 
		AbstractCollection, FixedTestTemplateModel) {

	var FixedTestTemplatesCollection = AbstractCollection.extend({
		model: FixedTestTemplateModel,
		url: function() {
			return Constants.apiRootUrl + '/fixedTestTemplates';
		}
	});
	
	return FixedTestTemplatesCollection;
});
