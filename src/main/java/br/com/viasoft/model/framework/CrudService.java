package br.com.viasoft.model.framework;

import org.apache.log4j.Logger;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

/**
 * User: gaspar
 * Date: 16/08/13
 * Time: 10:47
 * Esta classe prove metodos basicos para salvar, editar, remover e buscar informacoes no banco de dados
 * referentes as entidades atribuidas por generics ao extender esta classe
 * @param <T> entidade
 * @param <ID> identificacao da entidade
 */
public abstract class CrudService<T, ID extends Serializable> implements ICrudService<T, ID> {

    /**
     * field utilizada para gravar os logs
     */
    protected static final Logger logger = Logger.getLogger(CrudService.class.getName());

    /**
     * Metodo responsavel por retornar a interface JpaRepository implentada,
     * onde contem os metodos padoes para o CRUD (save, update, delete, find....)
     * que serao utilizadas nos demais metodos da classe abstrata
     * @return interface contendo metodos utilizados para o CRUD (save, update, delete, find....)
     */
    protected abstract JpaRepository<T, ID> getData();

    /**
     * Metodo invocado antes de persistir a entidade passada por parametro no banco de dados
     * @param entity entidade a ser persistida
     * @return entidade a ser persistida
     * @throws Exception retorna qualquer tipo de erro e atribui o mesmo ao log e passa pra frente
     */
    protected T preProcessorSave(T entity) throws Exception  {
        return entity;
    }

    /**
     * Metodo chamado depois de persistir a entidade passada por parametro no banco de dados
     * @param entity entidade persistida
     * @return entidade persistida
     * @throws Exception retorna qualquer tipo de erro e atribui o mesmo ao log e passa pra frente
     */
    protected T postProcessorSave(T entity) throws Exception {
        return entity;
    }

    /**
     * Metodo invocado antes de deletar a entidade passada por parametro no banco de dados
     * @param entity entidade a ser removida
     * @throws Exception retorna qualquer tipo de erro e atribui o mesmo ao log e passa pra frente
     */
    protected void preProcessorDelete(T entity) throws Exception {

    }

    /**
     * Metodo invocado antes de deletar a entidade corresponte ao id passado por parametro do banco de dados
     * @param id identificador da entidade a ser removida
     * @throws Exception retorna qualquer tipo de erro e atribui o mesmo ao log e passa pra frente
     */
    protected void preProcessorDelete(ID id) throws Exception {

    }

    /**
     * Metodo responsavel por persistir no banco de dados a entidade passada por parametro
     * invocando o metodo preProcessorSave antes de persistir a entidade e o metodo
     * postProcessorSave apos persistir a entidade no banco de dados
     * @param entity entidade a ser persistida no banco de dados
     * @return a entidade persistida no banco de dados com seus fields atualizados
     * @throws Exception caso algum erro ocorrer ao invocar os metodos preProcessorSave, postProcessorSave
     * e ao persistir a entidade  no banco de dados o erro eh atribuido ao log e eh passado para frente
     */
    @Override
    public T save(T entity) throws Exception {
        try {
            preProcessorSave(entity);
            getData().save(entity);
            afterSaveOrDelete();
            return postProcessorSave(entity);
        } catch (Exception e) {
            logger.error(e);
            throw new Exception("Erro ao salvar registro. " + e.getMessage());
        }
    }

    /**
     * Metodo invocado antes de persistir a lista de entidades passadas por parametro ao banco de dados
     * @param lista lista de entidades a serem persistida
     * @return retorna a lista de entidades a serem persistidas
     * @throws Exception retorna qualquer tipo de erro e atribui o mesmo ao log e passa pra frente
     */
    protected Iterable<T> preProcessorSave(Iterable<T> lista) throws Exception {
        return lista;
    }

    /**
     * Metodo invocado depois de persistir a lista de entidades passadas por parametro ao banco de dados
     * @param lista lista de entidades persistidas
     * @return retorna a lista de entidades persistidas
     * @throws Exception retorna qualquer tipo de erro e atribui o mesmo ao log e passa pra frente
     */
    protected Iterable<T> postProcessorSave(Iterable<T> lista) throws Exception {
        return lista;
    }

    /**
     * Metodo responsavel por persistir no banco de dados a lista de entidadas passados por parametro
     * invocando o metodo preProcessorSave antes de persistir as entidades e o metodo postProcessorSave
     * depois de persistir as entidades no banco de dados
     * @param lista entidades a serem persistidas no banco de dados
     * @throws Exception caso ocorra algum erro ao invocar os metodos preProcessorSave, postProcessorSave
     * ou persistir as entidades no banco de dados o erro eh atribuido ao log e passado pra frente
     */
    @Override
    public void save(Iterable<T> lista) throws Exception {
        try {
            preProcessorSave(lista);
            getData().save(lista);
            postProcessorSave(lista);
            afterSaveOrDelete();
        } catch (Exception e) {
            throw new Exception("Erro ao salvar registros. " + e.getMessage());
        }
    }

    /**
     * Metodo responsavel por persistir no banco de dados a entidada passada por parametro
     * e logo em seguida dar um flush no entityManager para verificar se a persistencia realmente
     * ocorreu com sucesso. Antes de persistir o metodo preProcessorSave eh invocado e apos persistir
     * e executar verificar se a persistencia realmente ocorreu o metodo postProcessorSave eh invocado
     * @param entity entidade a ser persistida
     * @return entidade a ser persistida com seus fields atualizados
     * @throws Exception caso ocorra algum erro ao invocar os metodos preProcessorSave, postProcessorSave
     * ou persistir as entidades no banco de dados o erro eh atribuido ao log e passado pra frente
     */
    public T saveAndFlush(T entity) throws Exception {
        try {
            preProcessorSave(entity);
            getData().save(entity);
            getData().flush();
            afterSaveOrDelete();
            return postProcessorSave(entity);
        } catch (Exception e) {
            logger.error(e);
            throw new Exception("Erro ao salvar registro. " + e.getMessage());
        }
    }

