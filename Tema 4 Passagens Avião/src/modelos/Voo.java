
package modelos;

import java.util.Date;
import util.DateUtil;

public class Voo {
    private static int codigo_Gerado = 1;  
    private int codigo;
    private String origem, destino;
    private Date horarioDoVoo;
    private Aviao Aviao;

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Date getHorarioDoVoo() {
        return horarioDoVoo;
    }

    public void setHorarioDoVoo(Date horarioDoVoo) {
        this.horarioDoVoo = horarioDoVoo;
    }

    public Aviao getAviao() {
        return Aviao;
    }

    public void setAviao(Aviao Aviao) {
        this.Aviao = Aviao;
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

    public Voo(String origem, String destino, Date horarioDoVoo, Aviao Aviao) {
        this.codigo = codigo_Gerado;
        codigo_Gerado++;
        this.origem = origem;
        this.destino = destino;
        this.horarioDoVoo = horarioDoVoo;
        this.Aviao = Aviao;
    }
    
    @Override
    public String toString(){
        String horario = DateUtil.dateHourToString(horarioDoVoo);
        String voo = "Vôo de código: "+this.codigo + 
                "\n Origem: "+this.origem +
                "\n Destino: "+this.destino +
                "\n Data/Hora de Partida: "+horario +
                "\n No Avião de nome: "+this.Aviao.getNome();
        return voo;
    }
    
    
    
}
