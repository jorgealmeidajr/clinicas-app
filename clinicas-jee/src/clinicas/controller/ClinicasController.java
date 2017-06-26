package clinicas.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import clinicas.dao.ClinicaDAO;
import clinicas.model.Clinica;

@Named
@ViewScoped
public class ClinicasController implements Serializable {

	private Clinica parametrosBusca;

	private String estadoCadastro;
	private Clinica entidadeCadastro;

	private List<Clinica> entidades;
	
	@Inject
	private ClinicaDAO dao;
	
	@PostConstruct
	public void init() {
		entidades = dao.listar();
	}
	
	public void pesquisar() {
		
	}
	
	public void limparBusca() {
		
	}
	
	public void iniciarCadastro() {
		
	}
	
	public void excluir(Clinica clinica) {
		
	}
	
	public void onRowEdit() {
		
	}
	
	public void onRowCancel() {
		
	}

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

	public List<Clinica> getEntidades() {
		return entidades;
	}

	private static final long serialVersionUID = 8239463511332207528L;

}
