/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisadorSintatico;

import analisadorLexico.Token;
import java.util.ArrayList;
import java.util.List;
import io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.lang.String;
/**
 *
 * @author thiag
 */
public class AnalisadorSintatico {
    private List<Token> tokens;
    private int pos;
    private int tam;
    
    
    public AnalisadorSintatico(List<Token> t)
    {   
        tokens=new ArrayList<>();
        tokens.addAll(t);
        pos=0;
        tam=tokens.size();
        System.out.print("tamanho: "+tam);
    }
    private void Match(String tok)
    {
        if (tokens.get(pos).getNome().equals(tok))
        {
            System.out.print("Token "+tok+" reconhecido na entrada");
            if(pos<tokens.size()-1)
            {
                pos++;
            }
            else
            {
                System.out.print("Token "+tok+" não esperado na entrada");
            }
        }
        
    }
    private void Programa(List<Token> tokens)
    {
        System.out.println("INICIOU");
        Match("INT");
        Match("MAIN");
        Match("LBRACKET");
        Match("RBRACKET");
        Match("LBRACE");
        Decl_Comando(tokens);
        Match("LBRACE");
        if (tokens.get(pos).getNome().equals("EOF")) {
            Match("EOF");
            System.out.println("Fim da análise.");
        }
    }
    private void Decl_Comando (List<Token> tokens)
    {
          if (tokens.get(pos).getNome().equals("INT") || tokens.get(pos).getNome().equals("FLOAT"))
          {
               Declaracao(tokens);
               Decl_Comando(tokens);
          }
          else if (tokens.get(pos).getNome().equals("LBRACE") ||
              tokens.get(pos).getNome().equals("ID") ||
              tokens.get(pos).getNome().equals("IF") ||
              tokens.get(pos).getNome().equals("WHILE") ||
              tokens.get(pos).getNome().equals("READ") ||
              tokens.get(pos).getNome().equals("PRINT"))
          {
              Comando(tokens);
              Decl_Comando(tokens);               
          }
          else {
//              vazio
          }          
    }
    private void Declaracao (List<Token> tokens)
    {
        Tipo(tokens);
        Match("ID");
        Decl2(tokens);        
    }
    private void Tipo (List<Token> tokens)
    {
        if (tokens.get(pos).getNome().equals("INT")){
            Match("INT");
//          altera tipo para lista
        }
        else if (tokens.get(pos).getNome().equals("FLOAT")){
            Match("FLOAT");
//          altera tipo para lista
        }   
        
    }
    private void Decl2 (List<Token> tokens)
    {
        if (tokens.get(pos).getNome().equals("COMMA")){
            Match("COMMA");
            Match("ID");
            Decl2(tokens);
        }
        else if (tokens.get(pos).getNome().equals("PCOMMA")){
            Match("PCOMMA");
        }
        else if (tokens.get(pos).getNome().equals("ATTR")) {
            Match("ATTR");
            Expressao(tokens);
            Decl2(tokens);
        }
    }
    private void Comando (List<Token> tokens)
    {
        if (tokens.get(pos).getNome().equals("LBRACE")){
            Bloco(tokens);
        }
        else if (tokens.get(pos).getNome().equals("ID")){
            Atribuicao(tokens);
        }
        else if (tokens.get(pos).getNome().equals("IF")){
            ComandoSe(tokens);
        }
        else if (tokens.get(pos).getNome().equals("WHILE")){
            ComandoEnquanto(tokens);
        }
        else if (tokens.get(pos).getNome().equals("READ")){
            ComandoRead(tokens);
        }
        else if (tokens.get(pos).getNome().equals("PRINT")){
            ComandoPrint(tokens);
        }
        else if (tokens.get(pos).getNome().equals("FOR")){
            ComandoFor(tokens);
        }  
    }
    private void Bloco (List<Token> tokens)
    {
        Match("LBRACE");
        Decl_Comando(tokens);
        Match("RBRACE");
    }
    private void Atribuicao (List<Token> tokens)
    {
        Match("ID");
        Match("ATTR");
        Expressao(tokens);
        Match("PCOMMA");
    }
    private void ComandoSe (List<Token> tokens)
    {
        Match("ID");
        Match("LBRACKET");
        Expressao(tokens);
        Match("RBRACKET");
        Comando(tokens);
        ComandoSenao(tokens);
    }
    private void ComandoSenao (List<Token> tokens)
    {
        if (tokens.get(pos).getNome().equals("ELSE")){
            Match("ELSE");
            Comando(tokens);
        }
        else {
            
        }
    }
    private void ComandoEnquanto (List<Token> tokens)
    {
        Match("WHILE");
        Match("LBRACKET");
        Expressao(tokens);
        Match("RBRACKET");
        Comando(tokens);
    }
    private void ComandoRead (List<Token> tokens)
    {
        Match("READ");
        Match("ID");
        Match("PCOMMA");
    }
    private void ComandoPrint (List<Token> tokens)
    {
        Match("PRINT");
        Match("LBRACKET");
        Expressao(tokens);
        Match("RBRACKET");
        Match("PCOMMA");
    }
    private void ComandoFor (List<Token> tokens)
    {
        Match("FOR");
        Match("LBRACKET");
        AtribuicaoFor(tokens);
        Match("PCOMMA");
        Expressao(tokens);
        Match("PCOMMA");
        AtribuicaoFor(tokens);
        Match("RBRACKET");
        Comando(tokens);
    }
    private void AtribuicaoFor (List<Token> tokens)
    {
        Match("ID");
        Match("ATTR");
        Expressao(tokens);
    }
    private void Expressao (List<Token> tokens)
    {
        Adicao(tokens);
        RelacaoOpc(tokens);
    }
    private void RelacaoOpc (List<Token> tokens)
    {
        if (tokens.get(pos).getNome().equals("LT") ||
            tokens.get(pos).getNome().equals("LE") ||
            tokens.get(pos).getNome().equals("GT") ||
            tokens.get(pos).getNome().equals("GE")){
            
            OpRel(tokens);
            Adicao(tokens);
            RelacaoOpc(tokens);
        }
        else {
      
        } 
    }
    private void OpRel (List<Token> tokens)
    {
        if (tokens.get(pos).getNome().equals("LT")){
            Match("LT");
        }
        else if (tokens.get(pos).getNome().equals("LE")){
            Match("LE");
        }
        else if (tokens.get(pos).getNome().equals("GT")){
            Match("GT");
        }
        else if (tokens.get(pos).getNome().equals("GE")){
            Match("GE");
        }
    }
    private void Adicao (List<Token> tokens)
    {
        Termo(tokens);
        AdicaoOpc(tokens);
    }
    private void AdicaoOpc (List<Token> tokens)
    {
        if (tokens.get(pos).getNome().equals("PLUS") ||
            tokens.get(pos).getNome().equals("MINUS")){
            OpAdicao(tokens);
            Termo(tokens);
            AdicaoOpc(tokens);
        }
        else {
            
        }
    }
    private void OpAdicao (List<Token> tokens)
    {
        if (tokens.get(pos).getNome().equals("PLUS")){
            Match("PLUS");
        }
        else if (tokens.get(pos).getNome().equals("MINUS")){
            Match("MINUS");
        }        
    }
    private void Termo (List<Token> tokens)
    {
        Fator(tokens);
        TermoOpc(tokens);
    }
    private void TermoOpc (List<Token> tokens)
    {
        if (tokens.get(pos).getNome().equals("MULT") ||
            tokens.get(pos).getNome().equals("DIV")){
            OpMult(tokens);
            Fator(tokens);
            TermoOpc(tokens);
        }
        else {
            
        }
    }
    private void OpMult (List<Token> tokens)
    {
        if (tokens.get(pos).getNome().equals("MULT")){
            Match("MUlT");
        }
        else if (tokens.get(pos).getNome().equals("DIV")){
            Match("DIV");
        } 
        
    }
    private void Fator (List<Token> tokens)
    {
        if (tokens.get(pos).getNome().equals("ID")){
            Match("ID");
        }
        else if (tokens.get(pos).getNome().equals("INTEGER_CONST")){
            Match("INTEGER_CONST");
        } 
        else if (tokens.get(pos).getNome().equals("FLOAT_CONST")){
            Match("FLOAT_CONST");
        }
        else if (tokens.get(pos).getNome().equals("LBRACKET")){
            Match("LBRACKET");
            Expressao(tokens);
            Match("RBRACKET");
        } 
    }
    
    public AnalisadorSintatico(){
        Programa(tokens);
    }
}
