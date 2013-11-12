package br.com.viasoft.model.service;

import br.com.viasoft.model.entity.Cliente;
import br.com.viasoft.model.framework.ICrudService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Gaspar Barancelli
 * Date: 06/11/13
 * Time: 22:01
 * To change this template use File | Settings | File Templates.
 */
public interface ClienteService extends ICrudService<Cliente, Long> {

    /**
     * Metodo responsavel por retornar uma lista de clientes
     * onde o nome dos mesmos contenham o valor passado pelo parametro nome
     * @param nome
     * @return
     */
    List<Cliente> findByNomeContaining(String nome);

    /**
     * Obtem os 10 primeiros clientes apenas ativos
     * quando o nome do mesmos contenham o valor
     * passado pelo parametro nome
     * @param nome
     * @return
     */
    List<Cliente> completeOnlyAtivos(String nome);

}
