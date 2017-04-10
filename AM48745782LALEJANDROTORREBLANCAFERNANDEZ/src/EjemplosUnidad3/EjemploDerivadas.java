/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EjemplosUnidad3;

import auxiliar.Funcion;
import auxiliar.MetodosFunciones;

/**
 *
 * @author antoniopallaresruiz
 */
public class EjemploDerivadas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        ARCT atan = new ARCT();
        D1ARCT d1atan = new D1ARCT();
        D2ARCT d2atan = new D2ARCT();

        double h = 0.1;

        double x = Math.sqrt(2); // PRUEBA CON DISTINTOS VALORES DE X   0, 0.5, 1.0, 2.5, 10
        double aprox = 0;
        double error = 10;

        System.out.println("n \t h   \t         //Derivada 2 puntos   \t //   error        \t  \t // (error/h^1)");

        for (int i = 0; i < 10; i++) {

            MetodosFunciones.derivada2puntos d1atan2ptos = new MetodosFunciones.derivada2puntos(atan, h);
            MetodosFunciones.error d12ptsError = new MetodosFunciones.error(d1atan2ptos, d1atan);
            aprox = d1atan2ptos.eval(x);
            error = d12ptsError.eval(x);
            if (i < 4) {
                System.out.println(i + "\t " + h + "\t \t // " + aprox + "\t // " + error + " \t // " + (error / (h)));
            } else {
                System.out.println(i + "\t " + h + "\t // " + aprox + "\t // " + error + " \t // " + (error / (h)));
            }
            h = h / 2;

        }

        h = 0.1;
        System.out.println("");
        System.out.println("n \t  h  \t \t //Derivada 3 puntos   \t //   error          \t // (error/h^2)");

        for (int i = 0; i < 10; i++) {
            MetodosFunciones.derivada3puntos d1atan3ptos = new MetodosFunciones.derivada3puntos(atan, h);
            MetodosFunciones.error d13ptsError = new MetodosFunciones.error(d1atan3ptos, d1atan);
            aprox = d1atan3ptos.eval(x);
            error = d13ptsError.eval(x);
            if (i < 4) {
                System.out.println(i + "\t " + h + "\t \t // " + aprox + "\t // " + error + " \t // " + (error / (h * h)));
            } else {
                System.out.println(i + "\t " + h + "\t // " + aprox + "\t // " + error + " \t // " + (error / (h * h)));
            }
            h = h / 2;

        }

         h=0.1;
        System.out.println("");
        System.out.println("n \t h  \t \t //Derivada SEGUNDA 3 puntos   //   error          // (error/h^2)");
       
        for (int i = 0; i < 10; i++) {        
        MetodosFunciones.derivadaSegunda3puntos d2atan3ptos=new MetodosFunciones.derivadaSegunda3puntos(atan, h);
        MetodosFunciones.error d13ptsError=new MetodosFunciones.error(d2atan3ptos, d2atan);        
        aprox=d2atan3ptos.eval(x);
        error= d13ptsError.eval(x);
        if(i<4){
            System.out.println( i+"\t "+ h+ "\t \t // " +aprox  +"\t // "+ error + " \t // "+ (error/ (h*h)));
        }else{
            System.out.println(i+"\t "+ h+ "\t // " +aprox  +"\t // "+ error + " \t // "+ (error/ (h*h)));
        }
        
        h=h/2;
        
        }

        h=0.1;
        System.out.println("");
        System.out.println("n \t h  \t \t //Derivada 5 puntos   //   error          // (error/h^4)");
       
        for (int i = 0; i < 13; i++) {
        MetodosFunciones.derivada5puntos d1atan5ptos=new MetodosFunciones.derivada5puntos(atan, h);
        MetodosFunciones.error d15ptsError=new MetodosFunciones.error(d1atan5ptos, d1atan);        
        aprox=d1atan5ptos.eval(x);
        error= d15ptsError.eval(x);
        if(i<4){
            System.out.println( i+"\t "+ h+ "\t \t // " +aprox  +"\t // "+ error + " \t // "+ (error/ (h*h*h*h)));
        }else{
            System.out.println( i+"\t "+ h+ "\t // " +aprox  +"\t // "+ error + " \t // "+ (error/ (h*h*h*h)));
        }
        
        h=h/2;
        
        }
        System.out.println(" QUE OCURRIO EN EL ULTIMO PASO ANTERIOR ?????");

        h=0.1;
        
        System.out.println("\nn \t h  \t //Derivada Extrapolacion k=1 (5 puntos)   //   error          // (error/h^4)");
       
        for (int i = 0; i < 10; i++) {        
        MetodosFunciones.derivadaExtrapolacion d1atanExtrapol=new MetodosFunciones.derivadaExtrapolacion(atan, h);
        MetodosFunciones.error d1ExtrError=new MetodosFunciones.error(d1atanExtrapol, d1atan);        
        aprox=d1atanExtrapol.eval(x);
        error= d1ExtrError.eval(x);
        if(i<4){
          System.out.println( i+"\t "+ h+ "\t \t // " +aprox  +"\t // "+ error + " \t // "+ (error/ (Math.pow(h, 2*2))));
        }else{
        System.out.println(i+"\t "+ h+ "\t // " +aprox  +" // "+ error + " // "+ (error/ (Math.pow(h, 2*2))));
        }
        h=h/2;
        
        }

        h = 0.1;

        System.out.println("\nn \t h  \t //Derivada Extrapolacion k=3   //   error          // (error/h^6)");

        for (int i = 0; i < 10; i++) {
            MetodosFunciones.derivadaExtrapolacion d1atanExtrapol = new MetodosFunciones.derivadaExtrapolacion(atan, h, 3, false);
            MetodosFunciones.error d1ExtrError = new MetodosFunciones.error(d1atanExtrapol, d1atan);
            aprox = d1atanExtrapol.eval(x);
            error = d1ExtrError.eval(x);
            if (i < 4) {
                System.out.println(i + "\t " + h + "\t \t  // " + aprox + "\t // " + error + " \t // " + (error / (Math.pow(h, 2 * 3))));
            } else {
                System.out.println(i + "\t " + h + "\t  // " + aprox + "\t // " + error + " \t // " + (error / (Math.pow(h, 2 * 3))));
            }
            h = h / 2;

        }
        System.out.println(" QUE OCURRIO EN LOS PASOS ANTERIORES (MENOS EL PRIMERO) ?????");

        h = 0.1;
        System.out.println("\nTabla extrapolacion para la derivada  (h = " + h + ") ");
        MetodosFunciones.derivadaExtrapolacion d1atanExtrapol1 = new MetodosFunciones.derivadaExtrapolacion(atan, h, 10, true);
        aprox = d1atanExtrapol1.eval(x);

    }

    public static class ARCT implements Funcion {

        public ARCT() {

        }

        public double eval(double x) {
            return Math.atan(x);
        }

    }

    public static class D1ARCT implements Funcion {

        public D1ARCT() {

        }

        public double eval(double x) {
            return 1 / (1 + x * x);
        }

    }

    public static class D2ARCT implements Funcion {

        public D2ARCT() {

        }

        public double eval(double x) {
            return -2 * x / (1 + 2 * x * x + x * x * x * x);
        }

    }
}
