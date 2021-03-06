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
import ast.*;
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
    private AST ast;

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getTam() {
        return tam;
    }

    public void setTam(int tam) {
        this.tam = tam;
    }

    public Simbolo getS() {
        return s;
    }

    public void setS(Simbolo s) {
        this.s = s;
    }

    public TabelaSimbolos getTabSimbolo() {
        return TabSimbolo;
    }

    public void setTabSimbolo(TabelaSimbolos TabSimbolo) {
        this.TabSimbolo = TabSimbolo;
    }

    public AST getAst() {
        return ast;
    }

    public void setAst(AST ast) {
        this.ast = ast;
    }
    
    
    public AnalisadorSintatico(List<Token> t)
    {   
        ast = new AST();
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
        ast.imprime();
        
    }
    private void Match(String tok)
    {
        if (tokens.get(pos).getNome().equals(tok))
        {
            //System.out.print("Token "+tok+" reconhecido na entrada\n");
            if(pos<tokens.size()-1)
            {
                pos++;
            }
            
        }
        else
        {
            //System.out.print("Token "+tok+" não esperado na entrada\n");
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
        Bloco main = new Bloco("AST");
        ast.setRaiz(main);
        Decl_Comando(main);
        Match("RBRACE");
        if (pos==tokens.size()-1) {
            //Match("EOF");
            System.out.println("Fim da análise sintática");
        }
    }
    private void Decl_Comando (No no)
    {
          System.out.print("Decl_comando recebeu "+no.getNome()+"\n");
          if (tokens.get(pos).getNome().equals("INT") || tokens.get(pos).getNome().equals("FLOAT"))
          {
               Declaracao(no);
               
               Decl_Comando(no);
          }
          else if (tokens.get(pos).getNome().equals("LBRACE") ||
              tokens.get(pos).getNome().equals("ID") ||
              tokens.get(pos).getNome().equals("IF") ||
              tokens.get(pos).getNome().equals("WHILE") ||
              tokens.get(pos).getNome().equals("READ") ||
              tokens.get(pos).getNome().equals("PRINT")||
              tokens.get(pos).getNome().equals("FOR"))
          {
              Comando(no);
              Decl_Comando(no);               
          }
          else {
//              vazio
          }          
    }
    private void Declaracao (No no)
    {
        s=new Simbolo();
        Tipo();
        s.setLexema(tokens.get(pos).getLexema());
        s.setLinha(tokens.get(pos).getLinha());
        Match("ID");
        s.setValor(null);
        Decl2(no);        
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
    private void Decl2 (No no)
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
            Decl2(no);
        }
        else if (tokens.get(pos).getNome().equals("PCOMMA")){
            TabSimbolo.adicionaSimbolo(s);
            Match("PCOMMA");
        }
        else if (tokens.get(pos).getNome().equals("ATTR")) {
            Attr at=new Attr("Attr");
            no.addFilho(at);
            Id id=new Id("ID");
            id.setLexema(tokens.get(pos-1).getLexema());
            at.addFilho(id);
            Match("ATTR");
            Expressao(at);
            s.setValor(tokens.get(pos-1).getLexema());
            Decl2(no);
        }
    }
    private void Comando (No no)
    {
        if (tokens.get(pos).getNome().equals("LBRACE")){
            Bloco(no);
        }
        else if (tokens.get(pos).getNome().equals("ID")){
            Atribuicao(no);
        }
        else if (tokens.get(pos).getNome().equals("IF")){
            ComandoSe(no);
        }
        else if (tokens.get(pos).getNome().equals("WHILE")){
            ComandoEnquanto(no);
        }
        else if (tokens.get(pos).getNome().equals("READ")){
            ComandoRead(no);
        }
        else if (tokens.get(pos).getNome().equals("PRINT")){
            ComandoPrint(no);
        }
        else if (tokens.get(pos).getNome().equals("FOR")){
            ComandoFor(no);
        }  
    }
    private void Bloco (No no)
    {
        Match("LBRACE");
        Bloco b = new Bloco("Bloco");
        Decl_Comando(b);
        Match("RBRACE");
        no.addFilho(b);
    }
    private void Atribuicao (No no)
    {
        Attr at = new Attr("Attr");
        
        Match("ID");
        Id id=new Id("ID");
        id.setLexema(tokens.get(pos-1).getLexema());
        at.addFilho(id);
        no.addFilho(at);
        Match("ATTR");
        Expressao(at);
        Match("PCOMMA");
    }
    private void ComandoSe (No no)
    {
        If if_no = new If("If");
        Match("IF");
        Match("LBRACKET");
        Expressao(if_no);
        
        Match("RBRACKET");
        Comando(if_no);
        ComandoSenao(if_no);
        no.addFilho(if_no);
    }
    private void ComandoSenao (No no)
    {
        if (tokens.get(pos).getNome().equals("ELSE")){
            Match("ELSE");
            Comando(no);
        }
        else {
            
        }
    }
    private void ComandoEnquanto (No no)
    {
        Match("WHILE");
        While p = new While("While");
        Match("LBRACKET");
        Expressao(p);
        Match("RBRACKET");
        Comando(p);
        no.addFilho(p);
    }
    private void ComandoRead (No no)
    {
        Match("READ");
        Read p = new Read("Read");
        Id id = new Id("Id");
        id.setLexema(tokens.get(pos).getLexema());
        p.addFilho(id);
        Match("ID");
        
        Match("PCOMMA");
        no.addFilho(p);
    }
    private void ComandoPrint (No no)
    {
        Match("PRINT");
        Print p = new Print("Print");
        Match("LBRACKET");
        Expressao(p);
        Match("RBRACKET");
        Match("PCOMMA");
        no.addFilho(p);
    }
    private void ComandoFor (No no)
    {
        Match("FOR");
        For f = new For("For");
        Match("LBRACKET");
        AtribuicaoFor(f);
        Match("PCOMMA");
        Expressao(f);
        Match("PCOMMA");
        AtribuicaoFor(f);
        Match("RBRACKET");
        Comando(f);
        no.addFilho(f);
    }
    private void AtribuicaoFor (No no)
    {
        Attr at=new Attr("Attr");
        no.addFilho(at);
        Id id=new Id("ID");
        Match("ID");
        id.setLexema(tokens.get(pos-1).getLexema());
        at.addFilho(id);
        Match("ATTR");
        Expressao(at);
        s.setValor(tokens.get(pos-1).getLexema());
    }
    private void Expressao (No no)
    {
        //criar uma variavel para receber o retorno de adicao
        Expr p = new Expr("");
        Adicao(p);
        
        RelacaoOpc(p); //passa o retorno
        no.addFilho(p);
        //retorna a exp final
        
    }
    private void RelacaoOpc (Expr no)
    {
        if (tokens.get(pos).getNome().equals("LT") ||
            tokens.get(pos).getNome().equals("LE") ||
            tokens.get(pos).getNome().equals("GT") ||
            tokens.get(pos).getNome().equals("GE")){
            //RelOp relop = new RelOp("RelOp");
            no.setNome("RelOp");
            OpRel(no);
            Adicao(no);
            RelacaoOpc(no);
            
        }
        else {
      
        } 
    }
    private void OpRel (Expr no)
    {
        if (tokens.get(pos).getNome().equals("LT")){
            Match("LT");
            no.setOp("<");
        }
        else if (tokens.get(pos).getNome().equals("LE")){
            Match("LE");
            no.setOp("<=");
        }
        else if (tokens.get(pos).getNome().equals("GT")){
            Match("GT");
            no.setOp(">");
        }
        else if (tokens.get(pos).getNome().equals("GE")){
            Match("GE");
            no.setOp(">=");
        }
    }
    private void Adicao (Expr no)
    {
        //ArithOp ar = new ArithOp("ArithOp");
        Termo(no);
        AdicaoOpc(no);
        //no.addFilho(no);
    }
    private void AdicaoOpc (Expr no)
    {
        if (tokens.get(pos).getNome().equals("PLUS") ||
            tokens.get(pos).getNome().equals("MINUS")){
            no.setNome("ArithOp");
            OpAdicao(no);
            Termo(no);
            
            AdicaoOpc(no);
        }
        else {
            
        }
    }
    private void OpAdicao (Expr no)
    {
        if (tokens.get(pos).getNome().equals("PLUS")){
            Match("PLUS");
            no.setOp("+");
            
        }
        else if (tokens.get(pos).getNome().equals("MINUS")){
            Match("MINUS");
            no.setOp("-");
        }        
    }
    private void Termo (Expr no)
    {
        Expr exp = new Expr("");
        Fator(exp);
        TermoOpc(exp);
        no.addFilho(exp);
    }
    private void TermoOpc (Expr no)
    {
        
        
        if (tokens.get(pos).getNome().equals("MULT") ||
            tokens.get(pos).getNome().equals("DIV")){
            no.setNome("ArithOp");
            OpMult(no);
            Fator(no);
            TermoOpc(no);
        }
        else {
           
        }
    }
    private void OpMult (Expr no)
    {
        if (tokens.get(pos).getNome().equals("MULT")){
            Match("MULT");
            no.setOp("*");
        }
        else if (tokens.get(pos).getNome().equals("DIV")){
            Match("DIV");
            no.setOp("/");
        } 
        
    }
    private void Fator (No no)
    {
        if (tokens.get(pos).getNome().equals("ID")){
            Id id=new Id("ID");
            id.setLexema(tokens.get(pos).getLexema());
            no.addFilho(id);
            Match("ID");
        }
        else if (tokens.get(pos).getNome().equals("INTEGER_CONST")){
            Num num = new Num("Num");
            num.setValor(tokens.get(pos).getLexema());
            no.addFilho(num);
            Match("INTEGER_CONST");
        } 
        else if (tokens.get(pos).getNome().equals("FLOAT_CONST")){
            Num num = new Num("Num");
            num.setValor(tokens.get(pos).getLexema());
            no.addFilho(num);
            Match("FLOAT_CONST");
        }
        else if (tokens.get(pos).getNome().equals("LBRACKET")){
            Match("LBRACKET");
            no.setHasParenteses(true);
            Expressao(no);
            Match("RBRACKET");
        } 
    }
    public TabelaSimbolos getTabelaSimbolos()
    {
        return TabSimbolo;
    }
}
