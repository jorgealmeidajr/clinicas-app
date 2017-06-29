package clinicas.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import clinicas.dao.CidadeDAO;
import clinicas.dao.EstadoDAO;
import clinicas.model.Cidade;
import clinicas.model.Clinica;
import clinicas.model.EnderecoClinica;
import clinicas.model.Estado;
import clinicas.service.ClinicaService;
import clinicas.utils.CNPUtils;

@Named
@ViewScoped
public class ClinicasController implements Serializable {

	private Clinica parametrosBusca;

	private String estadoCadastro;
	private Clinica entidadeCadastro;

	private List<Clinica> entidades;
	
	private Estado estado;
	private List<Estado> estados;
	
	private List<Cidade> cidades;
	private List<Cidade> cidadesCadastro;
	
	@Inject
	private ClinicaService service;
	
	@Inject
	private EstadoDAO estadoDAO;
	@Inject
	private CidadeDAO cidadeDAO;
	
	@PostConstruct
	public void init() {
		iniciarParametrosBusca();
		
		entidades = service.listar();
		
		estados = estadoDAO.listar();
		cidades = cidadeDAO.listar();
		cidadesCadastro = new ArrayList<>();
		
		estadoCadastro = "";
	}
	
	public void pesquisar() {
		if(parametrosBusca.getEndereco().getCidade() == null)
			parametrosBusca.getEndereco().setCidade(new Cidade());
		
		entidades = service.listar(parametrosBusca);
	}
	
	public void limparBusca() {
		iniciarParametrosBusca();
		pesquisar();
	}
	
	private void iniciarParametrosBusca() {
		parametrosBusca = new Clinica();
		parametrosBusca.setRazaoSocial("");
		parametrosBusca.setCnpj("");
		parametrosBusca.setEndereco(new EnderecoClinica());
		parametrosBusca.getEndereco().setCidade(new Cidade());
	}
	
	public List<Estado> completeEstados(String nomeEstado) {
		final String nome = nomeEstado.trim().toLowerCase();
		
		return estados.stream()
					  .filter(c -> c.getNome().toLowerCase().contains(nome))
					  .collect(Collectors.toList());
	}
	
	public List<Cidade> completeCidades(String nomeCidade) {
		final String nome = nomeCidade.trim().toLowerCase();
		
		return cidades.stream()
					  .filter(c -> c.getNome().toLowerCase().contains(nome))
					  .collect(Collectors.toList());
	}
	
	public List<Cidade> completeCidadesCadastro(String nomeCidade) {
		final String nome = nomeCidade.trim().toLowerCase();
		
		return cidadesCadastro.stream()
					  		  .filter(c -> c.getNome().toLowerCase().contains(nome))
					  		  .collect(Collectors.toList());
	}
	
	public void estadoSelecionado(SelectEvent event) {
		cidadesCadastro = cidades.stream()
				  				 .filter(c -> c.getEstado().getId().equals(estado.getId()))
				  				 .collect(Collectors.toList());
	}
	
	public void iniciarCadastro() {
		entidadeCadastro = new Clinica();
		entidadeCadastro.setEndereco(new EnderecoClinica());
		entidadeCadastro.setAtendeSus(false);
		estado = null;
	}
	
	public void iniciarEdicao(Clinica clinica) {
		entidadeCadastro = new Clinica(clinica);
		
		if (clinica.getEndereco().getCidade() != null) {
			estado = clinica.getEndereco().getCidade().getEstado();
			
			cidadesCadastro = cidades.stream()
	 				 				 .filter(c -> c.getEstado().getId().equals(estado.getId()))
	 				 				 .collect(Collectors.toList());
		} else {
			estado = null;
		}
	}
	
	public void salvar() {
		if (!verificarCadastro()) 
			return;
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			service.salvar(entidadeCadastro);
			context.addMessage(null, new FacesMessage("Clínica salva com sucesso."));
			
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro no cadastro", e.getMessage()));
		}
		
		pesquisar();
	}
	
	public void atualizar() {
		if (!verificarCadastro()) 
			return;
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			service.atualizar(entidadeCadastro);
			context.addMessage(null, new FacesMessage("Clínica atualizada com sucesso."));
			
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro no cadastro", e.getMessage()));
		}
		
		pesquisar();
	}
	
	private boolean verificarCadastro() {
		boolean retorno = true;
		FacesContext context = FacesContext.getCurrentInstance();
		
		if (entidadeCadastro.getRazaoSocial() == null || entidadeCadastro.getRazaoSocial().trim().isEmpty()) {
			retorno = false;
			context.addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de validação", "O campo Razão Social é obrigatório."));
		}
		
//		if (entidadeCadastro.getEspecialidade() == null 
//			|| entidadeCadastro.getEspecialidade().getId() == null) {
//			retorno = false;
//			context.addMessage(null, 
//				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de validação", "O campo Especialidade é obrigatório."));
//		}
		
		String cnpj = entidadeCadastro.getCnpj().replaceAll("[.-/-]", "");
		
		if(!"".equals(cnpj.trim()) && !CNPUtils.isValidCNPJ(cnpj)) {
			retorno = false;
			context.addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de validação", "O CNPJ informado é inválido."));
		}
		
		if(service.existeClinicaComCNPJ(entidadeCadastro.getCnpj(), entidadeCadastro.getId())) {
			retorno = false;
			context.addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de validação", "O CNPJ informado já foi cadastrado."));
		}
		
		if (!retorno) {
			estadoCadastro = "erro_validacao";
		} else {
			estadoCadastro = "";
		}
		
		return retorno;
	}

	public void excluir(Clinica clinica) {
		service.excluir(clinica);
		
		pesquisar();
	}
	
	public void onRowEdit(RowEditEvent event) {
		Clinica clinica = (Clinica) event.getObject();
		service.atualizar(clinica);
	}
	
	public void onRowCancel(RowEditEvent event) { }

	public String getEstadoCadastro() {
		return estadoCadastro;
	}

	public void setEstadoCadastro(String estadoCadastro) {
		this.estadoCadastro = estadoCadastro;
	}

	public Clinica getParametrosBusca() {
		return parametrosBusca;
	}

	public Clinica getEntidadeCadastro() {
		return entidadeCadastro;
	}

	public void setEntidadeCadastro(Clinica entidadeCadastro) {
		this.entidadeCadastro = entidadeCadastro;
	}

	public List<Clinica> getEntidades() {
		return entidades;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	private static final long serialVersionUID = 8239463511332207528L;

}
