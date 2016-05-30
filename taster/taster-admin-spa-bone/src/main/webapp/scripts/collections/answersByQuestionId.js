define([ 
    	'jquery',
    	'backbone',
    	
    	'constants', 
    	
    	'collections/abstractCollection', 
    	'models/answer', 
    	
    	'backbonePaginator'
], function($, Backbone, 
		Constants, 
		AbstractCollection, AnswerModel) {

	var AnswersByQuestionIdCollection = AbstractCollection.extend({
		questionId : null,
		model: AnswerModel,
		initialize: function(options) {
			this.questionId = options.questionId;
		},
		url: function() {
			return Constants.apiRootUrl + '/questions/' + this.questionId + '/answers';
		},
	});
	
	return AnswersByQuestionIdCollection;
});
