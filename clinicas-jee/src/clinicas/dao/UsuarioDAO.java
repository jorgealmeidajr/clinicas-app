package clinicas.dao;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import clinicas.model.Usuario;

@ApplicationScoped
public class UsuarioDAO extends GenericDAO<Usuario> {

	public Optional<Usuario> findByEmailSenha(String email, String senha) {
		Usuario retorno = null;
		
		TypedQuery<Usuario> q = em.createQuery(
			"select u from Usuario u where u.email = :email and u.senha = :senha", Usuario.class);
		q.setParameter("email", email);
		q.setParameter("senha", senha);
		
		try {
			retorno = q.getSingleResult();
		} catch (Exception e) { }
		
		return Optional.ofNullable(retorno);
	}
	
	public Optional<Usuario> findByEmail(String email) {
		Usuario retorno = null;
		
		TypedQuery<Usuario> q = em.createQuery(
			"select u from Usuario u where u.email = :email", Usuario.class);
		q.setParameter("email", email);

		try {
			retorno = q.getSingleResult();
		} catch (Exception e) { }
		
		return Optional.ofNullable(retorno);
	}
	
}
