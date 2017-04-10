/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EjemplosUnidad1;

/**
 *
 * @author antoniopallaresruiz
 */
public class Ejemplo2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        /* Los enteros byte son los que se representan con 8 digitos
         * recordad que 2^8= 256
         */
        for (int i = 0; i < 256; i++) {
            System.out.println(" (byte) " + i + " = " + ((byte) i));
        }

        /* Los enteros int son los que se representan con 32 digitos 
         * recordad que el valor de  2^32 es
         */
        System.out.println("\n 2^32 =" + ((long) Math.pow(2, 32)) + "\n");
        int i = 1;
        for (int j = 1; j < 32; j++) {
            i = i * 2;

        }
        System.out.println(" 2^31 en (int) es " + i);
        i = i - 1;

        System.out.println(" 2^31 -1  = " + i);
        int j = 2147483647;
//       
//        
        j = j + 1;
        System.out.println("El valor obtenido 2147483647 +1 = " + i);

    }
}
