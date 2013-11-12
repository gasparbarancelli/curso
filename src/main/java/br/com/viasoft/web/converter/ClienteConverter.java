package br.com.viasoft.web.converter;

import br.com.viasoft.model.entity.Cliente;
import br.com.viasoft.model.framework.ICrudService;
import br.com.viasoft.model.service.ClienteService;
import br.com.viasoft.web.framework.BaseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: Gaspar Barancelli
 * Date: 10/11/13
 * Time: 20:19
 * To change this template use File | Settings | File Templates.
 */
@Component
public class ClienteConverter extends BaseConverter<Cliente, Long> {

    @Autowired private ClienteService clienteService;

    @Override
    protected ICrudService<Cliente, Long> getService() {
        return clienteService;
    }

    @Override
    protected String getAsString(Cliente value) {
        return value.getId().toString();
    }

    @Override
    protected Long getAsObject(String value) {
        return new Long(value);
    }
}
