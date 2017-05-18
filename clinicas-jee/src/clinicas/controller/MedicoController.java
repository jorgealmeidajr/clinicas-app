package clinicas.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import clinicas.dao.EspecialidadeDAO;
import clinicas.dao.EstadoDAO;
import clinicas.model.Especialidade;
import clinicas.model.Estado;
import clinicas.model.Medico;

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
	private EstadoDAO estadoDao;
	@Inject
	private EspecialidadeDAO especialidadeDao;
	
	@PostConstruct
	public void init() {
		iniciarParametrosBusca();
		
		medicos = new ArrayList<>();
		
		iniciarEstados();
		
		estados.addAll(estadoDao.listar());
		
		iniciarEspecialidades();
		
		especialidades.addAll(especialidadeDao.listar());
	}

	private void iniciarParametrosBusca() {
		parametrosBusca = new Medico();
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
		
	}
	
	public void limparBusca() {
		iniciarParametrosBusca();
	}
	
	public void iniciarCadastro() {
		entidadeCadastro = new Medico();
		entidadeCadastro.setNome("teste...");
		entidadeCadastro.setEspecialidade(new Especialidade());
		entidadeCadastro.setEstadoCrm(new Estado());
	}
	
	public void salvar() {
		
		
	}
	
	public void atualizar() {
		
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
