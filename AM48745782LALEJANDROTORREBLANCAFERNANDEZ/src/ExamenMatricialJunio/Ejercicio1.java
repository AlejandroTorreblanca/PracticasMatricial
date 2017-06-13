/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExamenMatricialJunio;
import auxiliar.*;

/**
 *
 * @author Alejandro
 */
public class Ejercicio1 {

    public static void crearTridiagonal(double [] dP,double a, double[] dI, double[] dS, double b)
    {
        for (int i = 0; i < dP.length; i++) {
            dP[i]=a;
            if (i < dI.length) {
                dI[i]=b;
                dS[i]=b;
            }
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int n=0;
        double precision=0.0001;
        int nmax=1000000;
        double max=0;
        for (int i = 0; i < 3; i++) {
            switch(i)
            {
                case 0: n=10;
                    break;
                case 1: n=100;
                    break;
                case 2: n=1000;
            }            
            System.out.println("\t\t****Apartado A  n="+n+"**** \n");
            double[] dP=new double[n];
            double[] dI=new double[n-1];
            double[] dS=new double[n-1];
            double[] dP1=new double[n];
            double[] dI1=new double[n-1];
            double[] dS1=new double[n-1];
            precision=0.0001;
            //Creamos las matrices tridiagonales T1 
            crearTridiagonal(dP,4.15,dI,dS,2);
            Matrices.Tridiagonal T1= new Matrices.Tridiagonal(dP, dI, dS);
            
             try {
                //Usamos el método para el cálculo de los valores  propios.
                double[] A=Matrices.TriDiagPropiosQR1(T1, precision, nmax);
                //System.out.println("matriz: "+ Matrices.toString(A));
                double min=A[0];
                for (int j = 0; j < A.length; j++) { //Buscamos el mayor y menor valor propio para dividirlos y obtener el númeor de condición..
                        if(Math.abs(A[j])>max)
                            max=Math.abs(A[j]);
                        
                        if(Math.abs(A[j])<min)
                            min=Math.abs(A[j]);
                        
                    }
                double condicion=max/min;
                System.out.println("El número de condición de la matriz de tamaño "+n+" es el mayor valor propio entre el menor"
                        + ", cada uno en valor absoluto: "+max+"/"+min+"="+condicion);
            } catch (Matrices.ErrorMatrices ex) {
            }
             
            System.out.println("\n\n\t\t****Apartado B  n="+n+"****\n");
            
            //Debemos calcular la matriz del método de Jacobi, Tj=D^-1(-(L+D))
            double[][] Maux1=new double[4][4];
            double[][] Maux2=new double[4][4];
            //Calculamos un ejemplo de matriz 4x4 que nos servirá para ver como se comportan los valores de Tj.
            for (int j = 0; j < Maux2.length; j++) {
                for (int k = 0; k < Maux2.length; k++) {
                    if(j==k)
                        Maux1[j][k]=1./4.15;
                    else if (j==k-1)
                        Maux2[j][k]=-2;
                    else if (j==k+1)
                        Maux2[j][k]=-2;
                }
            }
            
            try {
                double[][] res=Matrices.producto(Maux1, Maux2);
                crearTridiagonal(dP1,1,dI1,dS1,res[0][1]);
                Matrices.Tridiagonal Tj= new Matrices.Tridiagonal(dP1, dI1, dS1);  
                double[] A=Matrices.TriDiagPropiosQR1(Tj, precision, nmax);
                //System.out.println( Matrices.toString(A));
                max=0;
                for (int j = 0; j < A.length; j++) { //Buscamos el radio espectral, es el mayor de los valores propios en valor absoluto.
                        if(Math.abs(A[j])>max)
                            max=Math.abs(A[j]);
                    }
                System.out.println("El radio espectral  de la matriz Id+TJn de tamaño "+n+" es: "+max);

            } catch (Matrices.ErrorMatrices ex) {
            }
            
            System.out.println("\n\n\t\t****Apartado C  n="+n+"****\n");
            double[] x0=new double[n];
            x0[0]=1;
            double[] b=new double[n];
            double[] dC=new double[n];
            precision=0.0000001;
            for (int j = 0; j < b.length; j++) {
                b[j]=10*(j%10-5);
                dC[j]=2;    //Esta la usaremos en el apartado D pero aprovechamos el bucle para rellenarla.
                
            }
            try {
                double[] sol=Matrices.iterJacobi(T1, b, precision, nmax, x0, false);
                System.out.println("La solucion de Tnx=b usando Jacobi es:\n"+Matrices.toString(sol)+"\n");
            } catch (Matrices.ErrorMatrices ex) {
            }
            try {
                double[] sol=Matrices.iterGaussSeidel(T1, b, precision, nmax, x0, false);
                System.out.println("La solucion de Tnx=b usando GaussSeidel es:\n"+Matrices.toString(sol)+"\n");
            } catch (Matrices.ErrorMatrices ex) {
            }
            try {
                double aux=max-1;   //max es el radio espectral de Id+Tj, le restamos 1 para obtener el de Tj.
                double w=2/(1+Math.sqrt(1-aux*aux)); //Calculamos el w optimo a partir de el radio espectral de Tj.
                double[]sol=Matrices.iterrelajacion(T1, b, w, precision, nmax, x0, false);
                System.out.println("La solucion de Tnx=b usando relajacion es:\n"+Matrices.toString(sol));
            } catch (Matrices.ErrorMatrices ex) {
            }
                
            
            System.out.println("\n\n\t\t****Apartado D  n="+n+"****\n");
            
            try {
                double[] sol=Matrices.gradienteConjugado(T1, b, x0, precision);
                System.out.println("La solucion de Tnx=b usando gradiente conjugado es:\n"+Matrices.toString(sol)+"\n");
            } catch (Matrices.ErrorMatrices ex) {
            }
            
            
            try {
                Matrices.Diagonal C=new Matrices.Diagonal(dC);
                double[] sol = Matrices.gradienteConjugadoPrecondicionado(T1, C, b, x0, precision);
                System.out.println("La solucion de Tnx=b usando gradiente conjugado precondicionado es:\n"+Matrices.toString(sol));
            } catch (Matrices.ErrorMatrices ex) {
            }
            /*
            Podemos comprobar que los métodos de Gradiente conjugado conjergen mucho más rapido que Jacobi y Gauss-Seidel, y
            un poco más rápido que el método de relajación.
            
            */
        }
    }
}
