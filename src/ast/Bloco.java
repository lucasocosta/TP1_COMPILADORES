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
public class Bloco extends No{
    
    public Bloco(String nome) {
        super(nome);
        System.out.print("Criando no do tipo Bloco\n");
    }    
    
        public void print(int level)
        {
        int i;
        String tab="";
        for(i=0;i<level;i++)
            tab=tab+"    ";
        
        //System.out.print(tab+"<"+getNome()+">\n");
    
        for(i=0;hasFilho(i);i++)
        {
            getFilho(i).print(level);
        }
        //System.out.print(tab+"<\\"+getNome()+">\n");
    }
}
