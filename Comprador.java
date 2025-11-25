/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.util.Date;

/**
 *
 * @author arthurzimmer
 */
public class Comprador extends Participante{
    private String pais;
    private String email;
        
    public Comprador(long cod, String nome, String pais, String email) {
        super(cod, nome);
        this.email = email;
        this.pais = pais;
    }

    public String getEmail() {
        return email;
    }

    public String getPais() {
        return pais;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPais(String Pais) {
        this.pais = pais;
    }

    @Override
    public String geraDescricao() {
        return super.geraDescricao() + "; \nEmail: " + email + "; \nPaís: " + pais;
    }
}
