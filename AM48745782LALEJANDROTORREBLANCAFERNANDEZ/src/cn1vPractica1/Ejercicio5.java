/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn1vPractica1;
// Apartado 3 hecho en el ejercio 4
/**
 *
 * @author fenix
 */
public class Ejercicio5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Empezamos primero con los numeros combinatorios
        int nsum=50;
        double[] ncomb=new double[nsum];
        ncomb[0]=1;
        ncomb[1]=0.5;
        System.out.println("ncomb[0] = "+ncomb[0]+"\n ncomb[1] = "+ncomb[1]);
        for(int i=2;i<nsum;i++){
            ncomb[i]=ncomb[i-1]*(0.5-i+1)/i;
            System.out.println("ncom["+i+" ]="+ncomb[i]);
        }
        //Apartado 1
        double sumapos=1.5;
        double sumaneg=0;
        for(int i=2; i<nsum;i++){
            if(i%2==0){
                sumaneg += ncomb[i];
            }else{
                sumapos +=ncomb[i];
            }
                
        }
        System.out.println(" raizAproximada = "+(sumapos+sumaneg));
        //Apartado 2
        double suma=1;
        double pot=1;
        for(int i=1; i< nsum;i++){           
                pot *= -0.5;
                suma += ncomb[i]*pot;
            
          
           
    }
    System.out.println(" raizAproximada = "+(1./suma));
    }
}
