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

	var QuestionsByCategoryIdCollection = AbstractCollection.extend({
		categoryId : null,
		model: QuestionModel,
		initialize: function(options) {
			this.categoryId = options.categoryId;
		},
		url: function() {
			return Constants.apiRootUrl + '/categories/' + this.categoryId + '/questions';
		}
	});
	
	return QuestionsByCategoryIdCollection;
});
