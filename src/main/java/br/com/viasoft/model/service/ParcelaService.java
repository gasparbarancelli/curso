package br.com.viasoft.model.service;

import br.com.viasoft.model.entity.Cliente;
import br.com.viasoft.model.entity.Parcela;
import br.com.viasoft.model.framework.ICrudService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Gaspar Barancelli
 * Date: 10/11/13
 * Time: 19:09
 * To change this template use File | Settings | File Templates.
 */
public interface ParcelaService extends ICrudService<Parcela, Long> {

    /**
     * Metodo responsavel por retornar uma lista de parcelas
     * onde a descricao das mesmas contenham o valor passado pelo parametro descricao
     * @param descricao
     * @return
     */
    List<Parcela> findByDescricaoContaining(String descricao);

    /**
     * Obtem as 10 primeiras parcelas somente ativoas
     * quando a descricao das mesmas contenham o valor
     * passado pelo parametro descrica
     * @param descricao
     * @return
     */
    List<Parcela> completeOnlyAtivos(String descricao);

}
