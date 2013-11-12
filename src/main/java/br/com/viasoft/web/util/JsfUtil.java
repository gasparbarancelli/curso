/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.viasoft.web.util;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * @author Gaspar
 */
public class JsfUtil {
    
    public static void clearSession() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        if(session != null){
            session.invalidate();
        }
        facesContext.getExternalContext().getSession(true);
    }
    
    public static HttpSession getSession() {
         FacesContext contexto = FacesContext.getCurrentInstance();
         return (HttpSession) contexto.getExternalContext().getSession(true);
    }
    
    public static void setAttributeSession(final String name, final Object object) {
        HttpSession session = getSession();
        session.setAttribute(name, object);
    }
    
    public static String getRequestParameter(final String parameter) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(parameter);
    }
    
    public static Object getAttributeSession(final String name) {
        try {
            HttpSession session = getSession();
            return session.getAttribute(name);
        } catch (Exception ex) {
            return null;
        }
    }
    
    public static void removeAttributeSession(final String name) {
        HttpSession session = getSession();
        session.removeAttribute(name);
    }
    
    public static void addMessage(String message, FacesMessage.Severity Severity) {
        FacesContext.getCurrentInstance().addMessage("controller", new FacesMessage(Severity, message, message));
    }
    
    public static ELContext getElContext() {
        return FacesContext.getCurrentInstance().getELContext();
    }
    
    public static ExpressionFactory getExpressionFactory() {
        return FacesContext.getCurrentInstance().getApplication().getExpressionFactory();
    }
    
    public static String getParameter(String parameter) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(parameter);
    }
    
    public static String getRequestURL() {
        return ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRequestURL().toString();
    }
    
    public static String getRealPath() {
        return ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).getServletContext().getRealPath("/");
    }
    
    public static String getContextPath() {
        return ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).getServletContext().getContextPath();
    }
    
    public static String getURL() {
        final String context = getContextPath();
        
        String url = JsfUtil.getRequestURL();
        return url.substring(0, url.indexOf(context)) + context + "/";
    }
    
    public static void redirect(String page) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(page);
        } catch (IOException e) {
            
        }
    }

    public static Object getFlashParameter(String name){
        return FacesContext.getCurrentInstance().getExternalContext().getFlash().get(name);
    }

    public static void setFlashParameter(String name, Object value){
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put(name, value);
    }

}
