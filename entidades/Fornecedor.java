/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;
import java.util.Date;


/**
 *
 * @author arthurzimmer
 */
public class Fornecedor extends Participante{
    private Date fundacao;
    private Area area;
        
    public Fornecedor(long cod, String nome, Date fundacao, Area area) {
        super(cod, nome);
        this.fundacao = fundacao;
        this.area = area;
    }

    public Date getFundacao() {
        return fundacao;
    }

    public Area getArea() {
        return area;
    }

    public void setFundacao(Date fundacao) {
        this.fundacao = fundacao;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    @Override
    public String geraDescricao() {
        return  "\nData de fundação: " + fundacao + "; \nÁrea: " + area + super.geraDescricao();
    }

    @Override
    public String toString() {
        return "Fornecedor{" + "fundacao=" + fundacao + ", area=" + area + '}';
    }

    public String toCSVString() {
        return getCod() + ";" + getNome() + ";" + getFundacao().getTime() + ";" + getArea();
    }
}
