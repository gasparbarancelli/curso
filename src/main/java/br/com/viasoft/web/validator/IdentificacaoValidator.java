package br.com.viasoft.web.validator;

import org.apache.commons.lang.StringUtils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "identificacaoValidator")
public class IdentificacaoValidator implements Validator {
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        try {
            if (StringUtils.isNotBlank(String.valueOf(value))) {
                String valor = String.valueOf(value).trim();
                if (valor.length() == 11) {
                    CpfValidator.validaCPF(valor);
                } else if (valor.length() == 14) {
                    CnpjValidator.validaCNPJ(valor);
                } else {
                    throw new Exception();
                }
            }
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage("Validação de CPF/CNPJ falhou.", "CPF/CNPJ inválida.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
    
}
