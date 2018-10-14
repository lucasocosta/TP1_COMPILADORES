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
    private Simbolo s;
    private TabelaSimbolos TabSimbolo;
    
    
    public AnalisadorSintatico(List<Token> t)
    {   
        TabSimbolo= new TabelaSimbolos();
        tokens=new ArrayList<>();
        tokens.addAll(t);
        pos=0;
        tam=tokens.size();
        System.out.print("Tamanho da lista de tokens: "+tam);
        System.out.print("\nComeço da analise Sintática\n");
        Programa();
        System.out.print("\nTabela de Simbolos\nLex\tTipo\tValor\tLinha\n");
        for(int i=0;i<TabSimbolo.tamanho();i++)
        {
            System.out.print(TabSimbolo.get(i).getLexema()+"\t"+TabSimbolo.get(i).getTipo()+"\t"+
                    TabSimbolo.get(i).getValor()+"\t"+TabSimbolo.get(i).getLinha()+"\n");
        }
        
    }
    private void Match(String tok)
    {
        if (tokens.get(pos).getNome().equals(tok))
        {
            System.out.print("Token "+tok+" reconhecido na entrada\n");
            if(pos<tokens.size()-1)
            {
                pos++;
            }
            
        }
        else
        {
            System.out.print("Token "+tok+" não esperado na entrada\n");
        }
        
    }
    private void Programa()
    {
        System.out.println("INICIOU");
        Match("INT");
        Match("MAIN");
        Match("LBRACKET");
        Match("RBRACKET");
        Match("LBRACE");
        Decl_Comando();
        Match("RBRACE");
        if (pos==tokens.size()-1) {
            //Match("EOF");
            System.out.println("Fim da análise sintática");
        }
    }
    private void Decl_Comando ()
    {
          if (tokens.get(pos).getNome().equals("INT") || tokens.get(pos).getNome().equals("FLOAT"))
          {
               Declaracao();
               Decl_Comando();
          }
          else if (tokens.get(pos).getNome().equals("LBRACE") ||
              tokens.get(pos).getNome().equals("ID") ||
              tokens.get(pos).getNome().equals("IF") ||
              tokens.get(pos).getNome().equals("WHILE") ||
              tokens.get(pos).getNome().equals("READ") ||
              tokens.get(pos).getNome().equals("PRINT"))
          {
              Comando();
              Decl_Comando();               
          }
          else {
//              vazio
          }          
    }
    private void Declaracao ()
    {
        s=new Simbolo();
        Tipo();
        s.setLexema(tokens.get(pos).getLexema());
        s.setLinha(tokens.get(pos).getLinha());
        Match("ID");
        s.setValor(null);
        Decl2();        
    }
    private void Tipo ()
    {
        if (tokens.get(pos).getNome().equals("INT")){
            s.setTipo(tokens.get(pos).getNome());
            Match("INT");
//          altera tipo para lista
        }
        else if (tokens.get(pos).getNome().equals("FLOAT")){
            s.setTipo(tokens.get(pos).getNome());
            Match("FLOAT");
//          altera tipo para lista
        }   
        
    }
    private void Decl2 ()
    {
        if (tokens.get(pos).getNome().equals("COMMA")){
            TabSimbolo.adicionaSimbolo(s);
            Match("COMMA");
            s = new Simbolo();
            s.setTipo(TabSimbolo.ultimoSimbolo().getTipo());
            s.setValor(null);
            s.setLinha(tokens.get(pos).getLinha());
            s.setLexema(tokens.get(pos).getLexema());
            Match("ID");
            Decl2();
        }
        else if (tokens.get(pos).getNome().equals("PCOMMA")){
            TabSimbolo.adicionaSimbolo(s);
            Match("PCOMMA");
        }
        else if (tokens.get(pos).getNome().equals("ATTR")) {
            Match("ATTR");
            Expressao();
            s.setValor(tokens.get(pos-1).getLexema());
            Decl2();
        }
    }
    private void Comando ()
    {
        if (tokens.get(pos).getNome().equals("LBRACE")){
            Bloco();
        }
        else if (tokens.get(pos).getNome().equals("ID")){
            Atribuicao();
        }
        else if (tokens.get(pos).getNome().equals("IF")){
            ComandoSe();
        }
        else if (tokens.get(pos).getNome().equals("WHILE")){
            ComandoEnquanto();
        }
        else if (tokens.get(pos).getNome().equals("READ")){
            ComandoRead();
        }
        else if (tokens.get(pos).getNome().equals("PRINT")){
            ComandoPrint();
        }
        else if (tokens.get(pos).getNome().equals("FOR")){
            ComandoFor();
        }  
    }
    private void Bloco ()
    {
        Match("LBRACE");
        Decl_Comando();
        Match("RBRACE");
    }
    private void Atribuicao ()
    {
        Match("ID");
        Match("ATTR");
        Expressao();
        Match("PCOMMA");
    }
    private void ComandoSe ()
    {
        Match("IF");
        Match("LBRACKET");
        Expressao();
        Match("RBRACKET");
        Comando();
        ComandoSenao();
    }
    private void ComandoSenao ()
    {
        if (tokens.get(pos).getNome().equals("ELSE")){
            Match("ELSE");
            Comando();
        }
        else {
            
        }
    }
    private void ComandoEnquanto ()
    {
        Match("WHILE");
        Match("LBRACKET");
        Expressao();
        Match("RBRACKET");
        Comando();
    }
    private void ComandoRead ()
    {
        Match("READ");
        Match("ID");
        Match("PCOMMA");
    }
    private void ComandoPrint ()
    {
        Match("PRINT");
        Match("LBRACKET");
        Expressao();
        Match("RBRACKET");
        Match("PCOMMA");
    }
    private void ComandoFor ()
    {
        Match("FOR");
        Match("LBRACKET");
        AtribuicaoFor();
        Match("PCOMMA");
        Expressao();
        Match("PCOMMA");
        AtribuicaoFor();
        Match("RBRACKET");
        Comando();
    }
    private void AtribuicaoFor ()
    {
        Match("ID");
        Match("ATTR");
        Expressao();
    }
    private void Expressao ()
    {
        Adicao();
        RelacaoOpc();
    }
    private void RelacaoOpc ()
    {
        if (tokens.get(pos).getNome().equals("LT") ||
            tokens.get(pos).getNome().equals("LE") ||
            tokens.get(pos).getNome().equals("GT") ||
            tokens.get(pos).getNome().equals("GE")){
            
            OpRel();
            Adicao();
            RelacaoOpc();
        }
        else {
      
        } 
    }
    private void OpRel ()
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
    private void Adicao ()
    {
        Termo();
        AdicaoOpc();
    }
    private void AdicaoOpc ()
    {
        if (tokens.get(pos).getNome().equals("PLUS") ||
            tokens.get(pos).getNome().equals("MINUS")){
            OpAdicao();
            Termo();
            AdicaoOpc();
        }
        else {
            
        }
    }
    private void OpAdicao ()
    {
        if (tokens.get(pos).getNome().equals("PLUS")){
            Match("PLUS");
        }
        else if (tokens.get(pos).getNome().equals("MINUS")){
            Match("MINUS");
        }        
    }
    private void Termo ()
    {
        Fator();
        TermoOpc();
    }
    private void TermoOpc ()
    {
        if (tokens.get(pos).getNome().equals("MULT") ||
            tokens.get(pos).getNome().equals("DIV")){
            OpMult();
            Fator();
            TermoOpc();
        }
        else {
            
        }
    }
    private void OpMult ()
    {
        if (tokens.get(pos).getNome().equals("MULT")){
            Match("MUlT");
        }
        else if (tokens.get(pos).getNome().equals("DIV")){
            Match("DIV");
        } 
        
    }
    private void Fator ()
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
            Expressao();
            Match("RBRACKET");
        } 
    }
}
