package view;

import Repositorio.RepositorioPassagens;
import Menus.RelatoriosMenu;
import util.Console;
import util.DateUtil;
import java.util.Date;
import java.util.InputMismatchException;
import modelos.Venda_de_Passagem;

public class UIrelatorios {

    RepositorioPassagens vendidas;

    public UIrelatorios(RepositorioPassagens lista) {
        vendidas = lista;
    }

    public void executar() {
        int opcao = 0;
        do {
            RelatoriosMenu.mostrarMenu();
            try {
                opcao = Console.scanInt("Digite a opção: ");
                switch (opcao) {
                    case RelatoriosMenu.OP_VisualizarPorCliente:
                        visualizarPorCliente();
                        break;
                    case RelatoriosMenu.OP_VisualizarPorDestino:
                        visualizarPorDestino();
                        break;
                    case RelatoriosMenu.OP_VisualizarPorOrigem:
                        visualizarPorOrigem();
                        break;
                    case RelatoriosMenu.OP_VisualizarPorPassageiro:
                        visualizarPorPassageiro();
                        break;
                    case RelatoriosMenu.OP_VisualizarPorPeriodoDeVoo:
                        visualizarPorPeriodoDeVoo();
                        break;
                    case RelatoriosMenu.OP_Voltar:
                        System.out.println("Retornando ao Menu Principal");
                        break;
                    default:
                        System.out.println("Opção inválida..");
                }
            } catch (InputMismatchException ex) {
                System.out.println("Coloque apenas dígitos...");
                opcao = 100;
            } catch (Exception ex) {
                System.out.println("Houve algum erro inesperado...");
                opcao = 100;
            }
        } while (opcao != RelatoriosMenu.OP_Voltar);
    }

    public void visualizarPorCliente() {
        String rg = Console.scanString(" Digite o RG da pesquisa: ");
        for (Venda_de_Passagem vendas : vendidas.getListaPassagens()) {
            if (vendas.getCliente().getRG().equalsIgnoreCase(rg)) {
                System.out.println("Este cliente comprou a passagem código: " + vendas.getCodigo() + "\t comprada às: " + DateUtil.dateHourToString(vendas.getHoraVenda()) + " horas.");
            }
        }
    }

    public void visualizarPorDestino() {
        UIvoo Amostra = new UIvoo();
        Amostra.mostrarDestinosExistentes();
        String dest = Console.scanString("Copie dos destino apresentados e cole aqui: ");
        if (Amostra.ExisteDestino(dest)) {
            String apresentacao = "Códigos das passagens vendidas para esse destino:";
            String cods = "";
            //System.out.println("Códigos das passagens vendidas para esse destino: ");
            for (Venda_de_Passagem vendas : vendidas.getListaPassagens()) {
                if (vendas.getVoo().getDestino().equalsIgnoreCase(dest)) {
                    cods += " " + vendas.getCodigo();
                }
                System.out.println(apresentacao + cods);
            }
        }
    }

    public void visualizarPorOrigem() {
        UIvoo Amostra = new UIvoo();
        Amostra.mostrarOrigensExistentes();
        String origem = Console.scanString("Copie das origens apresentados e cole aqui: ");
        if (Amostra.ExisteDestino(origem)) {
            String apresentacao = "Códigos das passagens vendidas para essa origem:";
            String cods = "";
            //System.out.println("Códigos das passagens vendidas para esse destino: ");
            for (Venda_de_Passagem vendas : vendidas.getListaPassagens()) {
                if (vendas.getVoo().getOrigem().equalsIgnoreCase(origem)) {
                    cods += " " + vendas.getCodigo();
                }
            }
            System.out.println(apresentacao + cods);
        } else {
            System.out.println("Não teve Vendas dessa origem!");
        }
    }

    public void visualizarPorPassageiro() {
        String apresentacao = "Estes são os passageiros que compraram passagens: \n";
        System.out.println(apresentacao);
        for (Venda_de_Passagem venda : vendidas.getListaPassagens()) {
            System.out.println(venda.getCliente());
        }
    }

    public void visualizarPorPeriodoDeVoo() {
        int mes = Console.scanInt("Mês: ");
        int ano = Console.scanInt("Ano: ");
        for (Venda_de_Passagem vendaMensal : vendidas.getListaPassagemPorMês(mes, ano)) {
            System.out.println(vendaMensal);
        }
    }
}
