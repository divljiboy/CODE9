define([ 
    	'jquery',
    	'backbone',
    	
    	'constants', 
    	
    	'collections/abstractCollection', 
    	'models/category', 
    	
    	'backbonePaginator'
], function($, Backbone, 
		Constants, 
		AbstractCollection, CategoryModel) {

	var CategoriesCollection = AbstractCollection.extend({
		model: CategoryModel,
		url: function() {
			return Constants.apiRootUrl + '/categories';
		}
	});
	
	return CategoriesCollection;
});
