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
    
    Token t;
    
    public Num(String nome) {
        super(nome);
        System.out.print("Criando no do tipo Num");
    }

    public Token getToken() {
        return t;
    }

    public void setToken(Token t) {
        this.t = t;
    }
    
}

