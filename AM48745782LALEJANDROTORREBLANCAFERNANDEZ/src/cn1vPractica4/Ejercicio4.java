/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn1vPractica4;

import auxiliar.PolinomioInterpolador;

/**
 *
 * @author fenix
 */
public class Ejercicio4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        double[][] xy={{0,0},{0.5,Math.PI/6},{Math.sqrt(2)/2,Math.PI/4},
            {Math.sqrt(3)/2,Math.PI/3},{1,Math.PI/2}};
        PolinomioInterpolador q =new PolinomioInterpolador(xy);
        double x=q.eval(0.75);
        System.out.println("arcsen(0.75) approx = "+x+" \n sen (x) = "+
                Math.sin(x));
        
        double[][] xy1={{0,0},{0.5,Math.PI/6},{Math.sqrt(2)/2,Math.PI/4},
            {Math.sqrt(3)/2,Math.PI/3}};
        PolinomioInterpolador q1 =new PolinomioInterpolador(xy1);
        double x1=q1.eval(0.75);
        System.out.println("arcsen(0.75) approx = "+x1+" \n sen (x1) = "+
                Math.sin(x1));
        
        double[][] xy2={{0.5,Math.PI/6},{Math.sqrt(2)/2,Math.PI/4},
            {Math.sqrt(3)/2,Math.PI/3},{Math.sin(x1),x1}};
        PolinomioInterpolador q2 =new PolinomioInterpolador(xy2);
        double x2=q2.eval(0.75);
        System.out.println("arcsen(0.75) approx = "+x2+" \n sen (x2) = "+
                Math.sin(x2));
        
        double[][] xy3={{Math.sqrt(2)/2,Math.PI/4},
            {Math.sqrt(3)/2,Math.PI/3},{Math.sin(x1),x1},{Math.sin(x2),x2}};
        PolinomioInterpolador q3 =new PolinomioInterpolador(xy3);
        double x3=q3.eval(0.75);
        System.out.println("arcsen(0.75) approx = "+x3+" \n sen (x3) = "+
                Math.sin(x3));
        
        double[][] xy4={{Math.sqrt(3)/2,Math.PI/3},{Math.sin(x1),x1},
            {Math.sin(x2),x2},{Math.sin(x3),x3}};
        PolinomioInterpolador q4 =new PolinomioInterpolador(xy4);
        double x4=q4.eval(0.75);
        System.out.println("arcsen(0.75) approx = "+x4+" \n sen (x4) = "+
                Math.sin(x4));
                
    }
    
}
