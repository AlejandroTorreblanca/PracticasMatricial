/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anmPractica6;

import auxiliar.Matrices;

/**
 *
 * @author fenix
 */
public class Ejercicio2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Matrices.ErrorMatrices {
        // TODO code application logic here
        double[][] A={{17,0,0,0},{0,3,0,0},{0,0,-2,0},{0,0,0,5}};
        double[][] S={{1,-1,0,1},{1,1,-1,0},{0,1,1,1},{1,0,1,-1}};
        S=Matrices.producto(1/Math.sqrt(3), S);
        A=Matrices.producto(Matrices.traspuesta(S), Matrices.producto(A, S));
        
        double tol=.5E-7;
        int nmax=1000;
        
        double[][] vectYvaloresProp=Matrices.jacobiPropios(A, tol, nmax);
        
    }
    
}
