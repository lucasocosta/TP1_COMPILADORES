/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import analisadorSintatico.TabelaSimbolos;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author lucas
 */
public class io {
    private String buffer;
    private String arquivo;
    private FileReader fr;
    private FileWriter fw;
    private BufferedReader bfr;
    private BufferedWriter bfw;
    
    public io()
    {
        
    }
    
    public io(String arquivo) throws IOException
    {
        this.arquivo=arquivo;
        this.buffer="";
        fr=new FileReader(arquivo);
        bfr = new BufferedReader(fr);
    }
    
    public void tabSimbolos(String arquivo, TabelaSimbolos tabSimbolos) throws IOException
    {
        arquivo=this.arquivo;
        fw = new FileWriter(arquivo);
        PrintWriter pw = new PrintWriter(fw);
        pw.print("\nTabela de Simbolos\nLex\tTipo\tValor\tLinha\n");
        for(int i=0;i<tabSimbolos.tamanho();i++)
        {
            pw.print(tabSimbolos.get(i).getLexema()+"\t"+tabSimbolos.get(i).getTipo()+"\t"+
                    tabSimbolos.get(i).getValor()+"\t"+tabSimbolos.get(i).getLinha()+"\n");
        }
        fw.close();
    }
    
    public void fechar_arquivo() throws IOException
    {
        bfr.close();
        fr.close();
    }

    public String getBuffer() {
        return buffer;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }
    
    public void refresh() throws FileNotFoundException, IOException
    {

        String s="";
        int i=0;
        while(i<4096)
        {
            s= bfr.readLine();
            //System.out.print(" s="+s);
            if(s!=null)
            {
                this.buffer = this.buffer+s+"\n";
            }
            else
            {
                break;
            }
            i = i +s.length();
        }

    }
    
    public boolean bufferVazio()
    {
        //System.out.print("vazio = "+this.buffer.isEmpty());
        return this.buffer.isEmpty();
    }
    
    public char getNextChar()
    {
        char valor=this.buffer.charAt(0);
        this.buffer = this.buffer.substring(1);
        return valor;
    }
}
