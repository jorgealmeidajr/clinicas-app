package clinicas.service;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import clinicas.dao.UsuarioDAO;
import clinicas.model.Usuario;
import clinicas.utils.CriptografiaUtils;

@Named
@ApplicationScoped
public class UsuarioService {
	
	@Inject
	private UsuarioDAO dao;

	public Usuario verificarLogin(final String email, final String senha) {
		String senhaCriptografada = CriptografiaUtils.converterStringParaMD5(senha);
		
		Optional<Usuario> op = dao.findByEmailSenha(email, senhaCriptografada);
		
		if (!op.isPresent()) {
			throw new IllegalArgumentException("Nao foi encontrado um usuario com o email e a senha informados.");
		}
		
		return op.get();
	}
	
	@Transactional
	public void salvar(Usuario usuario) {
		String senhaCriptografada = CriptografiaUtils.converterStringParaMD5(usuario.getSenha());
		usuario.setSenha(senhaCriptografada);
		
		dao.salvar(usuario);
	}
	
	@Transactional
	public void atualizar(Usuario usuario, String novaSenha) {
		if (novaSenha != null && !novaSenha.trim().isEmpty()) {
			String senhaCriptografada = CriptografiaUtils.converterStringParaMD5(novaSenha);
			usuario.setSenha(senhaCriptografada);
		}
		
		dao.atualizar(usuario);
	}
	
}
