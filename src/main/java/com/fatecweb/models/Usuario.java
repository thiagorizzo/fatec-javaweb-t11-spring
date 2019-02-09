/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fatecweb.models;

public class Usuario {

    private String nome;

    private int id;
    
    private String senha;

    public Usuario(String nome, int id) {
        this.nome = nome;
        this.id = id;
    }

    public Usuario(String nome, int id, String senha) {
        this.nome = nome;
        this.id = id;
        this.senha = senha;
    }
    
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
}
