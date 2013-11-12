package br.com.viasoft.web.framework;

import br.com.viasoft.model.entity.Item;
import br.com.viasoft.model.framework.ICrudService;
import br.com.viasoft.web.util.JsfUtil;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;


/**
 * Esta classe prove propriedades e metodos basicos para
 * utilizacao de um controller com acesso ao banco de dados
 * @param <T> entidade
 * @param <ID> identificacao da entidade
 * @author Gaspar
 */
public abstract class CrudController<T extends Object, ID extends Serializable> extends BaseController {

    /**
     * Tipo da entidade passada por generics
     */
    private Class<T> type;

    /**
     * Field corresponde a entidade passada por generics utilizada para persistir/alterar/deletar no banco de dados
     */
    protected T entity;

    /**
     * Field correspondente a entidade apenas utilizada para exibir informacoes
     */
    protected T entityView;

    /**
     * Lista de entidades passadas por generics
     */
    protected List<T> lsEntity = new ArrayList<T>();

    /**
     * Identificador passado por generics
     */
    protected ID id;

    /**
     * Retornar a implementação da interface ICrudService para que a classe possa realizar as operacoes de CRUD
     * @return Servico a ser utilizado para persistir, alterar, excluir, editar e pesquisar a entidade passada por generics
     */
    protected abstract ICrudService<T, ID> getService();

    /**
     * Obtendo o tipo da entidade passada por generics e instanciando uma nova entidade
     */
    public CrudController() {
        setType((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
        create();
    }

    @PostConstruct
    public void init() {
        inicializar();
    }

    /**
     * Método a ser executado ao inicializar o metodo init
     */
    protected void inicializar() {

    }

    /**
     * Instancia uma nova entidade conforme seu tipo obtido por generics
     * @return
     */
    private T newRecord() {
        try {
            return (T) getType().newInstance();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Este metodo recupera um objeto do scopo de flash
     * e caso o mesmo nao seja nulo o atribui o valor da entidade
     */
    protected void postCreate() {
        Object entity = JsfUtil.getFlashParameter("editar");
        if (entity != null) {
            this.entity = (T) entity;
        }
    }

    /**
     * Cria uma nova instacia da entidade passada por generics
     */
    private void create() {
        entity = newRecord();
        postCreate();
    }

    /**
     * Cria uma nova instacia da entidade passada por generics
     */
    public void reset() {
        create();
    }

    /**
     * Metodo a ser chamado antes de persistir a entidade no banco de dados
     * @param entity entidade a ser persistida
     * @return entidade a ser persistida
     */
    protected T preProcessorSave(T entity)  {
        return entity;
    }

    /**
     * Metodo a ser chamado depois de persistir a unidade no banco de dados
     * @param entity entidade persistida
     * @return entidade persistida
     */
    protected T postProcessorSave(T entity)  {
        return entity;
    }

    /**
     * Método a ser invocado antes de persistir a entidade ao banco de dados
     * apenas se o retorno for true o metodo sera persistido.
     * @param entity entidade a ser persistida no banco de dados
     * @return TRUE a entidade sera persistida
     */
    protected Boolean validacaoSave(T entity) {
        return Boolean.TRUE;
    }

    /**
     * Metodo responsavel por adicionar ao scopo de flash a entidade
     * passada por parametro para que seja recuperada posteriormente para edicao
     * @param entity entidade a ser editada
     * @return retorna a pagina de cadastro da entidade
     */
    public String editar(T entity) {
        JsfUtil.setFlashParameter("editar", entity);
        return getUrlFormPage();
    }

    /**
     * Este metodo deve retornar a pagina de cadastro da entidade
     * @return pagina de cadastro da entidade
     */
    protected abstract String getUrlFormPage();

    /**
     * Método responsavel executar validacoes e invocar metodos de pre e post
     * persistencia, a ordem de invocacao dos metodos sao:
     *     validacaoSave;
     *     preProcessorSave;
     *     servico responsavel por persistir no banco de dados;
     *     postProcessorSave;
     * Em caso de sucesso ao persistir a entidade uma mensagem de sucesso é adicionada ao contexto
     * caso contrario uma mensagem contendo o erro lancado é adicionado ao contexto
     */
    public void save() {
        try {
            if (validacaoSave(entity)) {
                preProcessorSave(entity);
                getService().save(entity);
                postProcessorSave(entity);
                addMessage("Registro salvo com sucesso!", FacesMessage.SEVERITY_INFO);
            }
        } catch (Exception e) {
            addMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    /**
     * Método responsavel por invocar o servico responsavel por deletar a entidade do banco de dados.
     * Em caso de sucesso o método de pesquisa é invocado e uma mensagem de sucesso é adicionada ao contexto.
     * Caso ocorrer um erro uma mensagem contento o erro lancado é adicionado ao contexto.
     */
    public void delete() {
        try {
            getService().delete(getId());
            find();
            addMessage("Registro removido com sucesso!", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            addMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    /**
     * Método responsavel por selecionar no banco de dados as informacoes
     * referentes a entidade
     */
    public void find() {
        lsEntity.clear();
        lsEntity.addAll(getService().findAll());
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    private Class<T> getType() {
        return type;
    }

    private void setType(Class<T> type) {
        this.type = type;
    }

    public List<T> getLsEntity() {
        return lsEntity;
    }

    public void setLsEntity(List<T> lsEntity) {
        this.lsEntity = lsEntity;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public T getEntityView() {
        return entityView;
    }

    public void setEntityView(T entityView) {
        this.entityView = entityView;
    }

}
