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
            try {
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
            } catch (InputMismatchException ex) {
                System.out.println("\nColoque apenas dígitos...");
                opcao = 100;
            } catch (Exception ex) {
                System.out.println("\nHouve algum erro inesperado...");
                opcao = 100;
            }
        } while (opcao != ClienteMenu.OP_VOLTAR);
    }

    public void cadastrarCliente() {
        String rg = Console.scanString("RG: ");
        if (listaClientes.clienteExiste(rg)) {
            System.out.println("\nRG já existente no cadastro");
        } else if (rg.replace(" ", "").isEmpty() /*testando se é vazio*/) {
            System.out.println("\nErro: RG VAZIO");            
        } else if (rg.matches("\\d{10,10}")) {
            String nome = Console.scanString("Escreva seu nome completo: ");
            if (nome.matches("\\s*")) {//testar com \s para ver se é vazio
                System.out.println(" \nErro: NOME VAZIO");                
            } else if (nome.matches("[A-Za-z]+(\\s[A-Za-z]+)*")) {
                String telefone = Console.scanString("Telefone para Contato: (xxxx-xxxx 4 números separado por hífem)");
                if (!telefone.matches("\\d{4,4}-\\d{4,4}")) {
                    System.out.println("\nErro: Telefone digitado diferente do formato indicado!!!");                    
                } else {
                    listaClientes.addClientes(new Cliente(nome, rg, telefone));
                    System.out.println("\nCliente " + nome + " \ncadastrado com sucesso!\n");
                }
            }else {
                System.out.println("\nErro: O nome não pode conter números, caracteres especiais, ou começa em espaço ou ter mais de um espaço");              
            }
        } else {
            System.out.println("\nErro:O RG não pode conter letras, espaços e no máximo 10 dígitos");            
        }
    }

    public void mostrarClientes() {
        System.out.println("-----------------------------\n");
        System.out.println(String.format("%-40s", "NOME") + "\t"
                + String.format("%-20s", "|RG") + "\t"
                + String.format("%-20s", "|TELEFONE"));
        for (Cliente Cliente : listaClientes.getListaClientes()) {
            System.out.println(String.format("%-40s", Cliente.getNome()) + "\t"
                    + String.format("%-20s", "|" + Cliente.getRG()) + "\t"
                    + String.format("%-20s", "|" + Cliente.getTelefone()));
        }
    }
}
