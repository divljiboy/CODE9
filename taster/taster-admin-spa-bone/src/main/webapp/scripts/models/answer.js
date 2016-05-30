define([ 
	'jquery', 
	'backbone',
	
	'constants', 
], function($, Backbone,
		Constants) {

	var AnswerModel = Backbone.Model.extend({
		idAttribute: "id",
		defaults : {
			id : null,
			answerContent : null,
			correct : null,
			questionId : null,
		},
		initialize : function(attributes, options) {		
			this.id = attributes.id;
			this.answerContent = attributes.answerContent;
			this.correct = attributes.correct;
			this.questionId = attributes.questionId;
		},
		urlRoot : function() {
			return Constants.apiRootUrl + '/answers';
		}
	});
	
	return AnswerModel;
});