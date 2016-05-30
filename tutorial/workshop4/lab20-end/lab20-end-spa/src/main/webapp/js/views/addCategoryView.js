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
    	var self = this;
        var categoryName = $('input[name="categoryName"]').val();
        var newCategory = new Code9.Category({name: categoryName});
        
        newCategory.save(null, {
            success: function(model, response, options) {
                self.unset();
                Code9.appRouter.navigate('categories', true);
            },
            error: function(model, response, options) {
                console.log("-+- error -+-");
            }
        });
    },

    cancelCategory: function() {
        Code9.appRouter.navigate('categories', true);
    },
    
    unset: function() {
        console.log('Unsetting view with id: ', this.cid, this.el ? ' and element: '+this.el.tagName+'#'+this.el.id : '');

        //COMPLETELY UNBIND THE VIEW
        this.undelegateEvents();

        this.$el.removeData().unbind();

        //Remove view from DOM
        this.$el.empty();
        this.stopListening();
    }
    
});