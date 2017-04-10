/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EjemplosUnidad1;

/**
 * Espsilon de la maquina
 *
 * @author apall
 */
public class Ejemplo3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        int n = 0;
        double e = 1;
        while ((1 + e) > 1) {
            e = e / 2;
            n = n + 1;
        }
        System.out.println("e = " + e + "\t n = " + n);
        System.out.println(" 1+e == 1 " + ((1 + e) == 1));
        e = e * (1 + 2 * e); // e = 2^(-53)+
        System.out.println(" 1+e == 1 " + ((1 + e) == 1));
        System.out.println("Epsilon de Maquina = " + e);

    }
}
