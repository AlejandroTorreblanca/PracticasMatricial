/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package anm1entrega;

import auxiliar.Matrices;

/**
 *
 * @author MILANOP02
 */
public class Ejemplo311 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int n = 3;
        double[][] A = {{2,-2,0},{2,3,1},{-1,0,-2}};
        double[] b = {1,5,7};
        double[] xo = new double[n];

       
        //System.out.println("A = "+ Matrices.toString(A));
        //System.out.println("b = "+ Matrices.toString(b));

        double[] sol = new double[n];
        double[] error = new double[n]; 

        try {
            sol = Matrices.solveGaussP(A, b);
//             System.out.println("matriz: ;"+'\n'+Matrices.toString(A));
             System.out.println("sol Gauss a A x =b es x "+ Matrices.toString(sol) );
             error = Matrices.residual(A, sol, b);
             System.out.println("tamaño del error Gauss = " + Matrices.norma(error));
//             sol= Matrices.Cramer(A, b);
//             System.out.println("sol Cramer a A x =b es x "+ Matrices.toString(sol) );
//             error = Matrices.residual(A, sol, b);
//             System.out.println("tama~no del error Cramer = " + Matrices.norma(error));
//            
            xo[0] = 1;
            xo[1] = -1;
            xo[2] = 10;
            double tol = 0.5E-8;
            double[] xiter = Matrices.iterJacobi(A, b, tol, 200, xo, false);
            System.out.println("xJacobi = "+ Matrices.toString(xiter));
            error = Matrices.residual(A, xiter, b);
            System.out.println("tamaño del error J  = " + Matrices.norma(error));
           xiter = Matrices.iterGaussSeidel(A, b, tol, 200, xo, false);
           error = Matrices.residual(A,  xiter, b);
           System.out.println("tamaño del error GS = " + Matrices.norma(error));
        } catch (Matrices.ErrorMatrices errr) {
        }



    }


// 0.3489065502000021 , 0.39562620080000843 , 0.2335982530000315
// 0.3489065502000021 , 0.39562620080000843 , 0.2335982530000315
}
