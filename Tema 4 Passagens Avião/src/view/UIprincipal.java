package view;

import Repositorio.RepositorioAvioes;
import Repositorio.RepositorioClientes;
import Repositorio.RepositorioPassagens;
import Repositorio.RepositorioVoos;
import util.Console;
import Menus.MenuPrincipal;

public class UIprincipal {

    private RepositorioAvioes listaAvioes;
    private RepositorioClientes listaClientes;
    private RepositorioPassagens listaPassagens;
    private RepositorioVoos listaVoos;

    public UIprincipal() {
        listaAvioes = new RepositorioAvioes();
        listaClientes = new RepositorioClientes();
        listaPassagens = new RepositorioPassagens();
        listaVoos = new RepositorioVoos();
    }

    public void executar() {
        int opcao = 0;
        do {
            MenuPrincipal.mostrarMenu();
            try {
                opcao = Console.scanInt("Digite sua opção:");
                switch (opcao) {
                    case MenuPrincipal.OP_Menu_Cliente:
                        new UIcliente(listaClientes).executar();
                        break;
                    case MenuPrincipal.OP_Menu_Avioes:
                        new UIaviao(listaAvioes).executar();
                        break;
                    case MenuPrincipal.OP_Menu_Voo:
                        new UIvoo(listaAvioes, listaVoos).executar();
                        break;
                    case MenuPrincipal.OP_Menu_Passagens:
                        new UIpassagem(listaClientes, listaPassagens, listaVoos).executar();
                        break;
                    case MenuPrincipal.OP_Menu_Relatorios:
                        new UIrelatorios(listaPassagens).executar();
                        break;
                    case MenuPrincipal.OP_SAIR:
                        System.out.println("Aplicação finalizada!!!");
                        break;
                    default:
                        System.out.println("Opção inválida..");
                }
            } catch (Exception ex) {
                System.out.println("Coloque apenas dígitos...");
                opcao = 100;
            }
        } while (opcao != MenuPrincipal.OP_SAIR);
    }
}
