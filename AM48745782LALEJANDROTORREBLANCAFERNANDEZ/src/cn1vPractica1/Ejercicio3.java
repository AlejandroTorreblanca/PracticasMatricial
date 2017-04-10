/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn1vPractica1;

/**
 *
 * @author fenix
 */
public class Ejercicio3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Explica los resultados de las siguientes instrucciones.
double[] a = {12.6, 2.3, 6.28};
double[] c=cambiar(a);
double[] d=nocambiar(a);
System.out.println(c[0]);
System.out.println(d[0]);
}
public static double[] cambiar(double[] b) {
double[] c = new double[b.length];
//c = b;   Lo quitamos para que no apunten al mismo sitio y se produzca aliasing
System.arraycopy(b, 0, c, 0, b.length);//En c copias la informacion de b
c[0] = 3455.23;//Y cambia el primer elemento
return c;
}
public static double[] nocambiar(double[] d) {
double[] e = new double[d.length];
//e = d;//Lo quitamos para que no se produzca aliasing
//Para copiar los elementos del array sin hacer aliasing 
System.arraycopy(d, 0, e, 0, d.length);

return e;
}

    
    
}
