package br.com.viasoft.web.converter;

import br.com.viasoft.model.entity.Item;
import br.com.viasoft.model.framework.ICrudService;
import br.com.viasoft.model.service.ItemService;
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
public class ItemConverter extends BaseConverter<Item, Long> {

    @Autowired private ItemService itemService;

    @Override
    protected ICrudService<Item, Long> getService() {
        return itemService;
    }

    @Override
    protected String getAsString(Item value) {
        return value.getId().toString();
    }

    @Override
    protected Long getAsObject(String value) {
        return new Long(value);
    }
}
