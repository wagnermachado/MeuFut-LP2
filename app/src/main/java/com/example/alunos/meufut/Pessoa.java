package com.example.alunos.meufut;

/**
 * Created by alunos on 05/10/17.
 */

public class Pessoa {

    private String nome;
    private int pg, og;

    public Pessoa(String nome) {
        this.nome = nome;
        this.pg = 0;
        this.og = 0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPg() {
        return pg;
    }

    public void setPg(int pg) {
        this.pg = pg;
    }

    public int getOg() {
        return og;
    }

    public void setOg(int og) {
        this.og = og;
    }
}
