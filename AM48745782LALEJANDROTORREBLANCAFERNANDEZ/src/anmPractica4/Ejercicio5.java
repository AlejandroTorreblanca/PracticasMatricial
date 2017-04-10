/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anmPractica4;

import auxiliar.Matrices;

/**
 *
 * @author fenix
 */
public class Ejercicio5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
            throws Matrices.ErrorMatrices {
        // TODO code application logic here
        int fila = 10;
        int colu = fila/3;
        int rang = colu;
        double[][] A = Matrices.MatrizRango(fila, colu, rang);
        //System.out.println("A= \n " + Matrices.toString(A));

        //double[][] R = Matrices.RHouseholder(A);
        //System.out.println("R= \n " + Matrices.toString(R));

        double[] b = new double[fila];
        for (int i = 0; i < fila; i++) {
            b[i] = 1;
        }
        // solve con minimos cuadrados Ax=b
        

        long time_start, time_end;
        time_start = System.currentTimeMillis();
         // llamamos a la tarea
         double[] x=Matrices.solveMinimosCuadradosQR(A, b);
         System.out.println("min{ ||Ax -b|| } = "+
                 Matrices.normaResidual(A, x, b));
         
        time_end = System.currentTimeMillis();
        System.out.println("sistema sobredeterminado con QR " +
                (time_end - time_start) + " millisegundos");

        time_start = System.currentTimeMillis();
         // llamamos a la tarea
        x=Matrices.solveMinimosCuadradosCh(A, b);
         System.out.println("min{ ||Ax -b|| } = "+
                 Matrices.normaResidual(A, x, b));
         
        time_end = System.currentTimeMillis();
        System.out.println("sistema sobredeterminado con Choleski " +
                (time_end - time_start) + " millisegundos");


    }

}
