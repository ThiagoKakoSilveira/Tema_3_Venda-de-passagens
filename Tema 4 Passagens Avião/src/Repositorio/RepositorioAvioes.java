
package Repositorio;

import java.util.ArrayList;
import java.util.List;
import modelos.Aviao;

public class RepositorioAvioes {
    private List<Aviao> listaAvioes;
    
    
    public RepositorioAvioes(){
        listaAvioes = new ArrayList<Aviao>();
    }
    
    public boolean addAviao(Aviao aviao){
        return (listaAvioes.add(aviao));
    }
    
    public List<Aviao>getListaAvioes(){
        return listaAvioes;
    }
    
    public boolean aviaoExiste(int codigo){
        for(Aviao aviao : listaAvioes){
            if(aviao.getCodigo() == codigo){
                return true;
            }
        }
        return false;
    }
    
    public Aviao buscaAviao(int codigo){
        for(Aviao aviao : listaAvioes){
            if(aviao.getCodigo() == codigo){
                return aviao;
            }
        }
        return null;
    }
    
    public Aviao buscaAviaoPorNome(String nome){
        for(Aviao aviao : listaAvioes){
            if(aviao.getNome().equals(nome)){
                return aviao;
            }
        }
        return null;
    }
}

