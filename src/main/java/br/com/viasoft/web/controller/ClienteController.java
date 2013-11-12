package br.com.viasoft.web.controller;

import br.com.viasoft.model.entity.Cliente;
import br.com.viasoft.model.enumeration.SimNao;
import br.com.viasoft.model.framework.ICrudService;
import br.com.viasoft.model.service.ClienteService;
import br.com.viasoft.web.framework.CrudController;
import br.com.viasoft.web.util.EnumUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.faces.model.SelectItem;

/**
 * Created with IntelliJ IDEA.
 * User: Gaspar Barancelli
 * Date: 06/11/13
 * Time: 22:02
 * To change this template use File | Settings | File Templates.
 */
@Controller
@Scope("view")
public class ClienteController extends CrudController<Cliente, Long> {

    @Autowired private ClienteService clienteService;

    private SelectItem[] opcoesSimNao;

    @Override
    protected void inicializar() {
        opcoesSimNao = EnumUtil.populaSelectItem(SimNao.values());
    }

    @Override
    protected ICrudService<Cliente, Long> getService() {
        return clienteService;
    }

    @Override
    protected String getUrlFormPage() {
        return "/pages/cadastros/clienteForm.xhtml?faces-redirect=true";
    }

    @Override
    public void find() {
        lsEntity = clienteService.findByNomeContaining(entity.getNome());
    }

    public SelectItem[] getOpcoesSimNao() {
        return opcoesSimNao;
    }
}
