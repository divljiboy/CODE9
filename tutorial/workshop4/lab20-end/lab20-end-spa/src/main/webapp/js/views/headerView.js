/**
 * @author a.stoisavljevic
 * @date March 2014.
 * 
 */

Code9.HeaderView = Backbone.View.extend({
    el: $('#mainArea'),
    template: _.template($('#tpl-header').html()),
    render: function() {
        $(this.el).html(this.template());
        return this;
    }
});