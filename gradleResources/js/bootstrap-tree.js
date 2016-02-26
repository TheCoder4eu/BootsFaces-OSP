function initBsTree() {
	$('.tree > ul').attr('role', 'tree').find('ul').attr('role', 'group');
	$('.tree').find('li:has(ul)').addClass('parent_li').attr('role', 'treeitem').find(' > span').attr('title', 'Collapse this branch').on('click', function (e) {
        var children = $(this).parent('li.parent_li').find(' > ul > li');
        if (children.is(':visible')) {
    		children.hide('fast');
    		$(this).attr('title', 'Expand this branch').prev('i.sign').addClass('glyphicon-plus').removeClass('glyphicon-minus');
        }
        else {
    		children.show('fast');
    		$(this).attr('title', 'Collapse this branch').prev('i.sign').addClass('glyphicon-minus').removeClass('glyphicon-plus');
        }
        e.stopPropagation();
    });
}

function bsTreeToggleVisibility() {
	$('.tree').find('span').click();
}