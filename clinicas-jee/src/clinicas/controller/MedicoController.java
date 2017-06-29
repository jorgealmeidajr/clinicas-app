package clinicas.controller;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import clinicas.dao.ClinicaDAO;
import clinicas.dao.EspecialidadeDAO;
import clinicas.dao.EstadoDAO;
import clinicas.model.Clinica;
import clinicas.model.Especialidade;
import clinicas.model.Estado;
import clinicas.model.Medico;
import clinicas.service.MedicoService;
import clinicas.utils.CNPUtils;

@Named
@ViewScoped
public class MedicoController implements Serializable {

	private static final long serialVersionUID = 2944813252524122584L;

	private Medico parametrosBusca;
	
	private String estadoCadastro; 
	private Medico entidadeCadastro;
	
	private List<Medico> medicos;
	
	private List<Estado> estados;
	private List<Especialidade> especialidades;
	
	@Inject
	private MedicoService service;
	
	@Inject
	private EstadoDAO estadoDao;
	@Inject
	private EspecialidadeDAO especialidadeDao;
	@Inject
	private ClinicaDAO clinicaDao;
	
	@PostConstruct
	public void init() {
		iniciarParametrosBusca();
		
		iniciarCadastro();
		
		medicos = service.listar();
		
		estados = estadoDao.listar();
		
		especialidades = especialidadeDao.listar();
	}

	private void iniciarParametrosBusca() {
		parametrosBusca = new Medico();
		parametrosBusca.setNome("");
		parametrosBusca.setCpf("");
		parametrosBusca.setEspecialidade(new Especialidade());
		parametrosBusca.setEstadoCrm(new Estado());
	}

	public void pesquisar() {
		if(parametrosBusca.getEspecialidade() == null)
			parametrosBusca.setEspecialidade(new Especialidade());
		
		if(parametrosBusca.getEstadoCrm() == null)
			parametrosBusca.setEstadoCrm(new Estado());
		
		medicos = service.listar(parametrosBusca);
	}
	
	public void limparBusca() {
		iniciarParametrosBusca();
		
		pesquisar();
	}
	
	public void iniciarCadastro() {
		entidadeCadastro = new Medico();
		entidadeCadastro.setEspecialidade(new Especialidade());
		entidadeCadastro.setEstadoCrm(new Estado());
	}
	
	public void iniciarEdicao(Medico medico) {
		entidadeCadastro = new Medico(medico);
		estadoCadastro = "";
	}
	
	public void salvar() {
		if (!verificarCadastro()) return;
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			service.salvar(entidadeCadastro);
			context.addMessage(null, new FacesMessage("Médico salvo com sucesso."));
			
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro no cadastro", e.getMessage()));
		}
		
		pesquisar();
	}
	
	private boolean verificarCadastro() {
		boolean retorno = true;
		FacesContext context = FacesContext.getCurrentInstance();
		
		if (entidadeCadastro.getNome() == null || entidadeCadastro.getNome().trim().isEmpty()) {
			retorno = false;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de validação", "O campo nome é obrigatório."));
		}
		
		if (entidadeCadastro.getEspecialidade() == null 
			|| entidadeCadastro.getEspecialidade().getId() == null) {
			retorno = false;
			context.addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de validação", "O campo Especialidade é obrigatório."));
		}
		
		if (entidadeCadastro.getClinica() == null 
			|| entidadeCadastro.getClinica().getId() == null) {
			retorno = false;
			context.addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de validação", "O campo Clínica é obrigatório."));
		}
		
		String cpf = entidadeCadastro.getCpf().replaceAll("[.-]", "");
		if(!"".equals(cpf.trim()) && !CNPUtils.isValidCPF(cpf)) {
			retorno = false;
			context.addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de validação", "O CPF informado é inválido."));
		}
		
		if(service.existeMedicoComCPF(entidadeCadastro.getCpf(), entidadeCadastro.getId())) {
			retorno = false;
			context.addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de validação", "O CPF informado já foi cadastrado."));
		}
		
		if (!retorno) {
			estadoCadastro = "erro_validacao";
		} else {
			estadoCadastro = "";
		}
		
		return retorno;
	}
	
	public List<Estado> completeEstados(String nomeEstado) {
		final String nome = nomeEstado.trim().toLowerCase();
		
		return estados.stream()
					  .filter(c -> c.getNome().toLowerCase().contains(nome))
					  .collect(Collectors.toList());
	}

	public void atualizar() {
		if (!verificarCadastro()) return;
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			service.atualizar(entidadeCadastro);
			context.addMessage(null, new FacesMessage("Médico atualizado com sucesso."));
			
			estadoCadastro = "";
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro no cadastro", e.getMessage()));
		}
		
		pesquisar();
	}
	
	public void excluir(Medico medico) {
		service.excluir(medico);
		
		pesquisar();
	}
	
	public void onRowEdit(RowEditEvent event) {
		Medico medico = (Medico) event.getObject();
		
		service.atualizar(medico);
	}
	
	public void onRowCancel(RowEditEvent event) {
		System.out.println("on row cancel");
	}
	
	public void onCellEdit(CellEditEvent event) {
		System.out.println("on cell edit");
	}
	
	public List<Especialidade> completeEspecialidades(String nomeEspecialidade) {
		final String nome = nomeEspecialidade.trim().toLowerCase();
		
		return especialidades.stream()
							 .filter(e -> e.getNome().toLowerCase().contains(nome))
							 .collect(Collectors.toList());
	}
	
	public List<Clinica> completeClinicas(String nomeClinica) {
		final String nome = nomeClinica.trim().toLowerCase();
		
		return clinicaDao.listar()
						 .stream()
						 .filter(e -> e.getRazaoSocial().toLowerCase().contains(nome))
						 .collect(Collectors.toList());
	}
	
	public void handleImagemUpload(FileUploadEvent event) {
		byte[] bytesImagem = event.getFile().getContents();
		entidadeCadastro.setFoto(bytesImagem);
	}
	
	public StreamedContent displayImage(byte[] image) {
		// esse metodo nao funcionou, TODO pesquisar o motivo...
		FacesContext context = FacesContext.getCurrentInstance();

		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		} else {
			if(image != null) 
				return new DefaultStreamedContent(new ByteArrayInputStream(image));
		}

		return new DefaultStreamedContent();
	}

	public Medico getEntidadeCadastro() {
		return entidadeCadastro;
	}

	public void setEntidadeCadastro(Medico entidadeCadastro) {
		this.entidadeCadastro = entidadeCadastro;
	}

	public Medico getParametrosBusca() {
		return parametrosBusca;
	}

	public List<Medico> getMedicos() {
		return medicos;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public List<Especialidade> getEspecialidades() {
		return especialidades;
	}

	public String getEstadoCadastro() {
		return estadoCadastro;
	}

	public void setEstadoCadastro(String estadoCadastro) {
		this.estadoCadastro = estadoCadastro;
	}
	
}
