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
public class While extends No{
    
    public While(String nome) {
        super(nome);
        System.out.print("Criando no do tipo While\n");
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
        System.out.print(tab+"While (");
        
        getFilho(0).geraPython(0);
        
        System.out.print("):\n");
        
        
        getFilho(1).geraPython(level+1);
        
        System.out.print("\n");
    }
}
