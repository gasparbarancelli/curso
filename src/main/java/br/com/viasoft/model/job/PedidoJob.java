package br.com.viasoft.model.job;

import br.com.viasoft.model.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: Gaspar Barancelli
 * Date: 11/11/13
 * Time: 22:23
 * To change this template use File | Settings | File Templates.
 */
@Component
public class PedidoJob {

    @Autowired private PedidoService pedidoService;

    public void executar() {
        pedidoService.findAll();
    }

}
