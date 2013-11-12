package br.com.viasoft.web.controller;

import br.com.viasoft.model.entity.Parcela;
import br.com.viasoft.model.enumeration.SimNao;
import br.com.viasoft.model.framework.ICrudService;
import br.com.viasoft.model.service.ParcelaService;
import br.com.viasoft.web.framework.CrudController;
import br.com.viasoft.web.util.EnumUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.faces.model.SelectItem;

/**
 * Created with IntelliJ IDEA.
 * User: Gaspar Barancelli
 * Date: 10/11/13
 * Time: 19:11
 * To change this template use File | Settings | File Templates.
 */
@Controller
@Scope("view")
public class ParcelaController extends CrudController<Parcela, Long> {

    @Autowired private ParcelaService parcelaService;

    private SelectItem[] opcoesSimNao;

    @Override
    protected void inicializar() {
        opcoesSimNao = EnumUtil.populaSelectItem(SimNao.values());
    }

    @Override
    protected ICrudService<Parcela, Long> getService() {
        return parcelaService;
    }

    @Override
    protected String getUrlFormPage() {
        return "/pages/cadastros/parcelaForm.xhtml?faces-redirect=true";
    }

    @Override
    public void find() {
        lsEntity = parcelaService.findByDescricaoContaining(entity.getDescricao());
    }

    public SelectItem[] getOpcoesSimNao() {
        return opcoesSimNao;
    }
}
