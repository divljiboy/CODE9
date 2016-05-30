/**
 * @author a.stoisavljevic
 * @date: March 2014.
 * 
 * This is example how to define Backbone Category View that should be rendered
 * as part of list of categories
 * 
 */
Code9.CategoriesListView = Backbone.View.extend({
    template: _.template($("#tpl-categories").html()),

    initialize: function() {
        Code9.headerView = new Code9.HeaderView();
        Code9.headerView.render();
    	
        _.bindAll(this, 'render', 'renderOne');
    },

    renderOne: function(model) {
        var categoryView = new Code9.CategoryView({model: model});
        this.$("tbody").append(categoryView.render().$el);
    },

    render: function() {
        var $categories, collection = this.collection;
        $(this.el).append(this.template({}));
        $categories = this.$("tbody");
        this.collection.each(this.renderOne, this);
        return this;
    },

    events: {
        "click #btnAddNewCategory": "addNewCategory"
    },

    addNewCategory: function() {
        Code9.appRouter.navigate('add-new-category', true);
    }

});
