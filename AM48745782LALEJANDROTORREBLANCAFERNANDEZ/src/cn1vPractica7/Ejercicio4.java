/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn1vPractica7;

import ORG.netlib.math.complex.Complex;
import auxiliar.Polinomio;

/**
 *
 * @author fenix
 */
public class Ejercicio4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Polinomio.ErrorPolinomios {
        // TODO code application logic here
        double[] coefP0={-2,-5,7,-4,1};
        Polinomio p=new Polinomio(coefP0);
        double prec=0.5E-3;
        int nmax=100;
        int nint=200;
        Complex[] raices=p.buscaRaicesAleatorioPCR(prec, nmax, nint);
        for (int i = 0; i < raices.length; i++) {
            System.out.println("raiz["+i+"]= " + raices[i]);
            
        }
        
        double[] coefP1={3*Math.PI,-1,12,-12,0,-3,0,-1,25,-3,-12,1};
        Polinomio q=new Polinomio(coefP1);
        Complex[] raices2=q.buscaRaicesAleatorioPCR(prec, nmax, nint);
        for (int i = 0; i < raices2.length; i++) {
            System.out.println("raiz2["+i+"]= " + raices2[i]);
            
        }
        
    }
    
}
