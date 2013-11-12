package br.com.viasoft.model.data;

import br.com.viasoft.model.entity.Cliente;
import br.com.viasoft.model.enumeration.SimNao;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Gaspar Barancelli
 * Date: 06/11/13
 * Time: 22:00
 * To change this template use File | Settings | File Templates.
 */
public interface ClienteData extends JpaRepository<Cliente, Long> {

    List<Cliente> findByNomeContaining(String nome);

    List<Cliente> findByNomeContainingAndAtivo(String nome, SimNao ativo, Pageable pageable);

}
