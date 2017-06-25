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
		if (!verificarCadastro()) return;
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			
			usuario.setSenha(senha.trim());
			service.salvar(usuario);
			
			init();
			context.addMessage(null, new FacesMessage("Usuário criado com sucesso."));
			
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro no cadastro", e.getMessage()));
		}
	}

	private boolean verificarCadastro() {
		boolean retorno = true;
		FacesContext context = FacesContext.getCurrentInstance();
		
		if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
			retorno = false;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de validação", "O campo nome é obrigatório."));
		}
		
		if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
			retorno = false;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de validação", "O campo email é obrigatório."));
		}
		
		if (usuario.getId() == null && (senha == null || senha.trim().isEmpty())) {
			retorno = false;
			context.addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de validação", "O campo senha é obrigatório no cadastro de Usuário."));
		}
		
		return retorno;
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
