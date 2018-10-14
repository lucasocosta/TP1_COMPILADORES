/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisadorSintatico;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lucas
 */
public class TabelaSimbolos {
    
    private List<Simbolo> tabelaSimbolos;
    public TabelaSimbolos()
    {
        tabelaSimbolos=new ArrayList<>();
    }
    public void adicionaSimbolo(Simbolo s)
    {
        tabelaSimbolos.add(s);
    }
    public Simbolo ultimoSimbolo()
    {
        return tabelaSimbolos.get(tabelaSimbolos.size()-1);
    }
    
}
