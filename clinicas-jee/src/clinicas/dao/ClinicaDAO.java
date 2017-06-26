package clinicas.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import clinicas.model.Clinica;

@ApplicationScoped
public class ClinicaDAO extends GenericDAO<Clinica> {

	public List<Clinica> listar() {
		TypedQuery<Clinica> q = em.createQuery(
			"select cli from Clinica cli "
		  + "left join fetch cli.endereco e "
		  + "left join fetch e.cidade cid "
		  + "left join fetch cid.estado " 
		  + "order by cli.razaoSocial", Clinica.class);

		return q.getResultList();
	}

}
