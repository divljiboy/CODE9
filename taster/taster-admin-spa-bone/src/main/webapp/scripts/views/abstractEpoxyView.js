define([ 
    'jquery',
    'underscore',
    'backbone',
    'backboneEpoxy'
], function($, _, Backbone) {

	var AbstractEpoxyView = Backbone.Epoxy.View.extend({
		close : function() {
			this.$el.html('');
			this.stopListening();
			this.undelegateEvents();
		}
	});
	
	return AbstractEpoxyView;
});