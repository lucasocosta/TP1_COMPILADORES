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
public class Expr extends No {

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

    public void print(int level) {
        if (getNome().equals("")) {

            int i;
            String tab = "";
            for (i = 0; i < level - 1; i++) {
                tab = tab + "    ";
            }

            //System.out.print(tab + "<" + getNome() + ">\n");
            for (i = 0; hasFilho(i); i++) {
                getFilho(i).print(level);
            }
            //System.out.print(tab + "<\\" + getNome() + ">\n");
            return;
        }
        int i;
        String tab = "";
        for (i = 0; i < level; i++) {
            tab = tab + "    ";
        }

        System.out.print(tab + "<" + getNome() + " op='" + getOp() + "'>\n");

        for (i = 0; hasFilho(i); i++) {
            getFilho(i).print(level + 1);
        }
        System.out.print(tab + "<\\" + getNome() + ">\n");
    }

    public void geraPython(int level) {

        if (getNome().equals("")) {

            int i;
            //System.out.print(tab + "<" + getNome() + ">\n");

            for (i = 0; hasFilho(i); i++) {
                getFilho(i).geraPython(0);
            }
            //System.out.print(tab + "<\\" + getNome() + ">\n");
            return;
        }
        int i;
        String tab = "";
        for (i = 0; i < level; i++) {
            tab = tab + "    ";
        }

        //System.out.print(tab+"<"+getNome()+">\n");
        getFilho(0).geraPython(0);

        System.out.print(getOp());

        getFilho(1).geraPython(0);

        //System.out.print(tab+"\n");
    }

}
