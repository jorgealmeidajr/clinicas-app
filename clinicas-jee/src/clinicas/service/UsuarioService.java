package clinicas.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import clinicas.model.Usuario;

@Named
@ApplicationScoped
public class UsuarioService {

	public Usuario verificarLogin(final String email, final String senha) {
		if (email == null || email.isEmpty()) {
			throw new IllegalArgumentException("O campo email é obrigatório.");
		}
		
		if (senha == null || senha.isEmpty()) {
			throw new IllegalArgumentException("O campo senha é obrigatório.");
		}
		
		Usuario usuario = null;

		try {
			//email = email.toLowerCase().trim();
//			Query q = em.createNamedQuery(Usuario.FIND_BY_EMAIL_SENHA);
//			q.setParameter("email", email);
//			q.setParameter("senha", stringParaMd5(senha));
//			List<Usuario> retorno = q.getResultList();
//
//			if (retorno.size() == 1) {
//				userFound = (Usuario) retorno.get(0);
//			}

			usuario = new Usuario();
			usuario.setNome("Jorge Almeida Junior");
			usuario.setEmail("jorge@gmail.com");
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return usuario;
	}
	
}
