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

import clinicas.dao.EstadoDAO;
import clinicas.model.Estado;

@Named
@ApplicationScoped
@FacesConverter(value = "estadoConverter")
public class EstadoConverter implements Converter {

	@Inject
	private EstadoDAO dao;
	
	private List<Estado> estados;
	
	@PostConstruct
	public void init() {
		estados = dao.listar();
	}
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (value != null && !"null".equals(value) && value.trim().length() > 0) {
			try {
				return estados.stream()
							  .filter(e -> Integer.parseInt(value) == e.getId())
							  .findFirst()
							  .get();
			} catch (NumberFormatException e) {
				throw new ConverterException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "EstadoConverter::Erro de Conversão", e.getMessage()));
			}
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object object) {
		if (object != null)
			return String.valueOf(((Estado) object).getId());
		else
			return null;
	}

}
