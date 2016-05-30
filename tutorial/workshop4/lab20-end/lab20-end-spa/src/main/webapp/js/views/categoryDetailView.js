/**
 * @author a.stoisavljevic
 * @date: March 2014.
 * 
 * This is example how to define Backbone Category detail view to present one
 * single category
 * 
 */
Code9.CategoryDetailView = Backbone.View.extend({

    template: _.template($("#tpl-category-detail").html()),
    
    initialize: function() {
        headerView = new Code9.HeaderView();
        headerView.render();
    },

    render: function() {
        $(this.el).html(this.template(this.model.toJSON()));
        return this;
    },
    
    events: {
    	"click #btnBackToCategories": "backToCategories"
    },
    
    backToCategories: function() {
    	Code9.appRouter.navigate('categories', true);
    }
    

});