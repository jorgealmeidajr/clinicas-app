package clinicas.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import clinicas.model.Usuario;

@Named
@SessionScoped
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = 8666699850926627315L;

	private Usuario usuario;
	
	@PostConstruct
	public void init() {
		usuario = new Usuario();
	}

	public void salvar() {

	}

	public void editar() {

	}

	public Usuario getUsuario() {
		return usuario;
	}

}
