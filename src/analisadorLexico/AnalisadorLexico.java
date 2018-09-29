/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisadorLexico;

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
public class AnalisadorLexico {
    private String arquivo;
    private Map<String,String> palavras_reservadas;
    private List<Token> tokens;
    private int estado;
    private int linha;
    private String lexema;

    public AnalisadorLexico(String arquivo) throws IOException
    {
        this.arquivo=arquivo;
        io entrada = new io(arquivo);
        palavras_reservadas = new HashMap<>();
        carregar_palavras();
        tokens = new ArrayList<>();
        estado=0;
        linha=0;
        lexema="";
        
        while(true)
        {
            if(entrada.bufferVazio())
            {
                entrada.refresh();
                //System.out.println("buffer: "+entrada.getBuffer());
                if(entrada.bufferVazio())
                {
                    break;
                }
            }
            //System.out.println(""+entrada.getNextChar());
            char c=entrada.getNextChar();
            
            computaChar(c);
        }
        for(int i=0;i<tokens.size();i++)
        {
            System.out.print(tokens.get(i).getNome()+"linha: "+tokens.get(i).getLinha()+"\n");
        }
        entrada.fechar_arquivo();
        
    }
    
    private void computaChar(char c)
    {
        
        switch(estado)
        {
            case 0:
                if(c=='\n')
                {
                    linha++;
                    estado=0;
                }
                else if(c==' ')
                {
                    
                }
                else if(Character.isAlphabetic(c))
                {
                    //System.out.print(" é alpha\n");
                }
                else if(Character.isDigit(c))
                {
                    //System.out.print(" é digito\n");
                }
                else
                {
                    lexema=""+c;
                    Token t = new Token(palavras_reservadas.get(lexema),lexema,linha);
                    tokens.add(t);
                    lexema="";
                }
                break;
            case 1:
                if(Character.isAlphabetic(c))
                {
                    estado=1;
                    lexema+=c;
                }
                else if(Character.isDigit(c))
                {
                    estado=1;
                    lexema+=c;
                }
                else
                {
                    estado=0;
                    lexema=""+c;
                }
                break;
         }
    }

    public String getArquivo() {
        return arquivo;
    }

    public Map<String, String> getPalavras_reservadas() {
        return palavras_reservadas;
    }

    public List<Token> getTokens() {
        return tokens;
    }
    private void carregar_palavras()
    {
        
        palavras_reservadas.put("main","MAIN");
        palavras_reservadas.put("float","FLOAT");
        palavras_reservadas.put("int","INT");
        palavras_reservadas.put("if","IF");
        palavras_reservadas.put("else","ELSE");
        palavras_reservadas.put("while","WHILE");
        palavras_reservadas.put("for","FOR");
        palavras_reservadas.put("read","READ");
        palavras_reservadas.put("print","PRINT");
        palavras_reservadas.put("(","LBRACKET");
        palavras_reservadas.put(")","RBRACKET");
        palavras_reservadas.put("{","LBRACE");
        palavras_reservadas.put("}","RBRACE");
        palavras_reservadas.put(",","COMMA");
        palavras_reservadas.put(";","PCOMMA");
        palavras_reservadas.put("=","ATTR");
        palavras_reservadas.put("<","LT");
        palavras_reservadas.put("<=","LE");
        palavras_reservadas.put(">","GT");
        palavras_reservadas.put(">=","GE");
        palavras_reservadas.put("+","PLUS");
        palavras_reservadas.put("-","MINUS");
        palavras_reservadas.put("*","MULT");
        palavras_reservadas.put("/","DIV");
    }
    
}
