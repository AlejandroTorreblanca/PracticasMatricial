/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn1vPractica2;

import ORG.netlib.math.complex.Complex;
import auxiliar.PanelDibujo;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author Alejo Junior
 */
public class Ejercicio4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //TODO code application logic here
        //apartado a
        Complex z = new Complex(-1, 1);
        System.out.println("z = " + z);
        Complex z1 = new Complex(1, 3);
        System.out.println("z1 = " + z1);
        Complex z2 = new Complex(2, -6);
        System.out.println("z2 = " + z2);
        System.out.println("z1 + z2 = " + z1.add(z2));
        // apartado b
        double argz1=z1.arg();
        System.out.println("arg(z1) = " +argz1);
        double modz1=z1.abs();
        System.out.println("|z1| = " +modz1);
       //Apartado c
        double argz2=z2.arg();
        System.out.println("arg(z2) = " +argz2);
        double modz2=z2.abs();
        System.out.println("|z2| = " + modz2);
        //Apartado d
        Complex z3 = new Complex(1, -1);
        Complex sqrt1= z3.sqrt();
        System.out.println("sqrt1 = " + sqrt1+" ,sqrt1 * sqrt1 = "+ sqrt1.mul(sqrt1));
        Complex z4 = new Complex(1, 1);
        Complex sqrt2= z4.sqrt();
        System.out.println("sqrt2 = " +sqrt2+" ,sqrt2 * sqrt2 = "+ sqrt2.mul(sqrt2));
        //apartado e
        Complex z5 = new Complex(-1, -2);
        int n=10;
        argz1=z5.arg();
        modz1=z5.abs();
        Complex[] raices= new Complex[n];
        double[][] vertices =new double[n][2];
        double modW=Math.pow(modz1,1./n);
        for(int i=0;i<raices.length;i++){
            raices[i]=Complex.polar(modW, argz1/n + 2*Math.PI*i/n);
            vertices[i][0]=raices[i].re();
            vertices[i][1]=raices[i].im();
            System.out.println("raices[ "+i+" ]"+raices[i]);
        }
        PanelDibujo pd=new PanelDibujo("apartado e del Ejercicio 4");
         /*
         * Una buena estrategia para evitar dejar progamas en ejecucion es crear
         * un "listener" (modulo de escucha) sobre la cruz de cerrar la ventana del panel de dibujo
         * para poder cerrar este panel en cualquier momento y detener la ejecucion del programa		 *
         */


        pd.addWindowListener(
                new WindowAdapter() {

                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });
        
        
pd.addEjesCoordenados(true);
pd.addListaPuntos(Color.blue, vertices,5);
pd.repaint();
    }

}
