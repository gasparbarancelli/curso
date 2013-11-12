package br.com.viasoft.model.entity;

import br.com.viasoft.model.enumeration.SimNao;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Gaspar Barancelli
 * Date: 10/11/13
 * Time: 19:05
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Parcela implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "DESCRICAO", length = 60, nullable = false)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "ATIVO", nullable = false, length = 1)
    private SimNao ativo;

    @Column(name = "NUMERO_DE_PARCELAS", nullable = false)
    private Integer numeroDeParcelas;

    @Column(name = "DIAS_ENTRE_PARCELAS", nullable = false)
    private Integer diasEntreParcelas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public SimNao getAtivo() {
        return ativo;
    }

    public void setAtivo(SimNao ativo) {
        this.ativo = ativo;
    }

    public Integer getNumeroDeParcelas() {
        return numeroDeParcelas;
    }

    public void setNumeroDeParcelas(Integer numeroDeParcelas) {
        this.numeroDeParcelas = numeroDeParcelas;
    }

    public Integer getDiasEntreParcelas() {
        return diasEntreParcelas;
    }

    public void setDiasEntreParcelas(Integer diasEntreParcelas) {
        this.diasEntreParcelas = diasEntreParcelas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Parcela parcela = (Parcela) o;

        if (id != null ? !id.equals(parcela.id) : parcela.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
