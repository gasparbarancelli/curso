package br.com.viasoft.model.service.impl;

import br.com.viasoft.model.data.ClienteData;
import br.com.viasoft.model.entity.Cliente;
import br.com.viasoft.model.enumeration.SimNao;
import br.com.viasoft.model.framework.CrudService;
import br.com.viasoft.model.service.ClienteService;
import com.googlecode.ehcache.annotations.Cacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Gaspar Barancelli
 * Date: 06/11/13
 * Time: 22:01
 * To change this template use File | Settings | File Templates.
 */
@Service
public class ClienteServiceImpl extends CrudService<Cliente, Long> implements ClienteService {

    @Autowired private ClienteData clienteData;

    @Override
    protected JpaRepository<Cliente, Long> getData() {
        return clienteData;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findByNomeContaining(String nome) {
        if (nome == null)
            nome = "";

        return clienteData.findByNomeContaining(nome);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheName="cliente")
    public List<Cliente> completeOnlyAtivos(String nome) {
        if (nome == null)
            nome = "";

        return clienteData.findByNomeContainingAndAtivo(nome, SimNao.S, getPageable());
    }
}
