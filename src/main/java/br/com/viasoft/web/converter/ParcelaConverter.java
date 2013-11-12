package br.com.viasoft.web.converter;

import br.com.viasoft.model.entity.Parcela;
import br.com.viasoft.model.framework.ICrudService;
import br.com.viasoft.model.service.ParcelaService;
import br.com.viasoft.web.framework.BaseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: Gaspar Barancelli
 * Date: 10/11/13
 * Time: 20:16
 * To change this template use File | Settings | File Templates.
 */
@Component
public class ParcelaConverter extends BaseConverter<Parcela, Long> {

    @Autowired private ParcelaService parcelaService;

    @Override
    protected ICrudService<Parcela, Long> getService() {
        return parcelaService;
    }

    @Override
    protected String getAsString(Parcela value) {
        return value.getId().toString();
    }

    @Override
    protected Long getAsObject(String value) {
        return new Long(value);
    }
}
