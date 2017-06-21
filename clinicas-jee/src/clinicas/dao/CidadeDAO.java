package clinicas.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import clinicas.model.Cidade;

@ApplicationScoped
public class CidadeDAO extends GenericDAO<Cidade> {

	public List<Cidade> listar() {
		TypedQuery<Cidade> q = em.createQuery(
			"select c from Cidade c "
		  + "left join fetch c.estado "
		  + "order by c.nome", Cidade.class);
		
		return q.getResultList();
	}

}
