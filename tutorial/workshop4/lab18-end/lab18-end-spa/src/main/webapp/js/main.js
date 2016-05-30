/**
 * Created by astoisavljevic on 2/17/14.
 */

var AppRouter = Backbone.Router.extend({

        routes: {
            "": "home",
            "home": "home"
        },

        home: function() {
            console.log("home");
        }
});

var appRouter = new AppRouter();

Backbone.history.start();