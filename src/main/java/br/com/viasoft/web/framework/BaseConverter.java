package br.com.viasoft.web.framework;

import br.com.viasoft.model.framework.ICrudService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.io.Serializable;

/**
 * User: gaspar
 * Date: 19/08/13
 * Time: 08:16
 * Esta classe prove metodos para utilizacao da interface converter do JSF, fazendo com que os metodos
 * a serem implementados sejam mais simples e sem precisar se preocupar com validacoes
 */
public abstract class BaseConverter<T, ID extends Serializable> implements Converter {

    private static final Logger logger = Logger.getLogger(BaseConverter.class.getName());

    /**
     * Caractere utilizado para separar chaves compostas
     */
    protected final String SEPARADOR = "=";

    /**
     * Metodo responsavel por obter o servico onde sera obtido o objeto selecionado pelo usuario
     * chamando o metodo findById desta interface
     * @return
     */
    protected abstract ICrudService<T, ID> getService();

    /**
     * Metodo responsavel por retornar a chave do objeto passado por parametro
     * @param value
     * @return
     */
    protected abstract String getAsString(T value);

    /**
     * Metodo responsavel por retornar o objeto conforme a chave do mesmo passada por parametro
     * @param value
     * @return
     */
    protected abstract ID getAsObject(String value);

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (StringUtils.isNotBlank(value) && !value.equals("null")) {
            try {
                return getService().findById(getAsObject(value));
            } catch (NumberFormatException e) {
                logger.info(e);
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return getAsString((T) value);
        }
    }
}
