package clinicas.service;

import java.util.List;

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
	
	@Transactional
	public void salvar(Medico medico) {
		dao.salvar(medico);
	}
	
}
