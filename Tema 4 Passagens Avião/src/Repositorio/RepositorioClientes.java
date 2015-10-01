
package Repositorio;

import java.util.List;
import java.util.ArrayList;
import modelos.Cliente;

public class RepositorioClientes {    
    private List<Cliente> clientes;
    
    public RepositorioClientes(){
        clientes = new ArrayList<Cliente>();
    }

    public List<Cliente> getListaClientes() {
        return clientes;
    }
    
    public boolean addClientes(Cliente cliente) {
        return (clientes.add(cliente));
    }
    
     public boolean clienteExiste(String rg) {
        for (Cliente cliente : clientes) {
            if (cliente.getRG().equals(rg)) {
                return true;
            }
        }
        return false;
    }
     
     public Cliente devolveCliente(String RG){
         for (Cliente cliente : clientes) {
             if(cliente.getRG().equalsIgnoreCase(RG)){
                 return cliente;
             }
         }
         return null;
     }
     
     public Cliente devolveClientePorNome(String nome){
         for (Cliente cliente : clientes) {
             if(cliente.getNome().equalsIgnoreCase(nome)){
                 return cliente;
             }             
         }
         return null;
     }
}
