/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menus;

/**
 *
 * @author kako__000
 */
public class VoosMenu {
    public static final int OP_CADASTRAR = 1;
    public static final int OP_LISTAR = 2;
    public static final int OP_VOLTAR = 0;
    
    public static void mostrarMenu(){
        System.out.println("\n--------------------------------------\n"
                + "1- Cadastrar um Vôo\n"
                + "2- Listar Vôos\n"
                + "0- Voltar"
                + "\n--------------------------------------");
    }
}
