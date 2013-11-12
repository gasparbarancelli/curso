package br.com.viasoft.model.entity;

import java.io.Serializable;

import javax.persistence.*;

import br.com.viasoft.model.enumeration.SimNao;

@Entity
public class Usuario implements Serializable {

	private static final long serialVersionUID = -7087941956773933564L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Long id;
	
	@Column(name="USUARIO", length=60, nullable=false)
	private String usuario;
	
	@Column(name="SENHA", length=100, nullable=false)
	private String senha;
	
	@Column(name="ATIVO", length=1, nullable=false)
	private SimNao ativo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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

        Usuario usuario = (Usuario) o;

        if (id != null ? !id.equals(usuario.id) : usuario.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
