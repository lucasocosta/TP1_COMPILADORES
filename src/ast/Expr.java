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
public abstract class Expr extends No{
    
    String op;
    
    public Expr(String nome) {
        super(nome);
        System.out.print("Criando no do tipo Expr\n");
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }
    
    
}
