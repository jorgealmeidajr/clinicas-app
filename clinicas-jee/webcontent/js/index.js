
const constantes = {
	header : {
		height : 100,
		min_height : 40
	},
	
	menu_panel : {
		width : 160
	},
	
	getExpansaoHeader : function() {
		return this.header.height - this.header.min_height;
	}
};

const animationTimeMillis = 1500;

var indexController = {
	atualizarPaginaIndex : function() {
		if($('#menu-panel').html() !== "") {
	    	// usuario logou com sucesso, posso exibir o menu
	    	$('#menu-panel').css('left', '0px');
	    	$('#header').css('height', constantes.header.min_height + 'px');
	    } else {
	    	// usuario nao esta logado, aumento a area do content pane
	    	$('#content-pane').css('margin-left', '0px');
	    	$('#header').css('height', constantes.header.height + 'px');
	    }
	},
	
	doAfterTryLogin : function() {
		if($('#menu-panel').html().toString().replace(/ /g,'') !== "") {
			$("#menu-panel").animate({
				left : "+=" + constantes.menu_panel.width,
				height : "+=" + constantes.getExpansaoHeader()
			}, animationTimeMillis, function() { });
			
			$("#content").animate({
				height : "+=" + constantes.getExpansaoHeader()
			}, animationTimeMillis, function() { });
			
			$("#content-pane").animate({
				"margin-left" : "+=" + constantes.menu_panel.width,
			}, animationTimeMillis, function() { });
			
			$("#header").animate({
				height : "-=" + constantes.getExpansaoHeader()
			}, animationTimeMillis, function() {
				//$("#header").css('display', 'none');
			});
		}
	},
	
	doAfterLogout : function() {
		$("#menu-panel").animate({
			height : "-=" + constantes.getExpansaoHeader()
		}, animationTimeMillis, function() { });
		
		$("#content").animate({
			height : "-=" + constantes.getExpansaoHeader()
		}, animationTimeMillis, function() { });
		
		$("#content-pane").animate({
			"margin-left" : "-=" + constantes.menu_panel.width
		}, animationTimeMillis, function() { });
		
		$("#header").animate({
			height : "+=" + constantes.getExpansaoHeader()
		}, animationTimeMillis, function() {
			//$("#header").css('display', 'block');
		});
	}
};

$(document).ready(function() {
	indexController.atualizarPaginaIndex();
});
