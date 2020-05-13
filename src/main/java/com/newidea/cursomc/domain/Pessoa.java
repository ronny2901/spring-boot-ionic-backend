package com.newidea.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.newidea.cursomc.domain.enums.TipoCliente;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "pessoa")
@SecondaryTable(name = "funcionario", pkJoinColumns = @PrimaryKeyJoinColumn(name="id"))
public class Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;

    public Pessoa() {
    }

    public Pessoa(Integer id, String nome, String idade) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="nome")
    private String nome;

    @Column(name = "idade")
    private String idade;

    @Column(name="cargo", table = "funcionario")
    private String cargo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
