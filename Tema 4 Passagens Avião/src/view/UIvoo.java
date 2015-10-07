package view;

import java.text.ParseException;
import java.util.Date;
import modelos.Aviao;
import modelos.Voo;
import Repositorio.RepositorioAvioes;
import Repositorio.RepositorioVoos;
import util.Console;
import util.DateUtil;
import Menus.VoosMenu;
import java.util.InputMismatchException;

public class UIvoo {

    private RepositorioVoos voo;
    private RepositorioAvioes avioes;

    public UIvoo(RepositorioAvioes aviao, RepositorioVoos voo) {
        this.avioes = aviao;
        this.voo = voo;
    }

    public UIvoo(RepositorioVoos v) {
        this.voo = v;
    }

    public UIvoo() {

    }

    public enum Pontes_Aereas {

        Ponte1(1, "Porto Alegre", "São Paulo"),
        Ponte2(2, "São Paulo", "Porto Alegre"),
        Ponte3(3, "Rio de Janeiro", "Minas Gerais"),
        Ponte4(4, "Minas Gerais", "Maceió"),
        Ponte5(5, "Maceió", "Natal"),
        Ponte6(6, "Natal", "Maceió"),
        Ponte7(7, "Maceió", "Minas Gerais"),
        Ponte8(8, "Minas Gerais", "Rio de Janeiro");

        private int codigo;
        private String origem, destino;

        private Pontes_Aereas(int codigo, String origem, String destino) {
            this.codigo = codigo;
            this.origem = origem;
            this.destino = destino;
        }

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

        public int getCodigo() {
            return codigo;
        }

        public void setCodigo(int codigo) {
            this.codigo = codigo;
        }

        @Override
        public String toString() {
            String pontes = "Código: " + this.getCodigo() + "\nOrigem da rota: " + this.getOrigem() + "\nDestino da rota: " + this.getDestino();
            return pontes;
        }
    }

    public void executar() {
        int opcao = 0;
        do {
            VoosMenu.mostrarMenu();
            try{
            opcao = Console.scanInt("Digite a sua opção:");
            switch (opcao) {
                case VoosMenu.OP_CADASTRAR:
                    cadastrarVoo();
                    break;
                case VoosMenu.OP_LISTAR:
                    visualizarVoos();
                    break;
                case VoosMenu.OP_VOLTAR:
                    System.out.println("Retornando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida...");
            }
            }catch(InputMismatchException ex){
                System.out.println("Coloque apenas dígitos...");                
                opcao = 100;
            }catch(Exception ex){
                System.out.println("Houve algum erro inesperado...");
                opcao = 100;
            }
        } while (opcao != VoosMenu.OP_VOLTAR);
    }

    public void cadastrarVoo() {
        System.out.println("Selecione o Avião para realizar esse Vôo: ");
        new UIaviao(avioes).mostrarAvioes();
        int codigo = Console.scanInt("Digite o CODIGO do avião que voará:");
        if (avioes.aviaoExiste(codigo)) {
            Aviao aviao = avioes.buscaAviao(codigo);
            Date horaDoVoo = null;
            boolean dataValida;
            do {
                try {
                    String dataHora = Console.scanString("Digite a data e o horário do Vôo (dd/mm/aaaa hh:mm):");
                    horaDoVoo = DateUtil.stringToDateHour(dataHora);
                    dataValida = true;
                } catch (ParseException ex) {
                    System.out.println("Data ou hora no formato inválido!");
                    dataValida = false;
                }
            } while (!dataValida);
            mostrarPontes();
            //Pontes_Aereas ponteSelecionada = null; se for o caso colocar um while pra obrigar a seleção de uma ponte válida           
            int opPonte = Console.scanInt("Selecione o código da rota q deseja cadastrar:");
            if (ExistePonte(opPonte)) {
                Pontes_Aereas ponteSelecionada = devolvePonte(opPonte);
                String origem = ponteSelecionada.getOrigem();
                String destino = ponteSelecionada.getDestino();
                voo.addVoos(new Voo(origem, destino, horaDoVoo, aviao));
                System.out.println("Vôo cadastrado com sucesso!!!");
            } else {
                System.out.println("Não Existe uma rota com esse código!!!\n Ponte Aérea não cadastrada.");
            }
        } else {
            System.out.println("Não existe esse avião");
        }
    }

    public void mostrarPontes() {
        for (Pontes_Aereas Ponte_Sel : Pontes_Aereas.values()) {
            System.out.println("-----------------------------\n");
            System.out.println(Ponte_Sel);
            System.out.println("-----------------------------\n");
        }
    }

    public void mostrarOrigensExistentes() {
        String apresentado = "";
        for (Pontes_Aereas Ponte_Sel : Pontes_Aereas.values()) {
            apresentado += " " + Ponte_Sel.origem;
        }
        System.out.println("Origens existentes nas rotas disponíveis:\n" + apresentado);
    }

    public void mostrarDestinosExistentes() {
        String apresentado = "";
        for (Pontes_Aereas Ponte_Sel : Pontes_Aereas.values()) {
            apresentado += " " + Ponte_Sel.destino;
        }
        System.out.println("Destinos existentes nas rotas disponíveis:\n" + apresentado);
    }

    public Pontes_Aereas devolvePonte(int codigo) {
        Pontes_Aereas escolha = null;
        for (Pontes_Aereas Ponte_Sel : Pontes_Aereas.values()) {
            if (Ponte_Sel.getCodigo() == codigo) {
                return escolha = Ponte_Sel;
            }
        }
        return escolha;
    }

    public boolean ExistePonte(int codigo) {
        Pontes_Aereas existente = null;
        for (Pontes_Aereas Ponte_Existe : Pontes_Aereas.values()) {
            if (Ponte_Existe.codigo == codigo) {
                return true;
            }
        }
        return false;
    }

    public boolean ExisteDestino(String destino) {
        Pontes_Aereas existente = null;
        for (Pontes_Aereas Ponte_Existe : Pontes_Aereas.values()) {
            if (Ponte_Existe.destino.equalsIgnoreCase(destino)) {
                return true;
            }
        }
        return false;
    }

    public boolean ExisteOrigem(String origem) {
        Pontes_Aereas existente = null;
        for (Pontes_Aereas Ponte_Existe : Pontes_Aereas.values()) {
            if (Ponte_Existe.origem.equalsIgnoreCase(origem)) {
                return true;
            }
        }
        return false;
    }

    public void visualizarVoos() {
        for (Voo apresentado : voo.getListaVoos()) {
            System.out.println("------------------");
            System.out.println(apresentado);
            System.out.println("------------------\n");
        }
    }
}
