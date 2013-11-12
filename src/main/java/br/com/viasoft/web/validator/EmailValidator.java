package br.com.viasoft.web.validator;

import org.apache.commons.lang.StringUtils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@FacesValidator(value="emailValidator")
public class EmailValidator implements Validator {

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        try {
            validaEmail(String.valueOf(value));
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage("Validação de e-mail falhou.", "Formato de e-mail inválido.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

    public static void validaEmail(String value) throws Exception{
        if (StringUtils.isNotBlank(value)) {
            String[] emails = value.split(";");
            for (String email : emails) {
                Matcher matcher = pattern.matcher(email);
                if (!matcher.matches()) {
                    throw new Exception();
                }
            }
        }
    }
}
