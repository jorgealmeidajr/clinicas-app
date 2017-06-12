package clinicas.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import clinicas.model.Usuario;
import clinicas.service.UsuarioService;

@Named
@SessionScoped
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = 8666699850926627315L;

	private Usuario usuario;
	private String senha;
	
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
			if (senha == null || senha.trim().isEmpty()) {
				throw new IllegalArgumentException("O campo senha é obrigatório no cadastro de Usuário.");
			}
			
			usuario.setSenha(senha.trim());
			service.salvar(usuario);
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(e.getMessage()));
		}
	}

	public void editar() {
		usuario = loginController.getUsuario();
		senha = "";
		contentController.exibirCadastrarUsuario();
	}
	
	public void atualizar() {
		service.atualizar(usuario, senha);
	}
	
	public void handleImagemUpload(FileUploadEvent event) {		
		// UploadedFile imagem = event.getFile();
		byte[] bytesImagem = event.getFile().getContents();
		usuario.setFoto(bytesImagem);
		
		FacesMessage message = new FacesMessage("Sucesso",
			"Upload da imagem : '" + event.getFile().getFileName() + "' feita com sucesso.");
        FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public StreamedContent getFotoEnviada() throws IOException {
		return new DefaultStreamedContent(new ByteArrayInputStream(usuario.getFoto()));
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
