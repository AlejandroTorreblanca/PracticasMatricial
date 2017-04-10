/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auxiliar;

import ORG.netlib.math.complex.Complex;

/**
 *
 * @author apall para CN 1v del curso 2012/2013
 */
public class PolinomioInterpolador extends Polinomio{

    double[] coefFormaNewton;
    double[] nodos;

    public PolinomioInterpolador(double[][] xy) {
        this.nodos=new double[xy.length];
        this.coefFormaNewton = coefyNodosH(xy);
        coeficientesPol();

    }

    public PolinomioInterpolador() {
        coefFormaNewton=new double[1];
        nodos=new double[1];
        coef =new Complex[1];
    }

     double[] coefyNodosL(double[][] xy){
        int m=xy.length;   // m=n+1
        double[] p =new double[m];
             nodos=new double[m];
        for (int i = 0; i <m; i++) {
            p[i]=xy[i][1];
            nodos[i]=xy[i][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = m-1; j >= i; j--) {
                p[j]=(p[j]-p[j-1])/(nodos[j]-nodos[j-i]);
            }
        }
        return p;
    }
      double[] coefyNodosH(double[][] xy){
        int m=xy.length;   // m=numero de puntos a interpolar

        int[] l=new int[m];
        int[] aux=new int[m+1];
        aux[0]=0;
          for (int i = 0; i < m; i++) {
              l[i]=xy[i].length-1;
              aux[i+1]=aux[i]+l[i];
          }
        int n=aux[m]-1;
        nodos =new double[n+1]; // nodos (x_i repetidas)
        double[] p = new double[n+1]; // coeficientes de la forma de Newton
          for (int i = 0; i < m; i++) {
              for (int j = aux[i]; j < aux[i+1]; j++) {
                  nodos[j]=xy[i][0];
              }
          }
          for (int k = 0; k <= n; k++) { //recorremos las columas del diagram de DDN
              for (int i = m-1; i >= 0; i--) { //recorremos los nodos repetidos con i y j
                  for (int j = aux[i+1]-1; j >= Math.max(aux[i], k); j--) {
                      if(nodos[j-k] == nodos[j]){  // si se repiten los nodos
                          p[j]=xy[i][k+1];
                          for (int r = 2; r <= k; r++) {
                              p[j]= p[j]/r;
                          }
                      }else{  // si no se repiten los nodos
                          p[j]=(p[j]-p[j-1]) / (nodos[j] - nodos[j-k]);
                      }

                  }

              }

          }
        return p;

    }




    @Override
    public double eval(double x){
        int n= coefFormaNewton.length-1;
        double suma= coefFormaNewton[n];
        for(int i=n-1;i>=0;i--){
            suma = coefFormaNewton[i]+suma*(x-nodos[i]);
        }
        return suma;
    }


    void coeficientesPol(){
        int m=coefFormaNewton.length;
        coef = new Complex[m];
        for (int i = 0; i < m; i++) {
             coef[i]=new Complex();
        }      
        for (int i = m-1; i >=0; i--) {
            Complex auxiliar=new Complex(coefFormaNewton[i]);
            for (int k = m-1-i; k >=1; k--) {
                coef[k]=coef[k-1].sub(coef[k].scale(nodos[i]));

            }
            coef[0]=auxiliar.sub(coef[0].scale(nodos[i]));
        }

    }

}

