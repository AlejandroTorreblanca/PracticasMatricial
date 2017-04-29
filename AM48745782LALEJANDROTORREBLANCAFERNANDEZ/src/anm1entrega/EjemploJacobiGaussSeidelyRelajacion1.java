/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package anm1entrega;

/**
 *
 * @author apall
 * 
 * Ejemplo Métodos Jacobi; Gauss-Seidel y Relajación
 * 
 *          2 x +   y  =  4
 *            x + 2 y  = 11
 * 
 */
public class EjemploJacobiGaussSeidelyRelajacion1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        double precision = 0.5E-10;
        double a=0, b= 0;
        double xk=a, yk=b;
        for (int i = 0; i < 100; i++) {
            double errorx= (2*xk + yk -4);
            double errory= (xk+ 2*yk -11);
            if(errorx*errorx+errory*errory < precision * precision){
                System.out.println("Jacobi converge en k = "+ i + " etapas");
                System.out.println("x = "+ xk + "  y = "+yk);
                break;
            }
            double xks= xk -0.5*errorx; //-0.5*yk + 0.5* 4;
            double yks= yk -0.5*errory; //-0.5*xk + 0.5* 11;
            
            xk=xks;
            yk=yks;
            
        }
        xk=a; yk=b;
        for (int i = 0; i < 100; i++) {
            double errorx= (2*xk + yk -4);
            double xks= xk - 0.5*errorx; // -0.5*yk + 0.5* 4;
            double errory= (xks+ 2*yk -11);
            if(errorx*errorx+errory*errory < precision * precision){
                System.out.println("Gauss-Seidel converge en k = "+ i + " etapas");
                System.out.println("x = "+ xk + "  y = "+yk);
                break;
            }
           
            double yks= yk - 0.5*errory;  // -0.5*xks + 0.5* 11;
            
            xk=xks;
            yk=yks;
            
        }
        
        double w=1.16;//w= 4/(2+Math.sqrt(3));// 
        System.out.println("w = "+ w);
        xk=a; yk=b;
        for (int i = 0; i < 100; i++) {
            double errorx= (2*xk + yk -4);
            double xks= -0.5*yk + 0.5* 4;
            double errory= (xks+ 2*yk -11);
            if(errorx*errorx+errory*errory < precision * precision){
                System.out.println("relajacion converge en k = "+ i + " etapas");
                System.out.println("x = "+ xk + "  y = "+yk);
                break;
            }
           
            xk=xk - w * 0.5 * errorx;
            yk=yk - w * 0.5 * errory;
            
        }
    }
}
