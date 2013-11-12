package br.com.viasoft.model.service;

import br.com.viasoft.model.entity.Item;
import br.com.viasoft.model.framework.ICrudService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Gaspar Barancelli
 * Date: 04/11/13
 * Time: 22:39
 * To change this template use File | Settings | File Templates.
 */
public interface ItemService extends ICrudService<Item, Long> {

    /**
     * Metodo responsavel por retornar uma lista de itens
     * onde o nome dos mesmos contenham o valor passado pelo parametro nome
     * @param nome
     * @return
     */
    List<Item> findByNomeContaining(String nome);

    /**
     * Obtem os 10 primeiros itens quando o nome dos mesmos
     * contenham o valor passado pelo parametro nome
     * @param nome
     * @return
     */
    List<Item> complete(String nome);

}
