package clinicas.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import clinicas.model.Estado;

@ApplicationScoped
public class EstadoDAO extends GenericDAO<Estado> {

	public List<Estado> listar() {
		TypedQuery<Estado> q = em.createQuery(
			"select e from Estado e order by e.nome", Estado.class);
		
		return q.getResultList();
	}
	
}
