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
        if (tokens.get(pos).getNome()==tok)
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
    private void Programa(List<Token> t)
    {
        
    }
    private void Decl_Comando (List<Token> tokens)
    {
        
    }
    private void Declaracao (List<Token> tokens)
    {
        
    }
    private void Decl2 (List<Token> tokens)
    {
        
    }
    private void Tipo (List<Token> tokens)
    {
        
    }
    private void Comando (List<Token> tokens)
    {
        
    }
    private void Bloco (List<Token> tokens)
    {
        
    }
    private void Atribuicao (List<Token> tokens)
    {
        
    }
    private void ComandoSe (List<Token> tokens)
    {
        
    }
    private void ComandoSenao (List<Token> tokens)
    {
        
    }
    private void ComandoEnquanto (List<Token> tokens)
    {
        
    }
    private void ComandoRead (List<Token> tokens)
    {
        
    }
    private void ComandoPrint (List<Token> tokens)
    {
        
    }
    private void ComandoFor (List<Token> tokens)
    {
        
    }
    private void AtribuicaoFor (List<Token> tokens)
    {
        
    }
    private void Expressao (List<Token> tokens)
    {
        
    }
    private void RelacaoOpc (List<Token> tokens)
    {
        
    }
    private void OpRel (List<Token> tokens)
    {
        
    }
    private void Adicao (List<Token> tokens)
    {
        
    }
    private void AdicaoOpc (List<Token> tokens)
    {
        
    }
    private void OpAdicao (List<Token> tokens)
    {
        
    }
    private void Termo (List<Token> tokens)
    {
        
    }
    private void TermoOpc (List<Token> tokens)
    {
        
    }
    private void OpMult (List<Token> tokens)
    {
        
    }
    private void Fator (List<Token> tokens)
    {
        
    }
}