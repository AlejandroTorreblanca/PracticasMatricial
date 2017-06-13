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
public class Ejercicio01 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Matrices.ErrorMatrices {
        // TODO code application logic here
        double[][] A={{2,0,0,0},{0,3,0,0},{0,0,-2,0},{0,0,0,5}};
        double[][] S={{1,-1,0,1},{1,1,-1,0},{0,1,1,1},{1,0,1,-1}};
        S=Matrices.producto(1/Math.sqrt(3), S);
        A=Matrices.producto(Matrices.traspuesta(S), Matrices.producto(A, S));
        
        double tol=.5E-7;
        int nmax=1000;
        double[] x0={1,0,0,0};
        
        double[] vectYvalPropioA=Matrices.potencia(A, tol, nmax, x0, x0);
        // X0={1,1,0,0}
        x0[1]=1;
        double mu=0;
        vectYvalPropioA=Matrices.potenciaInversaDesplazada(A, mu, tol, nmax, x0, x0);
        // X0={1,0,1,0}
        x0[1]=0;
        x0[2]=1;
        mu=2.1;
        
        vectYvalPropioA=Matrices.potenciaInversaDesplazada(A, mu, tol, nmax, x0, x0);
        
        //apartado 2
        
        double[][] B={
            {-2,0,1,1},
            {-5,-5,4,5},
            {16,14,-8,-12},
            {-19,-15,11,15}};
        
        double[] y0={1,0,0,0};
        double desp=0.5;
        double[][] Bdesp=Matrices.suma(B, Matrices.producto(desp, Matrices.Id(4)));
        double[] vectYvalPropioB=Matrices.potencia(Bdesp, tol, nmax, y0, y0);
        vectYvalPropioB[4] -=desp;
        double lambda1=vectYvalPropioB[4]; // tenemos lambda1 y v1
        double[] vectProB=new double[4];
        System.arraycopy(vectYvalPropioB, 0,  vectProB, 0, 4);
        System.out.println("lambda1 = "+ lambda1);
        System.out.println("|| B v1 - lambda1 v1 || = "+
                Matrices.normaresidualPropios(B, vectYvalPropioB));
        
        //labmda4 = -lambda1 // buscamos vector propio v4
        
        vectYvalPropioB=Matrices.potenciaInversaDesplazada(B, -lambda1, tol, nmax, y0, y0);
        
        double lambda4=vectYvalPropioB[4]; // tenemos lambda1 y v1
        vectProB=new double[4];
        System.arraycopy(vectYvalPropioB, 0,  vectProB, 0, 4);
        System.out.println("lambda4 = "+ lambda4);
        System.out.println("|| B v4 - lambda4 v4 || = "+
                Matrices.normaresidualPropios(B, vectYvalPropioB));
        
        //lamba2?
        mu=0.2;
        vectYvalPropioB=Matrices.potenciaInversaDesplazada(B, mu, tol, nmax, y0, y0);
        
        double lambda2=vectYvalPropioB[4]; // tenemos lambda1 y v1
        vectProB=new double[4];
        System.arraycopy(vectYvalPropioB, 0,  vectProB, 0, 4);
        System.out.println("lambda2 = "+ lambda2);
        System.out.println("|| B v2 - lambda2 v2 || = "+
                Matrices.normaresidualPropios(B, vectYvalPropioB));
        
    }
 
}
    

