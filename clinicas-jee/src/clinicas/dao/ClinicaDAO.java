package clinicas.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import clinicas.model.Clinica;

@ApplicationScoped
public class ClinicaDAO extends GenericDAO<Clinica> {

	public List<Clinica> listar() {
		TypedQuery<Clinica> q = em.createQuery(
			"select c from Clinica c " 
		  + "order by c.razaoSocial", Clinica.class);

		return q.getResultList();
	}

}
