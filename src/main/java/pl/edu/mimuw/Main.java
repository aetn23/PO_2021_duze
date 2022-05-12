package pl.edu.mimuw;

import pl.edu.mimuw.matrix.GeneralMatrix;

public class Main {

  public static void main(String[] args) {
    // Tu trzeba wpisać kod testujący toString dla poszczególnych macierzy i wyników operacji
    double[][] test ={{1., 2., 3.}, {10., 10., 10.}, {0., 0., 0.}};
    var matrix = new GeneralMatrix(test);
    var new_matrx = matrix.times(2137.);
    System.out.println(new_matrx.toString());
  }
}
