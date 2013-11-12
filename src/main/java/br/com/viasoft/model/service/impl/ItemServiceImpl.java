package br.com.viasoft.model.service.impl;

import br.com.viasoft.model.data.ItemData;
import br.com.viasoft.model.entity.Item;
import br.com.viasoft.model.framework.CrudService;
import br.com.viasoft.model.service.ItemService;
import com.googlecode.ehcache.annotations.Cacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Gaspar Barancelli
 * Date: 04/11/13
 * Time: 22:40
 * To change this template use File | Settings | File Templates.
 */
@Service
public class ItemServiceImpl extends CrudService<Item, Long> implements ItemService {

    @Autowired private ItemData itemData;

    @Override
    protected JpaRepository<Item, Long> getData() {
        return itemData;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Item> findByNomeContaining(String nome) {
        if (nome == null)
            nome = "";

        return itemData.findByNomeContaining(nome);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheName="item")
    public List<Item> complete(String nome) {
        if (nome == null)
            nome = "";

        return itemData.findByNomeContaining(nome, getPageable());
    }
}
