define([ 
    	'jquery',
    	'backbone',
    	
    	'constants', 
    	
    	'backbonePaginator'
], function($, Backbone, 
		Constants) {

	var AbstractCollection = Backbone.PageableCollection.extend({
		// Once initialized and bootstrapped, paging backwards will be done on the client-side by default while paging forward will be done by fetching.
		mode: "server",
		state: {
			firstPage: 0,
			currentPage: 0,
			pageSize: Constants.itemsPerPage
		},
		queryParams: {
		    currentPage: "number",
		    pageSize: "size"
		},
		/**
		 * Fix for backbone paginator bug
		 */
		fetch: function(options) {
            var result = Backbone.PageableCollection.prototype.fetch.call(this, options);
            
            this.state.totalRecords = parseInt(result.getResponseHeader("X-total"));
            this.state.totalPages = this.state.totalRecords == 0 ? 1 : Math.ceil(this.state.totalRecords / this.state.pageSize);
            this.state.lastPage = this.state.totalPages - 1;
            
            if (this.state.currentPage > this.state.lastPage) {
            	this.state.currentPage = this.state.lastPage;
            	
            	result = Backbone.PageableCollection.prototype.fetch.call(this, options);
            }
            
            // Custom event
            this.trigger('changed');

            return result;
        }
	});
	
	return AbstractCollection;
});
