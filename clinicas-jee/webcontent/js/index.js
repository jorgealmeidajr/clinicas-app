
const animationTimeMillis = 1500;

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
				left : "+=160",
				height : "+=110"
			}, animationTimeMillis, function() { });
			
			$("#content").animate({
				height : "+=110"
			}, animationTimeMillis, function() { });
			
			$("#content-pane").animate({
				"margin-left" : "+=160px",
			}, animationTimeMillis, function() { });
			
			$("#header").animate({
				height : "-=110"
			}, animationTimeMillis, function() {
				$("#header").css('display', 'none');
			});
		}
	},
	
	doAfterLogout : function() {
		$("#menu-panel").animate({
			height : "-=110"
		}, animationTimeMillis, function() { });
		
		$("#content").animate({
			height : "-=110"
		}, animationTimeMillis, function() { });
		
		$("#content-pane").animate({
			"margin-left" : "-=160px"
		}, animationTimeMillis, function() { });
		
		$("#header").animate({
			height : "+=110"
		}, animationTimeMillis, function() {
			$("#header").css('display', 'block');
		});
	}
};

$(document).ready(function() {
	indexController.atualizarMenuPanel();
});
