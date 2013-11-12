package br.com.viasoft.web.controller;

import br.com.viasoft.model.entity.Item;
import br.com.viasoft.model.framework.ICrudService;
import br.com.viasoft.model.service.ItemService;
import br.com.viasoft.web.framework.CrudController;
import br.com.viasoft.web.util.JsfUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Created with IntelliJ IDEA.
 * User: Gaspar Barancelli
 * Date: 04/11/13
 * Time: 22:39
 * To change this template use File | Settings | File Templates.
 */
@Controller
@Scope("view")
public class ItemController extends CrudController<Item, Long> {

    @Autowired private ItemService itemService;

    @Override
    protected ICrudService<Item, Long> getService() {
        return itemService;
    }

    @Override
    protected String getUrlFormPage() {
        return "/pages/cadastros/itemForm.xhtml?faces-redirect=true";
    }

    @Override
    public void find() {
        lsEntity = itemService.findByNomeContaining(entity.getNome());
    }

}
