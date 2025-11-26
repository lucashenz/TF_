/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;
import entidades.*;

import java.util.Date;
import java.util.Queue;

/**
 *
 * @author arthurzimmer
 */
public class Venda {
    private Comprador comprador;
    private Tecnologia tecnologia;
    private long num;
    private Date data;
    private double valorFinal;

    public Venda(Comprador comprador, Tecnologia tecnologia, long num, Date data) {
        this.comprador = comprador;
        this.tecnologia = tecnologia;
        this.num = num;
        this.data = data;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public Tecnologia getTecnologia() {
        return tecnologia;
    }

    public long getNum() {
        return num;
    }

    public Date getData() {
        return data;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public void setTecnologia(Tecnologia tecnologia) {
        this.tecnologia = tecnologia;
    }

    public void setNum(long num) {
        this.num = num;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public double calculaValorFinal(Queue<Venda> FilaDevendas) {
        if (tecnologia == null || tecnologia.getFornecedor() == null) {
            System.err.println("Erro: tecnologia ou fornecedor não definido para a venda" + num);
            return 0.0;
        }
        double valorBase = tecnologia.getValorBase();
        double valorAcrescido = valorBase;
        Area areaFornecedor = tecnologia.getFornecedor().getArea();

        switch (areaFornecedor) {
            case TI:
                valorAcrescido = valorBase * 1.2;
                break;
            case ANDROIDES:
                valorAcrescido = valorBase * 1.15;
                break;

            case EMERGENTE:
                valorAcrescido = valorBase * 1.25;
                break;

            case ALIMENTOS:
                valorAcrescido = valorBase * 1.10;
                break;
            default:
                System.err.println("Area desconhecida para vendas" + num);
        }
        int contadorVendas = 0;
        for (Venda v : FilaDevendas) {
            if (v.getComprador().equals(this.comprador) && v.getNum() != this.num) {
                contadorVendas++;
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return "\nComprador: \n  -Nome comprador: " + comprador.getNome() + "\n  -Email comprador: " + comprador.getEmail() + "\n  -País comprador: " + comprador.getPais() + "\n  -ID comprador: " + comprador.getCod() + "; \nTecnologia: \n  -Modelo tecnologia: " + tecnologia.getModelo() + "\n  -Descrição tecnologia: " + tecnologia.getDescricao() + "\n  -Valor base tecnologia: " + tecnologia.getValorBase() + "\n  -Peso tecnologia: " + tecnologia.getPeso() + "\n  -Temperatura tecnologia: " + tecnologia.getTemperatura() + "\n   -Nome fornecedor tecnologia: " + tecnologia.getFornecedor().getNome() + "\n   -Fundação fornecedor da tecnologia:" + tecnologia.getFornecedor().getFundacao() + "\n   -Área fornecedor da tecnologia: " + tecnologia.getFornecedor().getArea() + "\n   -ID fornecedor da tecnologia: " + tecnologia.getFornecedor().getCod() + "; \nID: " + num + "; \nData: " + data + "; \nValor final: " + valorFinal;
    }

    public String toCSVString() {

        Comprador c = getComprador();
        Tecnologia t = getTecnologia();

        long codComprador = (c != null) ? c.getCod() : 0;
        long idTecnologia = (t != null) ? t.getId() : 0;

        return getNum() + ";" + getData().getTime() + ";" + getNum() + ";" +
                codComprador + ";" + idTecnologia;
    }

}
