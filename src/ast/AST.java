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
public class AST {
    
    No raiz;
    
    public AST()
    {
        
    }
    
    public void setRaiz(No root)
    {
        raiz=root;
    }
    
    public void imprime()
    {
        //System.out.print("<Astnode>\n");
        
        raiz.print(0);
        
        raiz.geraPython(0);
        
        //System.out.print("<\\Astnode>\n");
    }
    
}
