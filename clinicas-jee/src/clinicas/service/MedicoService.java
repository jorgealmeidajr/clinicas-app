package clinicas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import clinicas.dao.MedicoDAO;
import clinicas.model.Medico;

@ApplicationScoped
public class MedicoService {

	@Inject
	private MedicoDAO dao;
	
	public List<Medico> listar() {
		return dao.listar();
	}
	
	public List<Medico> listar(Medico parametrosBusca) {
		return dao.listar()
				  .stream()
				  .filter(filtrar(parametrosBusca))
				  .collect(Collectors.toList());
	}
	
	private Predicate<Medico> filtrar(final Medico parametrosBusca) {
		List<Predicate<Medico>> predicates = new ArrayList<>();

		if (!"".equals(parametrosBusca.getNome().trim())) {
			predicates.add(m -> m.getNome().toLowerCase().contains(parametrosBusca.getNome().trim().toLowerCase()));
		}
		
		if(parametrosBusca.getEspecialidade().getId() != null) {
			predicates.add(m -> m.getEspecialidade().getId().equals(parametrosBusca.getEspecialidade().getId()));
		}
		
		if (!"".equals(parametrosBusca.getCpf().trim())) {
			predicates.add(m -> m.getCpf().contains(parametrosBusca.getCpf().trim()));
		}
		
		if(parametrosBusca.getEstadoCrm().getId() != null) {
			predicates.add(m -> m.getEstadoCrm().getId().equals(parametrosBusca.getEstadoCrm().getId()));
		}
	        
		return predicates.stream().reduce(w -> true, Predicate::and);
	}
	
	public List<Medico> listar(String nome, Integer idEspecialidade, Integer idCidade) {
		if ("".equals(nome) && idEspecialidade == null && idCidade == null) {
			return new ArrayList<>();
		}
		
		return dao.listar()
				  .stream()
				  .filter(filtrar(nome, idEspecialidade, idCidade))
				  .collect(Collectors.toList());
	}
	
	private Predicate<Medico> filtrar(final String nome, final Integer idEspecialidade, final Integer idCidade) {
		List<Predicate<Medico>> predicates = new ArrayList<>();

		if (!"".equals(nome.trim())) {
			predicates.add(m -> m.getNome().toLowerCase().contains(nome.trim().toLowerCase()));
		}

		if(idEspecialidade != null) {
			predicates.add(m -> m.getEspecialidade().getId().equals(idEspecialidade));
		}
				
//		TODO if(parametrosBusca.getEstadoCrm().getId() != null) {
//			predicates.add(m -> m.getEstadoCrm().getId().equals(parametrosBusca.getEstadoCrm().getId()));
//		}
	        
		return predicates.stream().reduce(w -> true, Predicate::and);
	}
	
	@Transactional
	public void salvar(Medico medico) {
		dao.salvar(medico);
	}
	
	@Transactional
	public void atualizar(Medico medico) {
		dao.atualizar(medico);
	}
	
	@Transactional
	public void excluir(Medico medico) {
		dao.remover(medico);
	}
	
}
