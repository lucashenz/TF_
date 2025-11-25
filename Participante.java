/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

/**
 *
 * @author arthurzimmer
 */
public abstract class Participante {
    private long cod;
    private String nome;

    public Participante(long cod, String nome) {
        this.cod = cod;
        this.nome = nome;
    }

    public long getCod() {
        return cod;
    }

    public String getNome() {
        return nome;
    }

    public void setCod(long cod) {
        this.cod = cod;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String geraDescricao() {
        return "\nID: " + cod + "; \nNome: " + nome;
    }
    
    
}
