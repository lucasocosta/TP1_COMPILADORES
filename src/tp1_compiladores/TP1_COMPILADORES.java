/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1_compiladores;
import analisadorLexico.*;
import analisadorSintatico.AnalisadorSintatico;
import java.io.IOException;
import java.util.List;
import io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
        AnalisadorLexico lexico = new AnalisadorLexico("teste.txt");
        List<Token> tokens=new ArrayList<>();
        tokens.addAll(lexico.getTokens());
        AnalisadorSintatico sintatico = new AnalisadorSintatico(tokens);
    }
    
}
