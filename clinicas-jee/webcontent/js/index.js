
var indexController = {
	atualizarMenuPanel : function() {
		if($('#menu-panel').html() !== "") {
	    	// usuario logou com sucesso, posso exibir o menu
	    	$('#menu-panel').css('left', '0px');
	    } else {
	    	// usuario nao esta logado, aumento a area do content pane
	    	$('#content-pane').css('margin-left', '0px');
	    }
	},
	
	doAfterTryLogin : function() {
		if($('#menu-panel').html().toString().replace(/ /g,'') !== "") {
			$("#menu-panel").animate({
				"left" : "+=160px"
			}, 1500, function() { });
			
			$("#content-pane").animate({
				"margin-left" : "+=160px"
			}, 1500, function() { });
		}
	},
	
	doAfterLogout : function() {
		$("#content-pane").animate({
			"margin-left" : "-=160px"
		}, 1500, function() { });
	}
};

$(document).ready(function() {
	indexController.atualizarMenuPanel();
});
