/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author arthurzimmer
 */
public class Vendas {
    private List<Venda> vendas = new ArrayList<Venda>();

    public Vendas(List vendas) {
        this.vendas = vendas;
    }

    public List<Venda> getVendas() {
        return vendas;
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

    public boolean addVenda (Venda v) {
        for (int i = 0; i < vendas.size(); i++) {
            Venda venda = vendas.get(i);

            // verifica duplicidade de id
            if (v.getNum() == venda.getNum())
                throw new IllegalArgumentException("Código de venda repetido, tente novamente.");

            // insere em ordem decrescente
            if (v.getNum() > venda.getNum()) {
                vendas.add(i, v);
                return true;
            }

        }

        return vendas.add(v); // verificar logica de cadastro
    }

    public Venda getVenda(int i) {
        return vendas.get(i);
    }

    public int removeVendaPorNumero(int numero) {
            for (int i = 0; i < vendas.size(); i++) {
                if (vendas.get(i).getNum() == numero) {
                    vendas.remove(i);
                    return 0;
                }
            }
            return 1;
        }
    }

