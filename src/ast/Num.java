/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import analisadorLexico.Token;

/**
 *
 * @author lucas
 */
public class Num extends No{
   
    String valor;
    String tipo;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public Num(String nome) {
        super(nome);
        System.out.print("Criando no do tipo Num\n");
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
        public void print(int level)
    {
        int i;
        String tab="";
        for(i=0;i<level;i++)
            tab=tab+"    ";
        
        System.out.print(tab+"<"+getNome()+" valor='"+getValor()+"'>\n");
    
        for(i=0;hasFilho(i);i++)
        {
            getFilho(i).print(level+1);
        }
        //System.out.print(tab+"<\\"+getNome()+">\n");
    }
        
    public void geraPython(int level)
    {
        int i;
        String tab="";
        for(i=0;i<level;i++)
            tab=tab+"    ";
        
        System.out.print(tab+""+getValor());
    }

}

