package clinicas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import clinicas.dao.ClinicaDAO;
import clinicas.model.Clinica;

@ApplicationScoped
public class ClinicaService {

	@Inject
	private ClinicaDAO dao;
	
	public List<Clinica> listar() {
		return dao.listar();
	}
	
	public List<Clinica> listar(Clinica parametrosBusca) {
		return dao.listar()
				  .stream()
				  .filter(filtrar(parametrosBusca))
				  .collect(Collectors.toList());
	}

	private Predicate<? super Clinica> filtrar(Clinica parametrosBusca) {
		List<Predicate<Clinica>> predicates = new ArrayList<>();

		if (!"".equals(parametrosBusca.getRazaoSocial().trim())) {
			predicates.add(m -> m.getRazaoSocial().toLowerCase().contains(
				parametrosBusca.getRazaoSocial().trim().toLowerCase()));
		}

		if (!"".equals(parametrosBusca.getCnpj().trim())) {
			predicates.add(m -> {
				if(m.getCnpj() == null) return false;
				return m.getCnpj().contains(parametrosBusca.getCnpj().trim());
			});
		}

		if (parametrosBusca.getEndereco().getCidade().getId() != null) {
			predicates.add((c) -> {
				if (c.getEndereco().getCidade() != null) {
					return c.getEndereco().getCidade().getId().equals(parametrosBusca.getEndereco().getCidade().getId()); 
				}
				return false;
			});
		}

		return predicates.stream().reduce(w -> true, Predicate::and);
	}
	
	public boolean existeClinicaComCNPJ(String cnpj, Integer idClinica) {
		return dao.listar()
				  .stream()
				  .filter((cli) -> {
					if (cli.getCnpj() != null && !"".equals(cli.getCnpj())) {
						return cli.getCnpj().equals(cnpj) && cli.getId() != idClinica;
					}
					return false;
				  })
				  .count() > 0;
	}
	
	@Transactional
	public void salvar(Clinica clinica) {
		dao.salvar(clinica);
	}
	
	@Transactional
	public void atualizar(Clinica clinica) {
		dao.atualizar(clinica);
	}
	
	@Transactional
	public void excluir(Clinica clinica) {
		dao.remover(clinica);
	}
	
}
