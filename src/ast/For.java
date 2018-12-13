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
public class For extends No{
    
    public For(String nome) {
        super(nome);
        System.out.print("Criando no do tipo For\n");
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
            getFilho(i).print(level+1);
        }
        System.out.print(tab+"<\\"+getNome()+">\n");
    }
        
         public void geraPython(int level)
    {
        int i;
        String tab="";
        for(i=0;i<level;i++)
            tab=tab+"    ";
        
        //System.out.print(tab+"<"+getNome()+">\n");
        if(hasFilho(0))
            getFilho(0).geraPython(level);
        
        
        System.out.print(tab+"While (");
        if(hasFilho(1))
            getFilho(1).geraPython(0);
        
        System.out.print("):\n");
        
        if(hasFilho(2))
            getFilho(2).geraPython(level+1);
        if(hasFilho(3))
            getFilho(3).geraPython(level+1);
        
        System.out.print("\n");
           
    }
}
