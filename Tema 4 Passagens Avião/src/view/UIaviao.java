/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import modelos.Aviao;
import Repositorio.RepositorioAvioes;
import util.Console;
import Menus.AvioesMenu;
import java.util.InputMismatchException;

/**
 *
 * @author kako__000
 */
public class UIaviao {

    private RepositorioAvioes listaAvioes;

    public UIaviao(RepositorioAvioes lista) {
        this.listaAvioes = lista;
    }

    public void executar() {
        int opcao = 0;
        do {
            AvioesMenu.mostrarMenu();
            try {
                opcao = Console.scanInt("Digite sua opção:");
                switch (opcao) {
                    case AvioesMenu.OP_CADASTRAR:
                        cadastrarAvioes();
                        break;
                    case AvioesMenu.OP_LISTAR:
                        mostrarAvioes();
                        break;
                    case AvioesMenu.OP_VOLTAR:
                        System.out.println("Retornando ao menu principal..");
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
        } while (opcao != AvioesMenu.OP_VOLTAR);
    }

    private void cadastrarAvioes() {
        String nome = Console.scanString("Nome: ");
        if (listaAvioes.buscaAviaoPorNome(nome) != null) {
            System.out.println("Avião já existente no cadastro");
        } else {
            listaAvioes.addAviao(new Aviao(nome));
            System.out.println("Avião " + nome + " cadastrado com sucesso!");
        }
    }

    public void mostrarAvioes() {
        System.out.println("-----------------------------\n");
        System.out.println(String.format("%-10s", "CÓDIGO") + "\t"
                + String.format("%-20s", "|NOME"));
        for (Aviao aviao : listaAvioes.getListaAvioes()) {
            System.out.println(String.format("%-10s", aviao.getCodigo()) + "\t"
                    + String.format("%-20s", "|" + aviao.getNome()));
        }
    }
}
