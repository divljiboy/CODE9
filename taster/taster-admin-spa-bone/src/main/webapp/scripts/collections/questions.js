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

	var QuestionsCollection = AbstractCollection.extend({
		model: QuestionModel,
		url: function() {
			return Constants.apiRootUrl + '/questions';
		}
	});
	
	return QuestionsCollection;
});
