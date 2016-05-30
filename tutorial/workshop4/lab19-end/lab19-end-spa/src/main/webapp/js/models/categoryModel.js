/**
 * @author a.stoisavljevic
 * @date: March 2014.
 * 
 * This is example how to define Backbone Category Model
 * 
 */
Code9.Category = Backbone.Model.extend({
    urlRoot: Code9.Config.applicationServletPath + 'categories/',
    defaults: {
        name: 'unknown'
    }
});
