package clinicas.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public abstract class GenericDAO<E> {

	@Inject
	protected EntityManager em;
	
	public void salvar(E entity) {
		em.persist(entity);
	}
	
	public void atualizar(E entity) {
		em.merge(entity);
	}
	
	public void remover(E entity) {
		E entityToRemove = em.merge(entity);
		em.remove(entityToRemove);
	}
	
}
