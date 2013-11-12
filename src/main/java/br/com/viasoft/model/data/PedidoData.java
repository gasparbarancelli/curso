package br.com.viasoft.model.data;

import br.com.viasoft.model.entity.Cliente;
import br.com.viasoft.model.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Gaspar Barancelli
 * Date: 10/11/13
 * Time: 19:38
 * To change this template use File | Settings | File Templates.
 */
public interface PedidoData extends JpaRepository<Pedido, Long> {

    List<Pedido> findByCliente(Cliente cliente);

}
