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
public class AFD {
    private int estado;
    private String lexema;

    public int getEstado() {
        return estado;
    }

    public String getLexema() {
        return lexema;
    }
    
    public void AFD()
    {
        estado=0;
        lexema="";
    }
    
    public void computa_char(char c)
    {
        switch(estado)
        {
            case 1:
                if(Character.isAlphabetic(c))
                {
                    estado=2;
                    lexema+=c;
                }
                else if(Character.isDigit(c))
                {
                    estado=3;
                    lexema+=c;
                }
                else
                {
                    estado=0;
                    lexema=""+c;
                }
                break;
            case 2:
                if(Character.isAlphabetic(c))
                {
                    estado=1;
                    lexema+=c;
                }
                else if(Character.isDigit(c))
                {
                    estado=1;
                    lexema+=c;
                }
                else
                {
                    estado=0;
                    lexema=""+c;
                }
                break;
        }
    }
}
