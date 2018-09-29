/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1_compiladores;
import analisadorLexico.*;
import java.io.IOException;
/**
 *
 * @author lucas
 */
public class TP1_COMPILADORES {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        AnalisadorLexico teste = new AnalisadorLexico("/home/lucas/teste.txt");
    }
    
}
