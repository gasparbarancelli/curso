package br.com.viasoft.web.validator;

import org.apache.commons.lang.StringUtils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "cnpjValidator")
public class CnpjValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        try {
            validaCNPJ(String.valueOf(value));
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage("Validação de cnpj falhou.", "CNPJ inválido.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

    public static void validaCNPJ(String cnpj) throws Exception {
        if (!StringUtils.isEmpty(cnpj)) {
            cnpj = cnpj.replace(".", "").replace("/", "").replace("-", "");
            
            if (cnpj.equals("00000000000000") || cnpj.equals("11111111111111")
                    || cnpj.equals("22222222222222") || cnpj.equals("33333333333333")
                    || cnpj.equals("44444444444444") || cnpj.equals("55555555555555")
                    || cnpj.equals("66666666666666") || cnpj.equals("77777777777777")
                    || cnpj.equals("88888888888888") || cnpj.equals("99999999999999")
                    || (cnpj.length() != 14)) {
                throw new Exception();
            }

            Long.parseLong(cnpj);

            int soma = 0;
            String cnpj_calc = cnpj.substring(0, 12);

            char chr_cnpj[] = cnpj.toCharArray();
            for (int i = 0; i < 4; i++) {
                if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9) {
                    soma += (chr_cnpj[i] - 48) * (6 - (i + 1));
                }
            }

            for (int i = 0; i < 8; i++) {
                if (chr_cnpj[i + 4] - 48 >= 0 && chr_cnpj[i + 4] - 48 <= 9) {
                    soma += (chr_cnpj[i + 4] - 48) * (10 - (i + 1));
                }
            }

            int dig = 11 - soma % 11;
            cnpj_calc = (new StringBuilder(String.valueOf(cnpj_calc))).append(dig != 10 && dig != 11 ? Integer.toString(dig) : "0").toString();
            soma = 0;
            for (int i = 0; i < 5; i++) {
                if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9) {
                    soma += (chr_cnpj[i] - 48) * (7 - (i + 1));
                }
            }

            for (int i = 0; i < 8; i++) {
                if (chr_cnpj[i + 5] - 48 >= 0 && chr_cnpj[i + 5] - 48 <= 9) {
                    soma += (chr_cnpj[i + 5] - 48) * (10 - (i + 1));
                }
            }

            dig = 11 - soma % 11;
            cnpj_calc = (new StringBuilder(String.valueOf(cnpj_calc))).append(dig != 10 && dig != 11 ? Integer.toString(dig) : "0").toString();

            if (!cnpj.equals(cnpj_calc)) {
                throw new Exception();
            }
        }
    }
}
