
package Repositorio;

import java.util.List;
import modelos.Venda_de_Passagem;
import modelos.Voo;
import java.util.ArrayList;
import java.util.Calendar;

public class RepositorioPassagens {
    private List<Venda_de_Passagem> listaPassagens;
    
    public RepositorioPassagens(){
        listaPassagens = new ArrayList<Venda_de_Passagem>();
    }
    
    public boolean addVendaPassagem(Venda_de_Passagem passagem){
        return (listaPassagens.add(passagem));
    }
    
    public List<Venda_de_Passagem>getListaPassagens(){
        return listaPassagens;
    }
    
    public Voo getVooDaPassagem(int codVoo){
        for (Venda_de_Passagem vooDaPassagem : listaPassagens){
            if(vooDaPassagem.getVoo().getCodigo() == codVoo){
                return vooDaPassagem.getVoo();
            }
        }
        return null;        
    }
    
    public boolean passagemExiste(int codigo){
        for (Venda_de_Passagem listaPassagem : listaPassagens) {
            if(listaPassagem.getCodigo()==codigo){
                return true;
            }
        }
        return false;
    }
    
    public Venda_de_Passagem buscaPassagem(int codigo){
        for (Venda_de_Passagem listaPassagem : listaPassagens) {
            if(listaPassagem.getCodigo()==codigo){
                return listaPassagem;
            }            
        }
        return null;
    }
    
    public Venda_de_Passagem buscaPassagemPorCliente(String RG){
        for (Venda_de_Passagem listaPassagem : listaPassagens) {
            if(listaPassagem.getCliente().getRG().equalsIgnoreCase(RG)){
                return listaPassagem;
            }            
        }
        return null;
    }
    
    public List<Venda_de_Passagem>getListaPassagemPorMês(int mes, int ano){
        List<Venda_de_Passagem> passagemMes = new ArrayList<Venda_de_Passagem>();
        Calendar calendario = Calendar.getInstance();
        
        for (Venda_de_Passagem pass : listaPassagens) {
            calendario.setTime(pass.getHoraVenda());
            if(calendario.get(Calendar.MONTH)==(mes-1) && calendario.get(Calendar.YEAR)==(ano)){
                passagemMes.add(pass);
            }            
        }
        return passagemMes;
    }
}
