package pl.edu.mimuw;

import pl.edu.mimuw.matrix.FullMatrix;
import pl.edu.mimuw.matrix.GeneralMatrix;

public class Main {

  public static void main(String[] args) {
    // Tu trzeba wpisać kod testujący toString dla poszczególnych macierzy i wyników operacji
    double[][] test ={{1., 3, 3.}, {1., 5., 0.}, {0., 0., 0.}};
    var matrix = new FullMatrix(test);
    var new_matrx = matrix.times(1);
    System.out.println(new_matrx.toString());
    System.out.println(new_matrx.normOne());
    System.out.println(new_matrx.normInfinity());
    System.out.println(new_matrx.frobeniusNorm());
  }
}
