/**
 * @author a.stoisavljevic
 * @date: March 2014.
 * 
 */
Code9.HomeView = Backbone.View.extend({
	
	el: $('#mainArea'),
	
	template: _.template($("#tpl-page-home").html()),

	
    initialize: function() {
        Code9.headerView = new Code9.HeaderView();
        Code9.headerView.render();
    },

	render: function() {
		$(this.el).append(this.template());
		return this;
	}

	
});