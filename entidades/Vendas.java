/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.util.ArrayList;
import java.util.List;


public class Vendas {
    private List<Venda> vendas = new ArrayList<Venda>();

    public Vendas(List vendas) {
        this.vendas = vendas;
    }

    public List<Venda> getVendas() {
        return vendas;
    }
    
    public void addVenda (Venda v) {
        vendas.add(v); // verificar logica de cadastro
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }

    @Override
    public String toString() {
        return "Vendas{" + "vendas=" + vendas + '}';
    }

    public int size() {
        return vendas.size();
    }

    public int removeVendaPorNumero(long numero) {
        for (int i = 0; i < vendas.size(); i++) {
            Venda v = vendas.get(i);
            if (v.getNum() == numero) {
                vendas.remove(i);
                return i;
            }
        }
        return -1;
    }
    
    
}
