package br.com.viasoft.model.framework;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;


/**
 * Esta classe prove metodos para fazer injecao de dependencia do entityManager
 * assim como fields para realizar queries.
 * @param <T> entidade
 * @author Gaspar
 */
public abstract class BaseRepository<T extends Serializable> {

    /**
     * Injecao da dependecia do entityManager
     */
    @PersistenceContext
    protected EntityManager entityManager;

    /**
     * Fields utilizados para realizar query da entidade
     */
    protected Root<T> root;
    protected CriteriaQuery<T> query;
    protected CriteriaBuilder criterio;
    protected Predicate predicate;

    /**
     * Fields utilizados para padronizar a quantidade
     * de registros retornados em uma query
     */
    protected static final Integer MAX_RESULT_COMPLETE = 10;
    protected static final Integer MAX_RESULT_COMPLETE2 = 30;
    protected static final Integer MAX_RESULT_SEARCH = 100;

    /**
     * Tipo da entidade passada por generics
     */
    protected Class<T> type;

    /**
     * Obtem o tipo da entidade atribuida por generics
     */
    public BaseRepository() {
        type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Inicializa todos os fields utilizados para relizarem query
     */
    protected void initialize() {
        criterio = entityManager.getCriteriaBuilder();
        query = criterio.createQuery(type);
        root = query.from(type);
        predicate = null;
    }

    /**
     * Adiciona a condicao OR a clausula WHERE da query
     * @param predicate condicao
     * @return
     */
    protected Predicate or(Predicate predicate) {
        if (this.predicate == null) {
            this.predicate = predicate;
        } else {
            this.predicate = criterio.or(this.predicate, predicate);
        }
        addWhere();

        return this.predicate;
    }

    /**
     * Adiciona a condicao AND a clausula WHERE da query
     * @param predicate condicao
     * @return
     */
    protected Predicate and(Predicate predicate) {
        if (this.predicate == null) {
            this.predicate = predicate;
        } else {
            this.predicate = criterio.and(this.predicate, predicate);
        }
        addWhere();

        return this.predicate;
    }

    /**
     * Adicionada uma clausula where caso exista alguma condicao
     * a ser utilizada na query
     */
    protected void addWhere() {
        if (predicate != null) {
            query.where(predicate);
        }
    }

    /**
     * Utilitario para o comando Containing onde eh criado
     * um comando LIKE do SQL
     * @param valor
     * @return
     */
    protected String containing(String valor) {
        return "%"+valor.toLowerCase()+"%";
    }

    /**
     * Retorna o valor de String caso o objeto passado por parametro
     * nao seja nulo
     * @param value objeto a ser obtido o valor em forma de string
     * @return retorna o valor do objeto em formatado de string caso o mesmo nao seja nulo
     */
    protected String getStringValue(Object value) {
        return value != null ? (String) value : null;
    }

}


