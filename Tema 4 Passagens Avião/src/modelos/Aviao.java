
package modelos;

public class Aviao {
    private static int codigo_Gerado = 1;
    private int codigo;
    private String nome;
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Aviao(String nome) {
        this.nome = nome;
        this.codigo = gerarCodigo();
    }
    
    public int gerarCodigo(){
        return (codigo_Gerado++);
    }
    
}
