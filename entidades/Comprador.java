/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author arthurzimmer
 */
public class Comprador extends Participante{
    private String pais;
    private String email;
    private int comprasRealizadas;

    public Comprador(long cod, String nome, String pais, String email) {
        super(cod, nome);
        this.email = email;
        this.pais = pais;
        this.comprasRealizadas = 0;
    }

    public String getEmail() {
        return email;
    }

    public String getPais() {
        return pais;
    }

    public void setComprasRealizadas(int comprasRealizadas) {
        this.comprasRealizadas = comprasRealizadas;
    }

    public int getComprasRealizadas() {
        return comprasRealizadas;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPais(String Pais) {
        this.pais = pais;
    }

    public int incrementaCompra() {
        int compra = this.comprasRealizadas;
        compra++;

        return compra;
    }



    @Override
    public String geraDescricao() {
        return super.geraDescricao() + "; \nEmail: " + email + "; \nPaís: " + pais;
    }

    public String toCSVString() {
        return getCod() + ";" + getNome() + ";" + getPais() + ";" + getEmail();
    }
}
