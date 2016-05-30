define([ 
    'jquery',
    'underscore',
    'backbone',
    'backboneEpoxy'
], function($, _, Backbone) {

	var AbstractView = Backbone.View.extend({
		close : function() {
			this.$el.html('');
			this.stopListening();
			this.undelegateEvents();
		}
	});
	
	return AbstractView;
});