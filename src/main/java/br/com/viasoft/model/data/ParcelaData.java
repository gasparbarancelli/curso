package br.com.viasoft.model.data;

import br.com.viasoft.model.entity.Parcela;
import br.com.viasoft.model.enumeration.SimNao;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Gaspar Barancelli
 * Date: 10/11/13
 * Time: 19:09
 * To change this template use File | Settings | File Templates.
 */
public interface ParcelaData extends JpaRepository<Parcela, Long> {

    List<Parcela> findByDescricaoContaining(String descricao);

    List<Parcela> findByDescricaoContainingAndAtivo(String descricao, SimNao ativo, Pageable pageable);

}
