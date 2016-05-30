/**
 * @author a.stoisavljevic
 * @date: March 2014.
 * 
 */
Code9.AppRouter = Backbone.Router.extend({

        routes: {
            "": "index",
            "home": "home",
            "about": "about",
            "categories": "categories",
            "category/:id": "category",
            "add-new-category": "addNewCategory"
        },
        
        index: function() {
        	Code9.indexView = new Code9.IndexView();
        	Code9.indexView.render();
        },

        home: function() {
        	Code9.homeView = new Code9.HomeView();
        	Code9.homeView.render();
        	
        },
        
        about: function() {
        	Code9.aboutView = new Code9.AboutView();
        	Code9.aboutView.render();
        },
        
        categories: function() {
            var categories = new Code9.Categories();
            categories.fetch({
                success: function(model, response, options) {
                    var categoriesListView = new Code9.CategoriesListView({collection: model});
                    $("#mainArea").append(categoriesListView.render().$el);
                },
                error: function(model, response, options) {
                    console.log("-+- there was an error fetching list of categories from server -+-");
                }
            });
        },
        category: function(id) {
            category = new Code9.Category({"id": id});
            categoryDetailView = new Code9.CategoryDetailView({model: category});
            category.fetch({
                success: function(model, response, options) {
                    $("#mainArea").append(categoryDetailView.render().$el);
                },
                error: function(model, response, options) {
                    console.log("-+- there was an error fetching category from server -+-");
                }
            });
        },
        
        addNewCategory: function() {
            addNewCategoryView = new Code9.NewCategoryView();
            addNewCategoryView.render();
        }
        
        
});

Code9.appRouter = new Code9.AppRouter();

Backbone.history.start();