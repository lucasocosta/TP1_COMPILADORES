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
        estado=1;
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
            //System.out.print(c);
        }
        for(int i=0;i<tokens.size();i++)
        {
            System.out.print(tokens.get(i).getNome()+" "+tokens.get(i).getLexema() +" linha: "+tokens.get(i).getLinha()+"\n");
        }
        entrada.fechar_arquivo();
        
    }
    
    private void computaChar(char c)
    {
        
        switch(estado)
        {       
            case 1:
                if(Character.isAlphabetic(c))
                {
                    estado=2;
                    lexema+=c;
                }
                else if(Character.isDigit(c))
                {
                    estado=3;
                    lexema+=c;
                }
                else if(c=='<')
                {
                    estado=6;
                    lexema=""+c;
                }
                else if(c=='>')
                {
                    estado=7;
                    lexema=""+c;
                }
                else if (c=='('||c==')'||c=='{'||c=='}'||c==','||c==';'||c=='+'||c=='-'||c=='*'||c=='/'||c=='='||c=='['||c==']')
                {
                    lexema=""+c;
                    Token t = new Token(palavras_reservadas.get(lexema),lexema,linha);
                    tokens.add(t);
                    lexema="";
                }
                else if(c=='\n')
                {
                    linha++;
                    lexema="";
                }
                else if(c=='\t'||c==' ')
                {
                    lexema="";
                }

                break;
            case 2:
                if(Character.isAlphabetic(c)||Character.isDigit(c))
                {
                    estado=2;
                    lexema+=c;
                }
                else
                {
                    estado=1;
                    Token t;
                    if(palavras_reservadas.get(lexema)!=null)
                    {
                        t = new Token(palavras_reservadas.get(lexema),lexema,linha);
                    }
                    else
                    {
                        t = new Token("ID",lexema,linha);
                    }
                    lexema="";
                    tokens.add(t);
                    computaChar(c);
                }
                break;
            case 3:
                if(Character.isDigit(c))
                {
                    estado=3;
                    lexema+=c;
                }
                else if(c=='.')
                {
                    estado=4;
                    lexema+=c;
                }
                else
                {
                    estado=1;
                    Token t;
                    if(palavras_reservadas.get(lexema)!=null)
                    {
                        t = new Token(palavras_reservadas.get(lexema),lexema,linha);
                    }
                    else
                    {
                        t = new Token("INTEGER_CONST",lexema,linha);
                    }
                    lexema="";
                    tokens.add(t);
                    computaChar(c);
                }
 
                break;
            case 4:
                if(Character.isDigit(c))
                {
                    estado=5;
                    lexema+=c;
                }
                
                break;
            case 5:
                
                if(Character.isDigit(c))
                {
                    estado=5;
                    lexema+=c;
                }
                else
                {
                    estado=1;
                    Token t;
                    if(palavras_reservadas.get(lexema)!=null)
                    {
                        t = new Token(palavras_reservadas.get(lexema),lexema,linha);
                    }
                    else
                    {
                        t = new Token("INTEGER_CONST",lexema,linha);
                    }
                    lexema="";
                    tokens.add(t);
                    computaChar(c);
                }
                
                break;
            case 6:
                if(c=='=')
                {
                    estado=1;
                    lexema+=c;
                    Token t = new Token(palavras_reservadas.get(lexema),lexema,linha);
                    tokens.add(t);
                    lexema="";
                }
                else
                {
                    estado=1;
                    Token t = new Token(palavras_reservadas.get(lexema),lexema,linha);
                    lexema="";
                    tokens.add(t);
                    computaChar(c);
                }
                
                
                break;
            case 7:
                if(c=='=')
                {
                    estado=1;
                    lexema+=c;
                    Token t = new Token(palavras_reservadas.get(lexema),lexema,linha);
                    lexema="";
                    tokens.add(t);
                }
                else
                {
                    estado=1;
                    Token t = new Token(palavras_reservadas.get(lexema),lexema,linha);
                    lexema="";
                    tokens.add(t);
                    computaChar(c);
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
