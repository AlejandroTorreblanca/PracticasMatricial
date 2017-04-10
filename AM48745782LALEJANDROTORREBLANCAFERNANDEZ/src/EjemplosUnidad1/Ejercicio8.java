/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EjemplosUnidad1;

/**
 *
 * @author antoniojosepallaresruiz
 */
public class Ejercicio8 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        double termino0 = 2. / Math.PI;
        double Ik = termino0;
        for (int i = 1; i <= 26; i++) {
            System.out.println("i = " + (i - 1) + "\t Ik = " + Ik);
            Ik = 1. / Math.PI - (2 * i * (2 * i - 1) / (Math.PI * Math.PI)) * Ik;

        }
    }
}
