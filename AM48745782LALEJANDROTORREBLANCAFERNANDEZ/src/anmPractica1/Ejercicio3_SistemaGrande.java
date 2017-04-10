/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anmPractica1;

import auxiliar.Matrices;

/**
 *
 * @author fenix
 */
public class Ejercicio3_SistemaGrande {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Matrices.ErrorMatrices {
        int n=1000000;
       double D=3;
       double[] dP=new double[n];
       double[] inf=new double[n-1];
       double[] s1=new double[n-1];
       double[] s2=new double[n-2];
       
       double[] b=new double[n];
        for (int i = 0; i < n-2; i++) {
           dP[i]=D;  
           inf[i]=-1;
           s1[i]=-1;
           s2[i]=-1;
           b[i]= 3* Math.sin(i*2*Math.PI/(n-1));
        }
        s1[n-2]=-1;
        inf[n-2]=-1;
        dP[n-2]=D;
        dP[n-1]=D;
        b[n-1]=0;
        
        
        
        
        double[] x=solveGaussSinPermutarFilas(dP, s1, s2, inf, b);
        
        System.out.println(" n= "+ n);
        
        System.out.println("|| A x -b || = "+ normaResidual(dP, s1, s2, inf, x, b));
        
    }
    public static double[] producto(double[] dP,double[] s1,double[] s2, double[] inf,double[] x){
        int n= x.length;
        double[] b=new double[n];
        b[0]=dP[0]*x[0]+s1[0]*x[1]+s2[0]*x[2];
        for (int i = 1; i < (n-2); i++) {
           b[i]= inf[i-1]*x[i-1]+dP[i]*x[i]+s1[i]*x[i+1]+s2[i]*x[i+2]; 
        }
        b[n-2]= inf[n-3]*x[n-3]+dP[n-2]*x[n-2]+s1[n-2]*x[n-1]; 
        b[n-1]= inf[n-2]*x[n-2]+dP[n-1]*x[n-1]; 
        return b;
    }
    public static double[] solveGaussSinPermutarFilas(double[] dP,double[] s1,
            double[] s2, double[] inf,double[] b)throws Matrices.ErrorMatrices{
       int n=b.length;
       double[] x=new double[n];
       double[] ndP=new double[n];
       double[] ns1=new double[n-1];
       double[] nb=new double[n];
       ndP[0]=dP[0]; ns1[0]=s1[0];nb[0]=b[0];
        for (int i = 1; i < (n-1); i++) {
            if(Math.abs(ndP[i-1])<0.5E-14){
                System.out.println("Error: matriz singular");
                throw new Matrices.ErrorMatrices();
            }
            double mult=inf[i-1]/ndP[i-1];
            ndP[i]=dP[i]-mult*ns1[i-1];
            ns1[i]=s1[i]-mult*s2[i-1];
            nb[i]=b[i]-mult*nb[i-1];
        }
        if(Math.abs(ndP[n-2])<0.5E-14){
                System.out.println("Error: matriz singular");
                throw new Matrices.ErrorMatrices();
            }
        double mult=inf[n-2]/ndP[n-2];
        ndP[n-1]=dP[n-1]-mult*ns1[n-2];
        nb[n-1]=b[n-1]-mult*nb[n-2];
       //resolvemos el sistema reducido
        x[n-1]=nb[n-1]/ndP[n-1];
        x[n-2]=(nb[n-2]-ns1[n-2]*x[n-1])/ndP[n-2];
        for (int i = n-3; i >=0; i--) {
            x[i]=(nb[i]-ns1[i]*x[i+1]-s2[i]*x[i+2])/ndP[i];
        }
       return x; 
    }
     public static double[] residual(double[] dP,double[] s1,double[] s2, 
             double[] inf,double[] x, double[] b) throws Matrices.ErrorMatrices{
         // devuelve Ax-b
         return Matrices.resta(producto(dP, s1, s2, inf, x), b);
     }
     public static double normaResidual(double[] dP,double[] s1,double[] s2, 
             double[] inf,double[] x, double[] b) throws Matrices.ErrorMatrices{
         // devuelve Ax-b
         return Matrices.norma(residual(dP, s1, s2, inf, x, b));
     }
}
