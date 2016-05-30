$(document).ready(function() {
	//align main menu items
	var menu = new TMainMenu('mainMenu');
	menu.alignMenuItems();
	$('input[type="text"]:first').focus();
});

function TMainMenu(unorderedListElement) {
	if (!unorderedListElement) return;
	if (typeof unorderedListElement === 'object' && typeof unorderedListElement.nodeName != 'undefined' && unorderedListElement.nodeName === 'ul') {
		this.root = $(unorderedListElement);
	} else if (typeof unorderedListElement === 'string') {
		this.root = $('#' + unorderedListElement);
	} else return;
}
TMainMenu.prototype.alignMenuItems = function() {
	if (typeof this.root === 'undefined') return;
	var menuItems = $('li', this.root);
	var idealWidth = Math.floor(this.root.outerWidth() / menuItems.length) - 18;
	menuItems.each(function() {
		var margin = Math.floor((idealWidth - $(this).outerWidth()) / 2) - 2;
		$(this).css({
			'width': idealWidth + 'px',
			'margin-right': '8px',
			'margin-right': '8px'
		});
	});
}
