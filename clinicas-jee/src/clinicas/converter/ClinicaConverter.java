package clinicas.converter;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import clinicas.dao.ClinicaDAO;
import clinicas.model.Clinica;

@Named
@ApplicationScoped
@FacesConverter(value = "clinicaConverter")
public class ClinicaConverter implements Converter {
	
	@Inject
	private ClinicaDAO dao;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (value != null && !"null".equals(value) && value.trim().length() > 0) {
			try {
				return dao.listar().stream()
							  	   .filter(e -> Integer.parseInt(value) == e.getId())
							  	   .findFirst()
							  	   .get();
			} catch (NumberFormatException e) {
				throw new ConverterException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ClinicaConverter::Erro de Conversão", e.getMessage()));
			}
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object object) {
		if (object != null)
			return String.valueOf(((Clinica) object).getId());
		else
			return null;
	}

}
