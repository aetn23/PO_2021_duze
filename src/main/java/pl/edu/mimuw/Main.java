package pl.edu.mimuw;

import pl.edu.mimuw.matrix.FullMatrix;
import pl.edu.mimuw.matrix.GeneralMatrix;

public class Main {

  public static void main(String[] args) {
    // Tu trzeba wpisać kod testujący toString dla poszczególnych macierzy i wyników operacji
    double[][] test ={{1., 3, 3.}, {1., 5., 0.}, {0., 0., 0.}};
    double[][] identity ={{1., 0, 0}, {0, 1., 0.}, {0., 0., 1.}};
    var iden_matrix = new FullMatrix(identity);
    var matrix = new FullMatrix(test);
    var new_matrx = matrix.times(1);
    System.out.println(new_matrx.toString());
    System.out.println(new_matrx.times(iden_matrix).toString());
  }
}
