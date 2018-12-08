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

}

