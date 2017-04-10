/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anmPractica2;

import auxiliar.Matrices;

/**
 *
 * @author fenix
 */
public class Ejercicio3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Matrices.ErrorMatrices {
        //apartado 1;
        int n=5;
        double[] b={1,2,3,4,5};
        double[][] U={{1,2,1,3,2},{0,2,1,3,1},{0,0,-1,4,0},{0,0,0,6,0},{0,0,0,0,100}};
        double[] x=Matrices.solveAscendente(U, b);
        double normaResidual=Matrices.normaResidual(U, x, b);
        System.out.println("||Ux -b|| = "+ normaResidual);
        
        double[][] L=Matrices.traspuesta(U);
        x=Matrices.solveDescendente(L, b);
        normaResidual = Matrices.normaResidual(L, x, b);
        System.out.println("||Lx -b ||= "+ normaResidual);
        
        //apartado 2
        n=100;
        double[] bn=new double[n];
        double[][] Un=new double[n][n];
        for (int i = 0; i < n; i++) {
            bn[i]=i+1;
            for (int j = i; j < Un.length; j++) {
               Un[i][j]=(i+j+2)*(i+j+2);
            }
        }
        double[] xn=Matrices.solveAscendente(Un, bn);
        double normaResidual_n=Matrices.normaResidual(Un, xn, bn);
        System.out.println("||U_n xn -bn|| = "+ normaResidual_n);
        
        double[][] Ln=Matrices.traspuesta(Un);
        xn=Matrices.solveDescendente(Ln, bn);
        normaResidual_n = Matrices.normaResidual(Ln, xn, bn);
        System.out.println("||L_n xn -bn ||= "+ normaResidual_n);
        
 
        double[][] invU=Matrices.inversaU(Un);
        double control=
                Matrices.norma1(Matrices.resta(Matrices.producto(Un, invU),Matrices.Id(n)));
        System.out.println("|| U invU -Id || = "+ control);
        double[][] invL=Matrices.inversaL(Ln);
       control=
                Matrices.norma1(Matrices.resta(Matrices.producto(Ln, invL),Matrices.Id(n)));
        System.out.println("|| L invL-Id || = "+ control);
        
    }
    
    
}
