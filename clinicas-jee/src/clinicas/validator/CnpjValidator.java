package clinicas.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import clinicas.utils.CNPUtils;

@FacesValidator("cnpjValidator")
public class CnpjValidator implements Validator {

	@Override
	public void validate(FacesContext c, UIComponent ui, Object obj) throws ValidatorException {
		String cnpj = (String) obj;
		
		if (cnpj == null || "".equals(cnpj)) {
			return; // paro a validacao
		}
		
		if (!CNPUtils.isValidCNPJ(cnpj)) {
			FacesMessage msg = new FacesMessage("O CNPJ informado é inválido!", "Erro na validação do CNPJ.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			
			throw new ValidatorException(msg);
		}
	}

}
