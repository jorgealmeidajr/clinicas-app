package clinicas.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import clinicas.model.Usuario;
import clinicas.service.UsuarioService;

@Named
@SessionScoped
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = 8666699850926627315L;

	private Usuario usuario;
	
	@Inject
	private UsuarioService service;
	
	@Inject
	private LoginController loginController;
	@Inject
	private ContentController contentController;
	
	@PostConstruct
	public void init() {
		usuario = new Usuario();
	}

	public void salvar() {
		try {
			service.salvar(usuario);
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(e.getMessage()));
		}
	}

	public void editar() {
		usuario = loginController.getUsuario();
		contentController.exibirCadastrarUsuario();
	}
	
	public void atualizar() {
		service.atualizar(usuario);
	}

	public Usuario getUsuario() {
		return usuario;
	}

}
