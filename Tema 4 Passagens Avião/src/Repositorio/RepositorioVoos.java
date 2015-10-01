
package Repositorio;

import java.util.ArrayList;
import java.util.List;
import modelos.Voo;

public class RepositorioVoos {
    private List<Voo> listaVoos;

    public RepositorioVoos() {
        listaVoos = new ArrayList<Voo>();
    }
    
    public boolean addVoos (Voo voo){
        return (listaVoos.add(voo));
    }    
    
    public List<Voo>getListaVoos(){
        return listaVoos;
    }
    
    public boolean vooExiste(int codigo){
        for(Voo voo : listaVoos){
            if(voo.getCodigo() == codigo){
                return true;
            }
        }
        return false;
    }
    
    public Voo buscaVoo(int codigo){
        for(Voo voo : listaVoos){
            if(voo.getCodigo() == codigo){
                return voo;
            }
        }
        return null;
    }
    
    /*public Voo buscaVooPorOrigem(String origem){
        for (Voo voo : listaVoos) {
            if(voo.getOrigem().equalsIgnoreCase(origem)){
                return voo;
            }            
        }
        return null;
    } */   
    
}
