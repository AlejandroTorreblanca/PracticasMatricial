/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn1vPractica8;

import ORG.netlib.math.complex.Complex;
import auxiliar.MetodosListas;
import auxiliar.Polinomio;

/**
 *
 * @author Salvador Sánchez-Pedreño
 */
public class Ejercicio1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Polinomio.ErrorPolinomios {
        // TODO code application logic here
     double a;   
     double[] raices;
     double[] tabla={-2,-5,7,-4,1};
     Polinomio pol=new Polinomio(tabla);     
     System.out.println("Método de Laguerre para el polinomio");
     System.out.println("p(x)="+pol.escribe());
     System.out.println("\n\nPunto inicial z0=-2");
     System.out.println("raiz="+pol.laguerre(new Complex(-2.), 1E-15,20));
     System.out.println("\n\nPunto inicial z0=3");
     System.out.println("raiz="+pol.laguerre(new Complex(3.), 1E-15,20));
     System.out.println("\n\nPunto inicial z0=1+i");
     System.out.println("raiz="+pol.laguerre(new Complex(1.,1.), 1E-15,20));
     
     System.out.println("Raíces reales mediante la sucesión de Sturm");
     System.out.println("\n\nIntervalos encontrados que contienen una única raíz:");
     a=pol.radioRaices();
     raices=pol.bisecSturm(1E-15, -a, a);
     System.out.println("raices="+MetodosListas.toString(raices));     
    }
    
}
