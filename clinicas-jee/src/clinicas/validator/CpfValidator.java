package clinicas.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import clinicas.utils.CNPUtils;

@FacesValidator("cpfValidator")
public class CpfValidator implements Validator {

	@Override
	public void validate(FacesContext c, UIComponent ui, Object obj) throws ValidatorException {
		String cpf = (String) obj;
		
		if (cpf == null || "".equals(cpf)) {
			return; // paro a validacao
		}
		
		if (!CNPUtils.isValidCPF(cpf)) {
			FacesMessage msg = new FacesMessage("O CPF informado é inválido!", "Erro na validação do CPF.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			
			throw new ValidatorException(msg);
		}
	}

}
