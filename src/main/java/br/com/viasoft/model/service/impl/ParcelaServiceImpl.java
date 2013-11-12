package br.com.viasoft.model.service.impl;

import br.com.viasoft.model.data.ParcelaData;
import br.com.viasoft.model.entity.Parcela;
import br.com.viasoft.model.enumeration.SimNao;
import br.com.viasoft.model.framework.CrudService;
import br.com.viasoft.model.service.ParcelaService;
import com.googlecode.ehcache.annotations.Cacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Gaspar Barancelli
 * Date: 10/11/13
 * Time: 19:10
 * To change this template use File | Settings | File Templates.
 */
@Service
public class ParcelaServiceImpl extends CrudService<Parcela, Long> implements ParcelaService {

    @Autowired private ParcelaData parcelaData;

    @Override
    protected JpaRepository<Parcela, Long> getData() {
        return parcelaData;
    }
    @Override
    @Transactional(readOnly = true)
    public List<Parcela> findByDescricaoContaining(String descricao) {
        if (descricao == null)
            descricao = "";

        return parcelaData.findByDescricaoContaining(descricao);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheName="parcela")
    public List<Parcela> completeOnlyAtivos(String descricao) {
        if (descricao == null)
            descricao = "";

        return parcelaData.findByDescricaoContainingAndAtivo(descricao, SimNao.S, getPageable());
    }
}
