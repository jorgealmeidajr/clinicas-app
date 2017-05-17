package clinicas.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

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
	
	@PostConstruct
	public void init() {
		parametrosBusca = new Medico();
		medicos = new ArrayList<>();
		//TODO iniciar estados com o banco... service...
		//TODO iniciar especialidades com o banco... service...
	}
	
	public void pesquisar() {
		
	}
	
	public void limparBusca() {
		
	}
	
	public void iniciarCadastro() {
		
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
