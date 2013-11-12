package br.com.viasoft.web.framework;

import org.primefaces.context.RequestContext;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Esta classe prove metodos utilizados em um projeto JSF, existem metodos responsaveis
 * por recuperar os contextos de request e faces e tambem adicionar mensagens aos contextos.
 * Algumas funcoes muito utilizadas em todos controllers tambem sao providas por esta classe.
 * @author Gaspar
 */
public abstract class BaseController {

    private transient FacesContext facesContext;
    private transient RequestContext requestContext;

    public RequestContext getRequestContext() {
        if (this.requestContext != null) {
            return this.requestContext;
        } else {
            return RequestContext.getCurrentInstance();
        }
    }

    public FacesContext getFacesContext() {
        if (null != facesContext) {
            return facesContext;
        } else {
            return FacesContext.getCurrentInstance();
        }
    }

    public void setFacesContext(FacesContext facesContext) {
        this.facesContext = facesContext;
    }

    protected void addMessage(String message, Throwable e) {
        getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null == e ? message : e.getMessage()));
    }

    protected void addMessage(String message, FacesMessage.Severity Severity) {
        getFacesContext().addMessage(null, new FacesMessage(Severity, message, message));
    }

    protected void addMessage(String clienteId, String message, FacesMessage.Severity Severity) {
        getFacesContext().addMessage(clienteId, new FacesMessage(Severity, message, message));
    }

    protected void addMessage(FacesMessage message) {
        getFacesContext().addMessage(null, message);
    }

    protected void executeJS(String metodoJs){
        getRequestContext().execute(metodoJs);
    }

}
