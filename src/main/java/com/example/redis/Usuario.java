package com.example.redis;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Usuario implements Serializable {

    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName = "SEQ_USER", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
    private Long id;
    private String nome;
    private Long seguidores;

    public Usuario() {
    }

    public Usuario(String nome, Long seguidores) {
        this.nome = nome;
        this.seguidores = seguidores;
    }

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

    public Long getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(Long seguidores) {
        this.seguidores = seguidores;
    }
}
