package clinicas.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import clinicas.dao.CidadeDAO;
import clinicas.dao.EspecialidadeDAO;
import clinicas.model.Cidade;
import clinicas.model.Especialidade;
import clinicas.model.Medico;
import clinicas.service.MedicoService;

@Named
@ViewScoped
public class BuscarMedicosController implements Serializable {

	private static final long serialVersionUID = 9203127584641946355L;
	
	private String nome;
	
	private Integer idEspecialidade;
	private List<Especialidade> especialidades;
	
	private Integer idCidade;
	private List<Cidade> cidades;
	
	private List<Medico> medicos;
	
	@Inject
	private EspecialidadeDAO especialidadeDAO;
	
	@Inject
	private CidadeDAO cidadeDAO;
	
	@Inject
	private MedicoService medicoService;
	
	public BuscarMedicosController() {
		
	}
	
	@PostConstruct
	public void init() {
		medicos = new ArrayList<>();
		
		especialidades = new ArrayList<>();
		especialidades.addAll(especialidadeDAO.listar());
		
		cidades = new ArrayList<>();
		cidades.addAll(cidadeDAO.listar());
	}
	
	public void buscarMedicos() {
		medicos = medicoService.listar(nome, idEspecialidade, idCidade);
	}
	
	public List<Especialidade> completeEspecialidades(String nomeEspecialidade) {
		final String nome = nomeEspecialidade.trim().toLowerCase();
		
		return especialidades.stream()
							 .filter(e -> e.getNome().toLowerCase().contains(nome))
							 .collect(Collectors.toList());
	}
	
	public void especilidadeSelecionada(SelectEvent event) {
        System.out.println(idEspecialidade);
        
        medicos = medicoService.listar(nome, idEspecialidade, idCidade);
    }
	
	public void campoEspecialidadeMudou(AjaxBehaviorEvent ev) {
		medicos = medicoService.listar(nome, idEspecialidade, idCidade);
	}
	
	public List<Cidade> completeCidades(String nomeCidade) {
		final String nome = nomeCidade.trim().toLowerCase();
		
		return cidades.stream()
					  .filter(c -> c.getNome().toLowerCase().contains(nome))
					  .collect(Collectors.toList());
	}
	
	public void cidadeSelecionada(SelectEvent event) {
        System.out.println("Cidade: " + idCidade);
        
        medicos = medicoService.listar(nome, idEspecialidade, idCidade);
    }
	
	public List<Especialidade> getEspecialidades() {
		return especialidades;
	}

	public List<Medico> getMedicos() {
		return medicos;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdEspecialidade() {
		return idEspecialidade;
	}

	public void setIdEspecialidade(Integer idEspecialidade) {
		this.idEspecialidade = idEspecialidade;
	}
	
	public List<Cidade> getCidades() {
		return cidades;
	}

	public Integer getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(Integer idCidade) {
		this.idCidade = idCidade;
	}

}