    /**
     * Metodo responsavel por remover do banco de dados a entidade com o identificador corresponte
     * ao valor passado por parametro. Antes de remover o metodo preProcessorDelete eh invocado
     * e apos remover o metodo postProcessorDelete eh invocado.
     * @param id identificador correspondente ao entidade a ser removida
     * @throws Exception caso ocorra algum erro ao executar os metodos preProcessorDelete,
     * postProcessorDelete e ao deletar o registro o erro eh atribuido ao log e passado pra frente
     */
    @Override
    public void delete(ID id) throws Exception {
        try {
            preProcessorDelete(id);
            getData().delete(id);
            postProcessorDelete(id);
            afterSaveOrDelete();
        } catch (Exception e) {
            logger.error(e);
            throw new Exception("Erro ao remover registro. " + e.getMessage());
        }
    }

    /**
     * Metodo a ser invocado apos o remover do banco de dados a entidade que corresponde
     * ao identificador passado por parametro
     * @param id identificador que corresponde a uma entidade
     * @throws Exception caso ocorra algum erro o mesmo eh atribuido ao log e passado pra frente
     */
    protected void postProcessorDelete(ID id) throws Exception{
    }

    /**
     * Metodo responsavel por remover do banco de dados a entidada passada por parametro.
     * O metodo preProcessorDelete eh invocado antes de deletar a entidade no banco de dados
     * e o metodo postProcessorDelete eh invcado apos deletar a entidade do banco de dados,
     * @param entity entidade a ser removida
     * @throws Exception caso ocorra algum erro ao executar os metodos preProcessorDelete,
     * postProcessorDelete e ao remover a entidade do banco de dados o erro eh atribuido ao log
     * e passado pra frente
     */
    @Override
    public void delete(T entity) throws Exception {
        try {
            preProcessorDelete(entity);
            getData().delete(entity);
            postProcessorDelete(entity);
            afterSaveOrDelete();
        } catch (Exception e) {
            logger.error(e);
            throw new Exception("Erro ao remover registro. " + e.getMessage());
        }
    }

    /**
     * Metodo responsavel por remover do banco de dados a entidade corresponda ao
     * identificador passado por parametro e logo em seguida eh invocado o metodo
     * flush para verificar se realmente a entidade foi removida.
     * Antes de remover o metodo preProcessorDelete eh invocado e apos remover e
     * executar o flush o metodo postProcessorDelete eh invocado.
     * @param id identificador correspondente a entidade a ser removida do banco de dados
     * @throws Exception caso ocorra algum erro ao executar os metodos
     * preProcessorSave, postProcessorSave, delete ou flush o erro eh atribuido
     * ao log e passado pra frente.
     */
    public void deleteAndFlush(ID id) throws Exception {
        try {
            preProcessorDelete(id);
            getData().delete(id);
            getData().flush();
            postProcessorDelete(id);
            afterSaveOrDelete();
        } catch (Exception e) {
            logger.error(e);
            throw new Exception("Erro ao remover registro. " + e.getMessage());
        }
    }

    /**
     * Metodo responsavel por remover do banco de dados a entidade passado por parametro
     * e logo em seguida chamar o metodo flush do entityManager para saber
     * se o registro realmente vai ser excluido com sucesso. Antes de deletar o registro
     * o metodo preProcessorDelete eh invocado e apos efetuar o delete e flush o metodo
     * postProcessor delete eh invocado.
     * @param entity entidade a ser removida do banco de dados
     * @throws Exception caso ocorra algum erro ao executar os metodos
     * preProcessorSave, postProcessorSave, delete ou flush o erro eh atribuido
     * ao log e passado pra frente.
     */
    public void deleteAndFlush(T entity) throws Exception {
        try {
            preProcessorDelete(entity);
            getData().delete(entity);
            getData().flush();
            postProcessorDelete(entity);
            afterSaveOrDelete();
        } catch (Exception e) {
            logger.error(e);
            throw new Exception("Erro ao remover registro. " + e.getMessage());
        }
    }

    /**
     * Metodo a ser invocado apos o remover a entidade do banco de dados
     * @param entity entidade removida do banco de dados
     * @throws Exception caso ocorra algum erro o mesmo eh atribuido ao log e passado pra frente
     */
    protected void postProcessorDelete(T entity) throws Exception {
    }

    /**
     * Obtem do banco de dados uma lista com todas as
     * entidades existentes no banco de dados
     * @return
     */
    @Override
    public List<T> findAll() {
        return getData().findAll();
    }

    /**
     * Obtem do banco de dados a entidade correspondente
     * ao identificador passado por parametro
     * @param id identificador da entidade
     * @return entidade obtida no banco de dados pelo seu identificador
     * passado por parametro
     */
    @Override
    public T findById(ID id) {
        return getData().findOne(id);
    }

    /**
     * Metodo responsavel por criar uma paginacao do SPRING DATA iniciando
     * na pagina 0 e setando ao maximo 10 registros
     * @return
     */
    public Pageable getPageable() {
        return new PageRequest(0, 10);
    }

    /**
     * Depois que o pedido for salvo ou deletado este metodo eh executado
     * geralmente utilizado para remover informacoes gravadas em cache
     */
    public void afterSaveOrDelete() {

    }

}

