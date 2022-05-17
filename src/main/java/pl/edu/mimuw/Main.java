package pl.edu.mimuw;

import pl.edu.mimuw.matrix.DoubleMatrixFactory;
import pl.edu.mimuw.matrix.FullMatrix;
import pl.edu.mimuw.matrix.GeneralMatrix;
import pl.edu.mimuw.matrix.IDoubleMatrix;

import static pl.edu.mimuw.matrix.DoubleMatrixFactory.sparse;
import static pl.edu.mimuw.matrix.MatrixCellValue.cell;
import static pl.edu.mimuw.matrix.Shape.matrix;

public class Main {

	public static void main(String[] args) {
		// Tu trzeba wpisać kod testujący toString dla poszczególnych macierzy i wyników operacji
		double[][] test = {{1., 3, 3.}, {1., 5., 0.}, {0., 0., 0.}};
		double[][] identity = {{1., 0, 0}, {0, 1., 0.}, {0., 0., 1.}};
		var iden_matrix = new FullMatrix(identity);
		var matrix = new FullMatrix(test);
		var new_matrx = matrix.times(1);
		System.out.println(new_matrx.toString());
		System.out.println(new_matrx.normOne());
		System.out.println(new_matrx.normInfinity());
		System.out.println(new_matrx.frobeniusNorm());
		System.out.println(new_matrx.times(iden_matrix).toString());

		System.out.println(SPARSE_2X3.toString());

		final var l = DoubleMatrixFactory.sparse(
						matrix(1_000_000, 1_000_000_000),
						cell(0, 0, 42),
						cell(767, 123_123, 24),
						cell(999_999, 999_999_999, 66)
		);
		final var r = DoubleMatrixFactory.sparse(
						matrix(1_000_000, 1_000_000_000),
						cell(0, 0, 24),
						cell(767, 123_123, 42)
		);
		final var result = l.plus(r);

	}


	public static final IDoubleMatrix SPARSE_2X3 = sparse(matrix(2, 3),
					cell(0, 0, 1),
					cell(0, 1, 2),
					cell(0, 2, 3),
					cell(1, 0, 4),
					cell(1, 1, 5),
					cell(1, 2, 6)
	);

	public static final IDoubleMatrix SPARSE_3X2 = sparse(matrix(3, 2),
					cell(0, 0, 1),
					cell(0, 1, 2),
					cell(1, 0, 3),
					cell(1, 1, 4),
					cell(2, 0, 5),
					cell(2, 1, 6)
	);

}
