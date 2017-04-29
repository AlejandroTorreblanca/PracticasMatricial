/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anmPractica5;

import auxiliar.Matrices;

/**
 *
 * @author Alejandro
 */
public class ejercicio2 {

    public static void main(String[] args) throws Matrices.ErrorMatrices {
        double[][] A={
                    {0.2, 0.1, 1, 1, 0}, 
                    {0.1,4,-1,1,-1},
                    {1,-1,60,0,-2},
                    {1,1,0,8,4},
                    {0,-1,-2, 4, 700}};
        double[] b={1,2,3,4,5};
        double[] diagC=new double[A.length];
        for (int i = 0; i < diagC.length; i++) {
            diagC[i]=Math.sqrt(A[i][i]);
        }
        Matrices.Diagonal C=new Matrices.Diagonal(diagC);
        double tol=1E-3;
        double[] x=Matrices.gradienteConjugadoPrecondicionado(A, C, b, new double[A.length], tol);
        Matrices.Matriz nA=new Matrices.Matriz(A);
        x=Matrices.gradienteConjugado(nA, b,  new double[A.length], tol);
        
        
        
        }
  
    
}
