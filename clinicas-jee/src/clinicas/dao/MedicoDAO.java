package clinicas.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import clinicas.model.Medico;

@ApplicationScoped
public class MedicoDAO extends GenericDAO<Medico> {

	public List<Medico> listar() {
		TypedQuery<Medico> q = em.createQuery(
			  "select m "
			+ "from Medico m "
			+ "order by m.nome", Medico.class);
		
		return q.getResultList();
	}
	
}
