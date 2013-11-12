package br.com.viasoft.web.controller;

import br.com.viasoft.model.entity.*;
import br.com.viasoft.model.framework.ICrudService;
import br.com.viasoft.model.service.ClienteService;
import br.com.viasoft.model.service.ItemService;
import br.com.viasoft.model.service.ParcelaService;
import br.com.viasoft.model.service.PedidoService;
import br.com.viasoft.web.framework.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Gaspar Barancelli
 * Date: 10/11/13
 * Time: 19:39
 * To change this template use File | Settings | File Templates.
 */
@Controller
@Scope("view")
public class PedidoController extends CrudController<Pedido, Long> {

    @Autowired private PedidoService pedidoService;

    @Autowired private ClienteService clienteService;

    @Autowired private ParcelaService parcelaService;

    @Autowired private ItemService itemService;

    private BigDecimal valorTotal = BigDecimal.ZERO;

    private Item item;

    @Override
    protected ICrudService<Pedido, Long> getService() {
        return pedidoService;
    }

    @Override
    protected String getUrlFormPage() {
        return "/pages/pedido/pedidoForm.xhtml?faces-redirect=true";
    }

    @Override
    public void reset() {
        super.reset();
        item = null;
        valorTotal = BigDecimal.ZERO;
    }

    @Override
    public void setEntityView(Pedido entityView) {
        super.setEntityView(pedidoService.findById(entityView.getId()));
    }

    @Override
    public void find() {
        if (entity.getCliente() != null)
            lsEntity = pedidoService.findByCliente(entity.getCliente());
        else
            super.find();
    }

    @Override
    protected void inicializar() {
        if (entity.getId() != null) {
            entity = pedidoService.findById(entity.getId());

            for (PedidoItem pedidoItem : entity.getItens())
                valorTotal = valorTotal.add(pedidoItem.getQuantidade().multiply(pedidoItem.getValor()));

            changeDescontoPercentual();
        }
    }

    public List<Cliente> completeCliente(String nome) {
        return clienteService.completeOnlyAtivos(nome);
    }

    public List<Parcela> completeParcela(String descricao) {
        return parcelaService.completeOnlyAtivos(descricao);
    }

    public List<Item> completeItem(String nome) {
        return itemService.complete(nome);
    }

    public void adicionarItem() {
        PedidoItem pedidoItem = new PedidoItem();
        pedidoItem.setPedido(entity);
        pedidoItem.setItem(item);
        pedidoItem.setQuantidade(BigDecimal.ONE);
        pedidoItem.setValor(item.getPreco());

        entity.getItens().add(pedidoItem);
        valorTotal = entity.getValorTotal().add(item.getPreco());
        ajustarValores();

        item = null;
    }

    private void ajustarValores() {
        entity.setDescontoPercentual(BigDecimal.ZERO);
        entity.setDescontoTotal(BigDecimal.ZERO);
        entity.setValorTotal(valorTotal);
    }

    public void tabelaDeItensEditada() {
        valorTotal = BigDecimal.ZERO;
        for (PedidoItem pedidoItem : entity.getItens())
            valorTotal = valorTotal.add(pedidoItem.getQuantidade().multiply(pedidoItem.getValor()));

        ajustarValores();
    }

    public void changeDescontoPercentual() {
        entity.setDescontoTotal(
                entity.getValorTotal().multiply(entity.getDescontoPercentual()).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP)
        );
        entity.setValorTotal(valorTotal.subtract(entity.getDescontoTotal()));
    }

    public void changeDescontoTotal() {
        entity.setDescontoPercentual(
                entity.getDescontoTotal().multiply(BigDecimal.valueOf(100)).divide(entity.getValorTotal(), 2, RoundingMode.HALF_UP)
        );
        entity.setValorTotal(valorTotal.subtract(entity.getDescontoTotal()));
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }
}
