/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisadorLexico;

/**
 *
 * @author lucas
 */
public class Token {
    private String nome;
    private String lexema;
    private int linha;
    
    public Token(String nome, String lexema, int linha)
    {
        this.nome=nome;
        this.lexema=lexema;
        this.linha=linha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }
}
