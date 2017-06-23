package clinicas.converter;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import clinicas.dao.CidadeDAO;
import clinicas.model.Cidade;

@Named
@ApplicationScoped
@FacesConverter(value = "cidadeConverter")
public class CidadeConverter implements Converter {
	
	@Inject
	private CidadeDAO dao;
	
	private List<Cidade> cidades;
	
	@PostConstruct
	public void init() {
		cidades = dao.listar();
	}

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (value != null && !"null".equals(value) && value.trim().length() > 0) {
			try {
				return cidades.stream()
							  .filter(e -> Integer.parseInt(value) == e.getId())
							  .findFirst()
							  .get();
			} catch (NumberFormatException e) {
				throw new ConverterException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "CidadeConverter::Erro de Conversão", e.getMessage()));
			}
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object object) {
		if (object != null)
			return String.valueOf(((Cidade) object).getId());
		else
			return null;
	}

}
