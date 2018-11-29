/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lucas
 */
abstract public class No {
    private String nome;
    private List<No> filhos;
    private String tipo;
    private String valor;
    
    public No(String nome)
    {
        this.nome=nome;
        this.tipo=null;
        this.valor=null;
        
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<No> getFilhos() {
        return filhos;
    }

    public void setFilhos(List<No> filhos) {
        this.filhos = filhos;
    }
    
    public void addFilho(No no)
    {
        filhos.add(no);
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    public No(String nome, String tipo, String valor)
    {
        this.nome=nome;
        this.tipo=tipo;
        this.valor=valor;
    }
    
}