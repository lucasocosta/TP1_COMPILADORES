/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

/**
 *
 * @author lucas
 */
public class Id extends No{
    
    String lexema;
    String tipo;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }
    
    public Id(String nome) {
        super(nome);
        System.out.print("Criando no do tipo Id\n");
    }
    
    public void print(int level)
    {
        int i;
        String tab="";
        for(i=0;i<level;i++)
            tab=tab+"    ";
        
        System.out.print(tab+"<"+getNome()+" lexema='"+getLexema()+"'>\n");
    
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
        
        System.out.print(getLexema());
    
        //System.out.print(tab+"<\\"+getNome()+">\n");
    }
    
    
    
}
