
package modelos;

import java.util.Objects;

public class Cliente {
    private String nome, RG, telefone;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRG() {
        return RG;
    }

    public void setRG(String RG) {
        this.RG = RG;
    }

    public String getTelefone() {
        return telefone;
    }
   
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Cliente(String nome, String RG, String telefone) {
        this.nome = nome;
        this.RG = RG;
        this.telefone = telefone;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.RG, other.RG)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString(){
        String cliente = "Nome: "+this.nome+"RG: "+this.RG;
        return cliente;        
    }
}
