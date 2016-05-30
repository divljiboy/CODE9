define([ 
	'jquery', 
	'backbone', 
         
	'bootstrap-dialog'
], function($, Backbone,
		BootstrapDialog) {
	
	var Util = {
			
		/**
		 * Show info dialog
		 */
		showInfoDialog: function(title, text) {
			BootstrapDialog.show({
	            title: title,
	            message: text,
	            type: BootstrapDialog.TYPE_INFO,
	            closable: false,
	            buttons: [{
	                label: 'Ok',
	                action: function(dialogItself){
	                    dialogItself.close();
	                }
	            }]
	        });
		},
		
		/**
		 * Show general error pop-up dialog
		 */
		showGeneralErrorDialog: function(text) {
			BootstrapDialog.show({
	            title: 'Warning',
	            message: text,
	            type: BootstrapDialog.TYPE_DANGER,
	            closable: false,
	            buttons: [{
	                label: 'Close',
	                action: function(dialogItself){
	                    dialogItself.close();
	                }
	            }]
	        });
		}
	};
	
	return Util;
});
