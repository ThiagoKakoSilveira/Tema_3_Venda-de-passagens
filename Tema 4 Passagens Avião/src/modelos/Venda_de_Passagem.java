
package modelos;

import java.util.Date;
import util.Console;
import util.DateUtil;

public class Venda_de_Passagem {
    
    private static int codigo_Gerado = 1;      
    private int codigo;
    private Cliente cliente;
    private Voo voo;
    private Date horaVenda;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Voo getVoo() {
        return voo;
    }

    public void setVoo(Voo voo) {
        this.voo = voo;
    }

    public Date getHoraVenda() {
        return horaVenda;
    }

    public void setHoraVenda(Date horaVenda) {
        this.horaVenda = horaVenda;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public static int getCodigo_Gerado() {
        return codigo_Gerado;
    }
    
    public Venda_de_Passagem(Cliente cliente, Voo voo, Date horaVoo){
        this.codigo = codigo_Gerado;
        codigo_Gerado++;
        this.cliente = cliente;
        this.horaVenda = horaVoo;
        this.voo = voo;
    }
    
    @Override
    public String toString() {
        String horaVendida = DateUtil.dateHourToString(horaVenda);
        String venda = "----------------------------------------/n"
                + " Código de Venda: " + this.codigo + " Vendida às " + horaVendida+"."
                + "\n Nome do Passageiro: " + cliente.getNome()
                + "\n " + voo;
        return venda;
    }
}
