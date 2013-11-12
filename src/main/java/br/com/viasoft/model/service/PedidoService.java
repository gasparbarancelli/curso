package br.com.viasoft.model.service;

import br.com.viasoft.model.entity.Cliente;
import br.com.viasoft.model.entity.Pedido;
import br.com.viasoft.model.framework.ICrudService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Gaspar Barancelli
 * Date: 10/11/13
 * Time: 19:38
 * To change this template use File | Settings | File Templates.
 */
public interface PedidoService extends ICrudService<Pedido, Long> {

    /**
     * Obtem todos pedidos vinculados ao cliente passado por parametro
     * @param cliente
     * @return
     */
    List<Pedido> findByCliente(Cliente cliente);

}
