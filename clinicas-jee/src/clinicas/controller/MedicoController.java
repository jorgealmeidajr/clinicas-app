package clinicas.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import clinicas.dao.EspecialidadeDAO;
import clinicas.dao.EstadoDAO;
import clinicas.model.Especialidade;
import clinicas.model.Estado;
import clinicas.model.Medico;
import clinicas.service.MedicoService;

@Named
@ViewScoped
public class MedicoController implements Serializable {

	private static final long serialVersionUID = 2944813252524122584L;

	private Medico parametrosBusca;
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
	
	@PostConstruct
	public void init() {
		iniciarParametrosBusca();
		
		iniciarCadastro();
		
		medicos = service.listar();
		
		iniciarEstados();
		
		estados.addAll(estadoDao.listar());
		
		iniciarEspecialidades();
		
		especialidades.addAll(especialidadeDao.listar());
	}

	private void iniciarParametrosBusca() {
		parametrosBusca = new Medico();
		parametrosBusca.setNome("");
		parametrosBusca.setCpf("");
		parametrosBusca.setEspecialidade(new Especialidade());
		parametrosBusca.setEstadoCrm(new Estado());
	}

	private void iniciarEspecialidades() {
		especialidades = new ArrayList<>();
		
		Especialidade e = new Especialidade();
		e.setNome("Selecione");
		e.setId(-1);
		
		especialidades.add(e);
	}

	private void iniciarEstados() {
		estados = new ArrayList<>();
		
		Estado e = new Estado();
		e.setNome("Selecione");
		e.setId(-1);
		
		estados.add(e);
	}
	
	public void pesquisar() {
		if(parametrosBusca.getEspecialidade() == null)
			parametrosBusca.setEspecialidade(new Especialidade());
		
		if(parametrosBusca.getEstadoCrm().getId() != null && parametrosBusca.getEstadoCrm().getId() == -1)
			parametrosBusca.getEstadoCrm().setId(null);
		
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
	
	public void salvar() {
		service.salvar(entidadeCadastro);
		
		pesquisar();
	}
	
	public void atualizar() {
		service.atualizar(entidadeCadastro);
		
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
	
}
