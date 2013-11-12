package br.com.viasoft.model.entity;

import br.com.viasoft.model.enumeration.SimNao;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Gaspar Barancelli
 * Date: 06/11/13
 * Time: 21:57
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NOME", length = 80, nullable = false)
    private String nome;

    @Column(name = "ENDERECO", length = 100, nullable = true)
    private String endereco;

    @Column(name = "NUMERO", length = 10, nullable = true)
    private String numero;

    @Column(name = "BAIRRO", length = 100, nullable = true)
    private String bairro;

    @Column(name = "TELEFONE", length = 20, nullable = false)
    private String telefone;

    @Column(name = "EMAIL", length = 80, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "ATIVO", length = 1, nullable = false)
    private SimNao ativo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public SimNao getAtivo() {
        return ativo;
    }

    public void setAtivo(SimNao ativo) {
        this.ativo = ativo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cliente cliente = (Cliente) o;

        if (id != null ? !id.equals(cliente.id) : cliente.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
