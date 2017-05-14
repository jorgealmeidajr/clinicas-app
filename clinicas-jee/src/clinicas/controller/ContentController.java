package clinicas.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class ContentController implements Serializable {

	private static final long serialVersionUID = -2695167817323691842L;
	
	private String paginaAtual;

	@PostConstruct
	public void init() {
		exibirPaginaInicial();
	}
	
	public void exibirPaginaInicial() {
		paginaAtual = "buscar-medicos.xhtml";
	}
	
	public void exibirCadastrarUsuario() {
		paginaAtual = "cadastrar-usuario.xhtml";
	}
	
	public void exibirListarMedicos() {
		paginaAtual = "app/medicos/listar-medicos.xhtml";
	}

	public String getPaginaAtual() {
		return paginaAtual;
	}

}
