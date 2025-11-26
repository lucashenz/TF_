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
public class Tecnologias {
    private List<Tecnologia> tecnologias = new ArrayList<>();

    public Tecnologias(List tecnologias) {
        this.tecnologias = tecnologias;
    }

    public List<Tecnologia> getTecnologias() {
        return tecnologias;
    }

    public void setTecnologias(List<Tecnologia> tecnologias) {
        this.tecnologias = tecnologias;
    }

    public boolean addTecnologia (Tecnologia t) {
        for(int i = 0; i < tecnologias.size(); i++){
            Tecnologia tec = tecnologias.get(i);

            // verifica duplicidade de ID
            if (tec.getId() == t.getId())
                throw new IllegalArgumentException("Código de usuário repetido, tente novamente.");

            // insere já ordenado
            if (t.getId() < tec.getId()) {
                tecnologias.add(i, t);
                return true;
            }
        }
        
        tecnologias.add(t);
        return true;
    }
    
    public int size(){
        return tecnologias.size();
    }
    
    public Tecnologia getTecnologia(int t){
        return tecnologias.get(t);
    }

    public Tecnologia buscarTecnologiaPorId(long id) {
        for (Tecnologia t : tecnologias) {
            if (t.getId() == id) {
                return t;
            }
        }

        throw new IllegalArgumentException(
                "ERRO: Não existe tecnologia com o ID " + id + ". Verifique o arquivo VENDASENTRADA.CSV."
        );
    }

    public Tecnologia getTecnologiaByName(String nome) {
        for (int i = 0; i < tecnologias.size(); i++) {
            if (tecnologias.get(i).getDescricao().equals(nome)) 
                return tecnologias.get(i);
        }
        return null;
    }

    public Tecnologia getTecnologiaComMaiorValor () {
        if (tecnologias.isEmpty()) {
            return null; 
        }

        Tecnologia tecnologiaComMaiorValor = tecnologias.get(0);

        for (Tecnologia tecnologia : tecnologias) {
            if (tecnologia.getValorBase() > tecnologiaComMaiorValor.getValorBase()) {
                tecnologiaComMaiorValor = tecnologia;
            }
        }

        return tecnologiaComMaiorValor;
    }
    
    @Override
    public String toString() {
        return "Tecnologias{" + "tecnologias=" + tecnologias + '}';
    }
}
