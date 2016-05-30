/**
 * @author a.stoisavljevic
 * @date: March 2014.
 * 
 * This is example how to define Backbone Category View that should be rendered
 * as part of list of categories
 * 
 */
Code9.CategoryView = Backbone.View.extend({

    render: function() {
        var html = _.template($("#tpl-category").html(), this.model.toJSON());
        this.setElement($(html));
        return this;
    },

    events: {
        "click button.btn-delete": "deleteCategory",
        "click button.btn-view": "viewCategory"
    },

    deleteCategory: function() {
        var categoryToRemove = this.model.get("id");
        this.model.destroy();
        this.remove();
    },

    viewCategory: function(ev) {
        Code9.appRouter.navigate('category/' + $(ev.target).data("id"), true);
    }

});

