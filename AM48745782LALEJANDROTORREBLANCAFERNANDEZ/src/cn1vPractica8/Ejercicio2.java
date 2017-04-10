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
public class Ejercicio2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Polinomio.ErrorPolinomios {
        // TODO code application logic here
     double a;
     double[] raices;
     Complex[] raicesc;
     Polinomio pol;
     Polinomio[] polis=new Polinomio[3];
     Complex[] todas;
     double prec=1E-15;
     
     
     polis[0]=new Polinomio(new double[] {-32,10,-5.6,0.,0.,1});
     polis[1]=new Polinomio(new double[] {15,-1,12,-123,0,32,0,-1,23,-1,-12,3});
     polis[2]=new Polinomio(new double[] {-1,-1,2,-13,0,32,0,-1,2,0.8,2,-0.5,0,0,5});
     
     for (int num=0;num<polis.length;num++){
     System.out.println("Polinomio 1\n p(x)="+polis[num].escribe());
     System.out.println("\nMétodo de Sturm: intervalos que contienen una única raíz real");
     a=polis[num].radioRaices();
     raices=polis[num].bisecSturm(prec, -a, a);
     System.out.println("raices="+MetodosListas.toString(raices));
     
     pol=polis[num];
     for (int i=0;i<raices.length;i++){
         pol=pol.deflacion(raices[i]);
     }
     System.out.println("\nBúsqueda aleatoria de raíces del polinomio deflacionado:\n");
     raicesc=pol.buscaRaicesAleatorioPCR(prec, 30, 100);


     // Copia de raíces al vector todas
     todas=new Complex[raices.length+raicesc.length];
     for (int j=0;j<raices.length;j++){
         todas[j]=new Complex(raices[j]);
     }
     System.arraycopy(raicesc, 0, todas, raices.length, raicesc.length);
         System.out.println("\n Lista de raices");
         for (int i = 0; i < todas.length; i++) {
             System.out.println("x["+i+"]= "+ todas[i]);;
             
         }
         System.out.println("\n");    
     
     //Reconstrucción del polinomio
     pol=Polinomio.Construido(new Complex(1.), todas);    
     System.out.println("\nPolinomio reconstruido:\n"+pol.escribe());
     
     //Nueva búsqueda: Newton sobre el polinomio original, 
     //iniciando en las raíces aproximadas del deflacionado
     System.out.println("\nNueva aproximación: Newton sobre el polinomio original");
     System.out.println("iniciando en las raíces aproximadas del deflacionado\n");
     for (int k=0;k<raicesc.length;k++){
         raicesc[k]=polis[num].newton(raicesc[k],prec,30);
     }
     System.arraycopy(raicesc, 0, todas, raices.length, raicesc.length);
     
     System.out.println("\n Lista de raices");
         for (int i = 0; i < todas.length; i++) {
             System.out.println("x["+i+"]= "+ todas[i]);;
             
         }
         System.out.println("\n");    
     
     
     pol=Polinomio.Construido(new Complex(1.), todas);
     System.out.println("\nPolinomio reconstruido:\n"+pol.escribe());
     }
    }
    
}
