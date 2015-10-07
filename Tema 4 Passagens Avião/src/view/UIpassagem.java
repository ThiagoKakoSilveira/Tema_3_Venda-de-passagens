package view;

import Menus.PassagensMenu;
import Menus.MenuClienteInexistente;
import Repositorio.RepositorioPassagens;
import Repositorio.RepositorioVoos;
import Repositorio.RepositorioClientes;
import modelos.Cliente;
import modelos.Voo;
import modelos.Venda_de_Passagem;
import util.DateUtil;
import util.Console;
import java.text.ParseException;
import java.util.Date;
import java.util.InputMismatchException;

public class UIpassagem {

    RepositorioPassagens vendas;
    RepositorioClientes clientes;
    RepositorioVoos voos;

    public UIpassagem(RepositorioClientes cliente, RepositorioPassagens venda, RepositorioVoos voo) {
        this.clientes = cliente;
        this.vendas = venda;
        this.voos = voo;
    }

    public void executar() {
        int opcao = 0;
        do {
            PassagensMenu.mostrarMenu();
            try {
                opcao = Console.scanInt("Digite aqui sua opção: ");
                switch (opcao) {
                    case PassagensMenu.OP_CADASTRAR:
                        cadastrar();
                        break;
                    case PassagensMenu.OP_LISTAR:
                        listarPassagensVendidas();
                        break;
                    case PassagensMenu.OP_VOLTAR:
                        System.out.println("Retornando ao menu principal...");
                        break;
                    default:
                        System.out.println("Opção inválida...");
                }
            } catch (InputMismatchException ex) {
                System.out.println("Coloque apenas dígitos...");
                opcao = 100;
            } catch (Exception ex) {
                System.out.println("Houve algum erro inesperado...");
                opcao = 100;
            }
        } while (opcao != PassagensMenu.OP_VOLTAR);
    }

    public void cadastrar() {
        String idCliente = Console.scanString("Digite o RG do passageiro: ");
        Cliente comprador = null;
        if (clientes.clienteExiste(idCliente)) {
            comprador = clientes.devolveCliente(idCliente);
        } else {
            System.out.println("Cliente não encotrado...");
            MenuClienteInexistente.mostrarMenu();
            int opcaoClienteInexistente = Console.scanInt("Digite o número da ação desejada: ");
            switch (opcaoClienteInexistente) {
                case MenuClienteInexistente.OP_CADASTRAR:
                    new UIcliente(clientes).cadastrarCliente();
                    if (clientes.clienteExiste(Console.scanString("Digite novamente o RG do cliente recém cadastrado: "))) {
                        comprador = clientes.devolveCliente(idCliente);
                        System.out.println("Cliente Selecionado...");
                    } else {
                        System.out.println("Erro na Seleção do Cliente");
                    }
                    break;
                case MenuClienteInexistente.OP_BUSCARNOME:
                    String nome = Console.scanString("Digite o nome que deseja procurar: ");
                    comprador = clientes.devolveClientePorNome(nome);
                    break;
                default:
                    System.out.println("Opção inválida...");
            }
        }
        new UIvoo(voos).visualizarVoos();
        Voo vooSel;
        int idVoo = Console.scanInt("Digite o código do voo: ");
        if (voos.vooExiste(idVoo)) {
            vooSel = voos.buscaVoo(idVoo);
            Date atual = new Date();
            vendas.addVendaPassagem(new Venda_de_Passagem(comprador, vooSel, atual));
            System.out.println("Venda cadastrada com sucesso...");
        } else {
            System.out.println("Voo não encontrado!!!");
        }
    }

    public void listarPassagensVendidas() {
        for (Venda_de_Passagem vp : vendas.getListaPassagens()) {
            System.out.println("------------------");
            System.out.println(vp);
            System.out.println("------------------");
        }

    }
}
