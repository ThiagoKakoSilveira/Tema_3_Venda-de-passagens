package view;

import Repositorio.RepositorioPassagens;
import Menus.RelatoriosMenu;
import util.Console;
import java.util.InputMismatchException;
import modelos.Venda_de_Passagem;

public class UIrelatorios {

    RepositorioPassagens vendidas;

    public UIrelatorios(RepositorioPassagens lista) {
        this.vendidas = lista;
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
                    case RelatoriosMenu.OP_VisualizarPorVoo:
                        visualizarPorVoo();
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
        System.out.println("\n Este cliente comprou a(s) passagem(ns):");
        for (Venda_de_Passagem vendas : vendidas.getListaPassagens()) {
            if (vendas.getCliente().getRG().equalsIgnoreCase(rg)) {
                System.out.println(vendas);
            }
        }
    }

    public void visualizarPorDestino() {
        UIvoo Amostra = new UIvoo();
        Amostra.mostrarDestinosExistentes();
        String dest = Console.scanString("Copie dos destino apresentados a cima e cole aqui: ");
        if (Amostra.ExisteDestino(dest)) {
            System.out.println("Passagens vendidas com esse destino");
            for (Venda_de_Passagem vendas : vendidas.getListaPassagens()) {
                if (vendas.getVoo().getDestino().equalsIgnoreCase(dest)) {
                    System.out.println(vendas);
                }
            }
        } else {
            System.out.println("Não teve Vendas dessa destino!");
        }
    }

    public void visualizarPorOrigem() {
        UIvoo Amostra = new UIvoo();
        Amostra.mostrarOrigensExistentes();
        String origem = Console.scanString("Copie das origens apresentadas a cima e cole aqui: ");
        if (Amostra.ExisteDestino(origem)) {
            System.out.println("Passagens vendidas com essa origem");
            for (Venda_de_Passagem vendas : vendidas.getListaPassagens()) {
                if (vendas.getVoo().getOrigem().equalsIgnoreCase(origem)) {
                    System.out.println(vendas);
                }
            }
        } else {
            System.out.println("Não teve Vendas dessa origem!");
        }
    }

    public void visualizarPorVoo() {
        int idVoo = Console.scanInt("Digite o código de voo que deseja relatório: ");
        for (Venda_de_Passagem vendida : vendidas.getListaPassagens()) {
            if (vendida.getVoo().getCodigo() == idVoo) {
                System.out.println(vendida);
            }
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
