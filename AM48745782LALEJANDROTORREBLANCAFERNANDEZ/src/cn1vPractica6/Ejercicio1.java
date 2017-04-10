/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn1vPractica6;

import ORG.netlib.math.complex.Complex;
import auxiliar.Polinomio;

/**
 *
 * @author Alvaro
 */
public class Ejercicio1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        double[] coe = {-2, -5, 7, -4, 1};
        Polinomio p = new Polinomio(coe);
        double x=3;
        int n=4;
        Complex[] D = p.evalDerivadas(Complex.real(x), n);
        for (int i = 0; i <= n; i++) {
            System.out.println("derivada "+i+"ésima\t D[" + i + "]=" + D[i]);
        }
        System.out.println("Polinomio desarollado en potencias de (x-"+x+") ");
        System.out.print("p(x) = "+ D[0]+" ");
        Complex[] C = new Complex[D.length];
        for (int i = 0; i <= n; i++) {
            C[i]= new Complex(D[i]);
        }
        for (int i = 1; i <= Math.min(n,p.grado()); i++) { 
            if(i%4==0)System.out.println("");
            for (int j = i; j <= Math.min(n, p.grado()); j++) {
                C[j]=C[j].scale(1./i);    
            } 
            System.out.print("+ "+C[i]+"(x - "+x+")^"+i);
        }
        System.out.println("");
        System.out.println("p(x) = "+ p);
    }

}
