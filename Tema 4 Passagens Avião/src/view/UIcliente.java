package view;

import modelos.Cliente;
import Repositorio.RepositorioClientes;
import util.Console;
import Menus.ClienteMenu;
import java.util.InputMismatchException;

public class UIcliente {

    private RepositorioClientes listaClientes;

    public UIcliente(RepositorioClientes listaClientes) {
        this.listaClientes = listaClientes;
    }

    public void executar() {
        int opcao = 0;
        do {
            ClienteMenu.MostrarMenuCliente();
            try{
            opcao = Console.scanInt("Digite sua opção:");
            switch (opcao) {
                case ClienteMenu.OP_CADASTRAR:
                    cadastrarCliente();
                    break;
                case ClienteMenu.OP_LISTAR:
                    mostrarClientes();
                    break;
                case ClienteMenu.OP_VOLTAR:
                    System.out.println("Retornando ao menu principal..");
                    break;
                default:
                    System.out.println("Opção inválida..");
            }
            }catch(InputMismatchException ex){
                System.out.println("Coloque apenas dígitos...");                
                opcao = 100;
            }catch(Exception ex){
                System.out.println("Houve algum erro inesperado...");
                opcao = 100;
            }
        } while (opcao != ClienteMenu.OP_VOLTAR);
    }

    public void cadastrarCliente() {
        String rg = Console.scanString("RG: ");
        if (listaClientes.clienteExiste(rg)) {
            System.out.println("RG já existente no cadastro");
        } else {
            String nome = Console.scanString("Nome: ");
            String telefone = Console.scanString("Telefone para Contato: ");
            listaClientes.addClientes(new Cliente(nome, rg, telefone));
            System.out.println("Cliente " + nome + " cadastrado com sucesso!");
        }
    }

    public void mostrarClientes() {
        System.out.println("-----------------------------\n");
        System.out.println(String.format("%-10s", "NOME") + "\t"
                + String.format("%-20s", "|RG"));
        for (Cliente Cliente : listaClientes.getListaClientes()) {
            System.out.println(String.format("%-10s", Cliente.getNome()) + "\t"
                    + String.format("%-20s", "|" + Cliente.getRG()));
        }
    }
}
