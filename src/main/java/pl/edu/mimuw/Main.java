package pl.edu.mimuw;

import pl.edu.mimuw.matrix.*;

import static pl.edu.mimuw.matrix.DoubleMatrixFactory.diagonal;

public class Main {

	// IDE Intelij Idea najnowsze Java 18.

	public static void main(String[] args) {
		// Tu trzeba wpisać kod testujący toString dla poszczególnych macierzy i wyników operacji

		double[] values = {1., 2., 3., 4., 5., 6., 7., 8., 9., 10.};
		double[][] values2 =
						{{1., 2., 3., 4., 5., 6., 7., 8., 9., 10.}, {1., 2., 3., 4., 5., 6., 7., 8., 9., 10.},
										{1., 2., 3., 4., 5., 6., 7., 8., 9., 10.},
										{1., 2., 3., 4., 5., 6., 7., 8., 9., 10.},
										{1., 2., 3., 4., 5., 6., 7., 8., 9., 10.},
										{1., 2., 3., 4., 5., 6., 7., 8., 9., 10.},
										{1., 2., 3., 4., 5., 6., 7., 8., 9., 10.},
										{1., 2., 3., 4., 5., 6., 7., 8., 9., 10.},
										{1., 2., 3., 4., 5., 6., 7., 8., 9., 10.},
										{1., 2., 3., 4., 5., 6., 7., 8., 9., 10.}};

		MatrixCellValue a1 = new MatrixCellValue(0, 0, 2);
		MatrixCellValue a2 = new MatrixCellValue(9, 9, 234);

		var anitdiagonal = new AntiDiagonalMatrix(values);
		var constant = new ConstantMatrix(Shape.matrix(10, 10), 21);
		var diag = new DiagonalMatrix(values);
		var full = new FullMatrix(values2);
		var identiy = new IdentityMatrix(10);
		var sparse_matrix = new SparseMatrix(Shape.matrix(1000, 100), a1, a2);

		System.out.println(anitdiagonal.toString());
		System.out.println(constant.toString());
		System.out.println(diag.toString());
		System.out.println(full.toString());
		System.out.println(identiy.toString());
		System.out.println(sparse_matrix.toString());


	}


}
