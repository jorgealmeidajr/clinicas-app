package clinicas.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import clinicas.model.Especialidade;

@ApplicationScoped
public class EspecialidadeDAO extends GenericDAO<Especialidade> {

	public List<Especialidade> listar() {
		TypedQuery<Especialidade> q = em.createQuery(
			"select e from Especialidade e order by e.nome", Especialidade.class);
		
		return q.getResultList();
	}
	
}
