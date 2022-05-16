package pl.edu.mimuw.matrix;

public class DoubleMatrixFactory {

  private DoubleMatrixFactory() {
  }

  public static IDoubleMatrix sparse(Shape shape, MatrixCellValue... values){
    return null; // Tu trzeba wpisać właściwą instrukcję
  }

  public static IDoubleMatrix full(double[][] values) {
    return new FullMatrix(values); // Tu trzeba wpisać właściwą instrukcję
  }

  public static IDoubleMatrix identity(int size) {
    return new IdentityMatrix(size); // Tu trzeba wpisać właściwą instrukcję
  }

  public static IDoubleMatrix diagonal(double... diagonalValues) {
    return new DiagonalMatrix(diagonalValues); // Tu trzeba wpisać właściwą instrukcję
  }

  public static IDoubleMatrix antiDiagonal(double... antiDiagonalValues) {
    return new AntiDiagonalMatrix(antiDiagonalValues); // Tu trzeba wpisać właściwą instrukcję
  }

  public static IDoubleMatrix vector(double... values){
    return new RowMatrix(values, Shape.vector(values.length));
  }

  public static IDoubleMatrix zero(Shape shape) {
    return new ZeroMatrix(shape); // Tu trzeba wpisać właściwą instrukcję
  }
}
