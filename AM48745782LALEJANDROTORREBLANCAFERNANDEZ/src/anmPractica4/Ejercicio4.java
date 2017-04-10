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
public class Ejercicio4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Matrices.ErrorMatrices {
        // A  m>n
        int m = 30;
        int n=  10;
        
        System.out.println("m = "+ m+ "  n = "+ n);
        double[][] A=new double[m][n];
        double[] b=new double[m];
        for (int i = 0; i < n; i++) {
           b[i]=Math.PI; 
           A[i][i]=2*(i+1);
            for (int j = 0; j < n; j++) {
             
               if((j==i+2)||(j==i-2)){
                   A[i][j]=0.5*(i+1);
               }
                if((j==i+4)||(j==i-4)){
                   A[i][j]=0.25*(i+1);
               }
            }    
        }
        for (int i = n; i < m; i++) {
            b[i]=Math.PI;
            for (int j = 0; j < n; j++) {
                A[i][j]=Math.random();
            }
        }
        
        
       // System.out.println("A = \n "+ Matrices.toString(A));
        
        
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
