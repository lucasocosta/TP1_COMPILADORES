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
public class If extends No{
    
    public If(String nome) {
        super(nome);
        System.out.print("Criando no do tipo If\n");
    }
    
    
        public void print(int level)
    {
        int i;
        String tab="";
        for(i=0;i<level;i++)
            tab=tab+"    ";
        
        System.out.print(tab+"<"+getNome()+">\n");
    
        for(i=0;hasFilho(i);i++)
        {
            if(i==2)
            {
                System.out.print(tab+"<Else>\n");
            }
            getFilho(i).print(level+1);
            
            if(i==2)
            {
                System.out.print(tab+"<\\Else>\n");
            }
        }
        System.out.print(tab+"<\\"+getNome()+">\n");
    }
}
