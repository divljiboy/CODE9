/**
 * @author a.stoisavljevic
 * @date: March 2014.
 * 
 * This is example how to define Backbone Categories Collection
 * 
 */
Code9.Categories = Backbone.Collection.extend({
    model: Code9.Category,
    url: Code9.Config.applicationServletPath + "categories/"
});

