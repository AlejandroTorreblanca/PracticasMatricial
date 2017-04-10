/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anm1entrega;

import auxiliar.Matrices;

/**
 *
 * @author Alejandro
 */
public class ejercicio1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Matrices.ErrorMatrices {

        double[][] A=auxiliar.Matrices.MatrizRango(8, 8, 8);
        double[][][][] D=auxiliar.Matrices.division4(A);
        System.out.println("A \n"+Matrices.toString(A));
        double[][] B=auxiliar.Matrices.reune4(D);
        System.out.println("B \n"+Matrices.toString(B));








    }
    
}
