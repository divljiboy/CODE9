/**
 * @author a.stoisavljevic
 * @date: March 2014.
 * 
 * This is example how to create Category and save it
 *  
 */
Code9.NewCategoryView = Backbone.View.extend({
    el: $('#mainArea'),

    template: _.template($("#tpl-add-category").html()),
    
    initialize: function() {
        Code9.headerView = new Code9.HeaderView();
        Code9.headerView.render();
    },

    render: function() {
        $(this.el).append(this.template());
        return this;
    },

    events: {
        "click #btnSubmitCategory": "submitCategory",
        "click #btnCancelCategory": "cancelCategory"
    },

    submitCategory: function() {
        var categoryName = $('input[name="categoryName"]').val();
        var newCategory = new Code9.Category({name: categoryName});
        newCategory.save();
        Code9.appRouter.navigate('categories', true);
    },

    cancelCategory: function() {
        Code9.appRouter.navigate('categories', true);
    }

});