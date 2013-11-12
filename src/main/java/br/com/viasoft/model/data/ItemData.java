package br.com.viasoft.model.data;

import br.com.viasoft.model.entity.Item;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Gaspar Barancelli
 * Date: 04/11/13
 * Time: 22:22
 * To change this template use File | Settings | File Templates.
 */
public interface ItemData extends JpaRepository<Item, Long> {

    List<Item> findByNomeContaining(String nome);

    List<Item> findByNomeContaining(String nome, Pageable pageable);

}
