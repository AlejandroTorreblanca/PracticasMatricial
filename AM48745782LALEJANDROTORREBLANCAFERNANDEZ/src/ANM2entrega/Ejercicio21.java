/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ANM2entrega;
import auxiliar.*;

/**
 *
 * @author Alejandro
 */
public class Ejercicio21 {

    
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
    public static void main(String[] args)  {
        System.out.println("\t\t****Apartado A****\n");
        int n=0;
        double precision=0.00001;
        int nmax=1000000;
        long start, stop;
        for (int i = 0; i < 3; i++) {
            switch(i)
            {
                case 0: n=6;
                    break;
                case 1: n=10;
                    break;
                case 2: n=250;
            }            
            
            double[] dP=new double[n];
            double[] dI=new double[n-1];
            double[] dS=new double[n-1];

            //Creamos las matrices tridiagonales T1 y T2
            crearTridiagonal(dP,4,dI,dS,2);
            Matrices.Tridiagonal T1= new Matrices.Tridiagonal(dP, dI, dS);
            crearTridiagonal(dP,2,dI,dS,-1);
            Matrices.Tridiagonal T2= new Matrices.Tridiagonal(dP, dI, dS);
           
            try {
                //Usamos el método solicitado para el cálculo de los valores y vectores propios.
                System.out.println("T1 con matriz de dimensión "+n);
                start=System.currentTimeMillis();
                double[][] A=Matrices.TriDiagPropiosQR(T1, precision, nmax);
                //System.out.println("matriz: "+ Matrices.toString(A));
                stop=System.currentTimeMillis();
                System.out.println("Calculados en "+(stop-start)+" ms con matriz de dimensión "+n);
            } catch (Matrices.ErrorMatrices ex) {
            }
            try {
                System.out.println("\nT2 con matriz de dimensión "+n);
                start=System.currentTimeMillis();
                double[][] A=Matrices.TriDiagPropiosQR(T2, precision, nmax);
                stop=System.currentTimeMillis();
                System.out.println("Calculados en "+(stop-start)+" ms con matriz de dimensión "+n+"\n");
                } catch (Matrices.ErrorMatrices ex) {
            }
        }
        //Si queremos ver los resultado de los métodos por pantalla:
//        System.out.println("Valores y vectores propios de T1 con dimensión : "+ n +" "+Matrices.toString(A));
//        
//        System.out.println("\nValores y vectores propios de T2 con dimensión : "+ n +Matrices.toString(B));
        

        
        /*
        Apartado 2
        La matriz T1 se puede escribir como T1=L+D+U y la matriz del método de Jacobi será de la forma Tj=D^-1(-(L+D)).
        Podemos escribir directamente la matriz Tj teniendo en cuenta que la inversa de una matriz diagonal se obtiene al invertir 
        cada número de la diagonal. Dado que la matriz tiene diagonal nula lo que haremos es sumarle la identidad y luego al 
        radio espectral resultante le rertaremos uno para obtener el de Tj.
        */
        System.out.println("\n\n\t\t****Apartado B****\n\n");
        
         for (int r = 0; r < 3; r++) {
            switch(r)
            {
                case 0: n=6;
                    break;
                case 1: n=10;
                    break;
                case 2: n=250;
            } 
            
            double[] dP=new double[n];
            double[] dI=new double[n-1];
            double[] dS=new double[n-1];
            double[] dP2=new double[n];
            double[] dI2=new double[n-1];
            double[] dS2=new double[n-1];

            //Creamos la matriz tridiagonal T1 
            crearTridiagonal(dP2,4,dI2,dS2,2);
            Matrices.Tridiagonal T1= new Matrices.Tridiagonal(dP2, dI2, dS2);
            
            double[][] Tj=new double[n][n];
            //Creamos la matriz Tj[][] directamente ya que es sencilla de calcular.
            for (int i = 0; i < Tj.length; i++) { 
                for (int j = 0; j < Tj.length; j++) {
                    if(i==j)
                        Tj[i][j]=0;
                    else if (i==j+1)
                        Tj[i][j]=-0.5;
                    else if (i==j-1)
                        Tj[i][j]=-0.5;
                }
            }
            //Creamos T3=Tj como una matriz abtracta tridiagonal.    
            crearTridiagonal(dP,1,dI,dS,-0.5);
            Matrices.Tridiagonal T3= new Matrices.Tridiagonal(dP, dI, dS);
           
            double[] C;
            double max=0;
            try {   
                //Usamos el método que solo nos devuelve los valores propios ya que los vectores no los necesitamos.
                C = Matrices.TriDiagPropiosQR1(T3, precision, nmax); 
                for (int j = 0; j < C.length; j++) { //Buscamos el radio espectral, es el mayor de los valores propios en valor absoluto.
                        if(Math.abs(C[j])>max)
                            max=Math.abs(C[j]);
                    }
                System.out.println("El radio espectral  de la matriz Id+TJ de tamaño "+n+" es: "+max);
                System.out.println("El radio espectral  de la matriz TJ de tamaño "+n+" es: "+(max-1));
                //System.out.println("TriDiagPropiosQR1: " + Matrices.toString(C));

            } catch (Matrices.ErrorMatrices ex) {  //Utilizamos el método de Jacobi para ver si converge a los valores propios.
            }
            //Calculamos el peso optimo a partir del radio espectral de matriz de Jacobi.
            
            double w=2/(1+Math.sqrt(1-(max-1)*(max-1)));
            
            //Calculamos el vector b de la ecuación T1x=b.
            double[] b=new double[n];
            for (int i = 0; i < b.length; i++) {
                b[i]=(2*i+1)*(-1)^i;
            }
            
            double[] x0=new double[n];
            x0[0]=1;
            x0[3]=1;
            try {
                double[] sol=Matrices.iterrelajacion(T1, b, w, precision, nmax, x0, false);
                System.out.println("La solución del sistema para tamaño "+n+" es:\n" + Matrices.toString(sol)+"\n");
            } catch (Matrices.ErrorMatrices ex) {
                System.out.println("La matriz no es simétrica o definida positiva y por tanto no está asegurada la convergencia.\n");
            }
            
        }
    }
    
}
