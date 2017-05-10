package clinicas.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class ContentController implements Serializable {

	private static final long serialVersionUID = -2695167817323691842L;

	private static final String PAGINA_INICIAL = "buscaMedicos.xhtml";
	
	private String paginaAtual;

	@PostConstruct
	public void init() {
		paginaAtual = PAGINA_INICIAL;
	}

	public String getPaginaAtual() {
		return paginaAtual;
	}

}
