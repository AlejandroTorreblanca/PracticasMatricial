/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn1vPractica3;

import auxiliar.Polinomio;

/**
 *
 * @author socrates
 */
public class Ejercicio2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        double[] xs = {1, 2, 3, 7.5};
        Polinomio[] fact = Polinomio.facLagrange(xs);

        for (int i = 0; i < fact.length; i++) {
            for (int j = 0; j < fact.length; j++) {
                System.out.println("L_" + i + "(xs[" + j + "]) "
                        + fact[i].eval(xs[j]));
            }
        }

    }

}
