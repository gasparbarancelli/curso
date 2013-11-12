package br.com.viasoft.model.framework;

import java.io.Serializable;
import java.util.List;

/**
 * Esta classe prove os metodos basicos utilizados para o CRUD da entidade passada por generics
 * ao extender esta classe.
 * @author Gaspar
 */
public interface ICrudService<T extends Object, ID extends Serializable> {

    /**
     * Metodo responsavel por persistir e alterar no banco de dados
     * a entidade passada por parametro
     * @param entity entidade a ser persitida/alterada no banco de dados
     * @return entidade persistida/alterada com seus fields atualizados
     * @throws Exception caso ocorrer algum erro ao persistir/alterar
     * entidade no banco de dados
     */
    T save(T entity) throws Exception;

    /**
     * Metodo responsavel por persistir no banco de dados a lista de
     * entidades passadas por parametro
     * @param lista lista de entidades a ser persistidas no banco de dados
     * @throws Exception caso ocorrer algum erro ao persistir entidade no banco de dados
     */
    void save(Iterable<T> lista) throws Exception;

    /**
     * Metodo responsavel por remove do banco de dados a entidade na qual seu identificador seja
     * correspondente ao valor passado por parametro
     * @param id identificador corresponte a entidade a ser removida
     * @throws Exception caso ocorrer algum erro ao remover entidade no banco de dados
     */
    void delete(ID id) throws Exception ;

    /**
     * Metodo responsavel por remover do banco de dados a entidade passada por parametro
     * @param entity entidade a ser removida do banco de dados
     * @throws Exception caso ocorrer algum erro ao remover entidade no banco de dados
     */
    void delete(T entity) throws Exception;

    /**
     * Metodo responsavel retornar uma lista com todas as entidades existentes no banco de dados
     * @return
     */
    List<T> findAll();

    /**
     * Metodo responsavel por recuperar do banco de dados a entidade na qual seu identificador
     * seja o mesmo do valor passado por parametro
     * @param id identificador da entidade a ser recuperada do banco de dados
     * @return entidade recuperada do banco de dados pelo seu identificador passado por parametro
     */
    T findById(ID id);

}

