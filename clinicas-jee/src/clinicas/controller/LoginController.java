package clinicas.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import clinicas.model.Usuario;

@Named
@SessionScoped
public class LoginController implements Serializable {

	private static final long serialVersionUID = 6443736038981020368L;

	private String email;
	private String senha;
	
	private Usuario usuarioLogado;
	
	public LoginController() {
		System.out.println(">>login_controller criado");
	}
	
	public void login() {
		
	}
	
	public void logout() {
		usuarioLogado = null;
	}
	
	public boolean isUsuarioLogado() {
		return usuarioLogado != null;
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
	
}
