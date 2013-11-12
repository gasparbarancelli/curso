package br.com.viasoft.model.service.impl;

import br.com.viasoft.model.data.PedidoData;
import br.com.viasoft.model.entity.Cliente;
import br.com.viasoft.model.entity.Pedido;
import br.com.viasoft.model.entity.PedidoParcela;
import br.com.viasoft.model.framework.CrudService;
import br.com.viasoft.model.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Gaspar Barancelli
 * Date: 10/11/13
 * Time: 19:38
 * To change this template use File | Settings | File Templates.
 */
@Service
public class PedidoServiceImpl extends CrudService<Pedido, Long> implements PedidoService {

    @Autowired private PedidoData pedidoData;

    @Override
    protected JpaRepository<Pedido, Long> getData() {
        return pedidoData;
    }

    @Override
    protected Pedido preProcessorSave(Pedido entity) throws Exception {
        entity.setParcelas(new ArrayList<PedidoParcela>());

        BigDecimal valorParcela = entity.getValorTotal().divide(
                BigDecimal.valueOf(entity.getParcela().getNumeroDeParcelas()), 2, RoundingMode.HALF_UP
        );

        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < entity.getParcela().getNumeroDeParcelas(); i++) {
            calendar.add(Calendar.DAY_OF_MONTH, entity.getParcela().getDiasEntreParcelas());

            PedidoParcela pedidoParcela = new PedidoParcela();
            pedidoParcela.setPedido(entity);
            pedidoParcela.setVencimento(calendar.getTime());
            pedidoParcela.setValor(valorParcela);
            entity.getParcelas().add(pedidoParcela);
        }

        return entity;
    }

    @Override
    @Transactional(readOnly = true)
    public Pedido findById(Long aLong) {
        Pedido pedido = super.findById(aLong);
        pedido.getItens();
        pedido.getParcelas();
        return pedido;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pedido> findByCliente(Cliente cliente) {
        return pedidoData.findByCliente(cliente);
    }
}
