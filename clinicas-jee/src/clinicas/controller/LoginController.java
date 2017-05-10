package clinicas.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import clinicas.model.Usuario;
import clinicas.service.UsuarioService;

@Named
@SessionScoped
public class LoginController implements Serializable {

	private static final long serialVersionUID = 6443736038981020368L;

	private String email;
	private String senha;
	
	private Usuario usuario;
	
	@Inject
	private ContentController contentController;
	
	@Inject
	private UsuarioService usuarioService;
	
	public LoginController() {
		System.out.println(">>login_controller criado");
	}
	
	public void login() {
		try {
			usuario = usuarioService.verificarLogin(email, senha);
		} catch (Exception e) {
			usuario = null;
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(e.getMessage()));
		}
	}
	
	public void logout() {
		usuario = null;
		email = "";
		senha = "";
		contentController.exibirPaginaInicial();
	}
	
	public boolean isUsuarioLogado() {
		return usuario != null;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
}
