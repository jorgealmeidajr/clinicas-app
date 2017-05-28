
var indexController = {
	
	doAfterTryLogin : function() {
		if($('#menu-panel').html().toString().replace(/ /g,'') !== "") {
			$("#menu-panel").animate({
				left : "+=160px"
			}, 1500, function() { });
		}
	}

};