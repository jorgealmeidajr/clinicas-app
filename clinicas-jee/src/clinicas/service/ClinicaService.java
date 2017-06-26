package clinicas.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import clinicas.dao.ClinicaDAO;

@ApplicationScoped
public class ClinicaService {

	@Inject
	private ClinicaDAO dao;
	
}
