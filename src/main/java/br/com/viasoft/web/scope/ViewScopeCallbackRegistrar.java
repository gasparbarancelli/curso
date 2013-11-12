package br.com.viasoft.web.scope;

import javax.faces.component.UIViewRoot;
import javax.faces.event.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: gasparbarancelli
 * Date: 10/04/13
 * Time: 13:38
 * To change this template use File | Settings | File Templates.
 */
public class ViewScopeCallbackRegistrar implements ViewMapListener {

    public void processEvent(SystemEvent event) throws AbortProcessingException {
        if(event instanceof PostConstructViewMapEvent) {
            PostConstructViewMapEvent viewMapEvent = (PostConstructViewMapEvent)event;
            UIViewRoot viewRoot = (UIViewRoot)viewMapEvent.getComponent();
            viewRoot.getViewMap().put(ViewScope.VIEW_SCOPE_CALLBACKS,new HashMap<String,Runnable>());
        } else if(event instanceof PreDestroyViewMapEvent) {
            PreDestroyViewMapEvent viewMapEvent = (PreDestroyViewMapEvent)event;
            UIViewRoot viewRoot = (UIViewRoot)viewMapEvent.getComponent();
            Map<String,Runnable> callbacks = (Map<String, Runnable>) viewRoot.getViewMap().get(ViewScope.VIEW_SCOPE_CALLBACKS);
            if(callbacks != null) {
                for(Runnable c:callbacks.values()) {
                    c.run();
                }
                callbacks.clear();
            }
        }
    }

    public boolean isListenerForSource(Object source) {
        return source instanceof UIViewRoot;
    }
}