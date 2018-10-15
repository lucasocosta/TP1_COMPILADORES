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
import io.*;
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
        String nomeArquivo;
        //nomeArquivo="teste.txt";
        nomeArquivo=args[0];
        
        AnalisadorLexico lexico = new AnalisadorLexico(nomeArquivo);
        AnalisadorSintatico sintatico = new AnalisadorSintatico(lexico.getTokens());
        //io saida=new io();
        //saida.tabSimbolos("tabSim.txt", sintatico.getTabelaSimbolos());
        
    }
    
}
