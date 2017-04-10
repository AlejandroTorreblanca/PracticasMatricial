/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn1vPractica6;

import EjemplosUnidad3.*;
import auxiliar.*;

/**
 *
 * @author antoniopallaresruiz
 */
public class Ejecicio3a {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SINC sinc = new SINC();

        int N = 10;
        double a = 0, b = Math.PI;

        double SinIntPI = 1.851937051982466170361053370157991363345;

        for (int k = 1; k <= N; k++) {

            double h = (b - a) / k;

            double intTrap = MetodosFunciones.integralTrapecio(sinc, a, b, k);

            double intSimp = MetodosFunciones.integralSimpson(sinc, a, b, k);

            double intGauss = MetodosFunciones.integralGauss(sinc, a, b, k);

            double intRombergAux1 = MetodosFunciones.integralTrapecio(sinc, a, b, 2 * k);
            double intRomberg1 = (4 * intRombergAux1 - intTrap) / (4 - 1);
            System.out.println("Romberg  1 = " + intRomberg1);
            double intRombergAux2 = MetodosFunciones.integralTrapecio(sinc, a, b, 4 * k);
            double intRomberg1b = (4 * intRombergAux2 - intRombergAux1) / (4 - 1);
            double intRomberg2 = (16 * intRomberg1b - intRomberg1) / (16 - 1);
            System.out.println("Romberg  2 = " + intRomberg2);

            System.out.println("\n Aproximaciones del SenoIntegral(PI) = Int_0^{PI} sen(x)/x dx ");

            System.out.println("paso = " + h);

            System.out.println("Trapecio compuesto con " + k + " divisiones");

            System.out.println("intTrapecio = " + intTrap
                    + "\t error = " + (SinIntPI - intTrap));

            System.out.println("Simpson compuesto con " + k + " divisiones");
            System.out.println("intSimpson = " + intSimp
                    + "\t error = " + (SinIntPI - intSimp));

            System.out.println("Gauss compuesto con " + k + " divisiones");
            System.out.println("intGauss = " + intGauss
                    + "\t error = " + (SinIntPI - intGauss));

            System.out.println("\nSinInt(PI) = " + SinIntPI);

        }

        /* double MonteCarlo=MetodosFunciones.intMonteCarlo1(sinc, a, b, 1000000);
        System.out.println("Aproximacion Monte-Carlo con  1000 evaluaciones " );
        
        System.out.println("MonteCarlo =" + MonteCarlo);
         */
        double intadap = MetodosFunciones.integralAdaptada(sinc, 0, Math.PI, .1E-10, 100);
        System.out.println("Integral adaptada entre 0 y Pi =  " + intadap);

        D2SINC g = new D2SINC();
        double intadap2 = MetodosFunciones.integralAdaptada(g, 0, 0.1/Math.PI, .1E-10, 100);
        
        double integral = intadap - 1 / Math.PI - 2 * intadap2;
         System.out.println(""+integral);
         System.out.println(""+Math.PI/2);

    }

    public static class SINC implements Funcion {

        public SINC() {
        }

        public double eval(double x) {
            if (x == 0) {
                return 1;
            }
            return Math.sin(x) / x;
        }
    }

    public static class D2SINC implements Funcion {

        public D2SINC() {
        }

        public double eval(double x) {
            if (x == 0) {
                return 0;
            }
            return x * (Math.sin(1 / x));
        }
    }

}
