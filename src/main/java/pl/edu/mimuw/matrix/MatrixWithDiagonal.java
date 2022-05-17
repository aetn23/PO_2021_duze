package pl.edu.mimuw.matrix;

import java.util.ArrayList;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public abstract class MatrixWithDiagonal extends GeneralMatrix {
	protected ArrayList<Double> diagonal;

	public MatrixWithDiagonal(double... diagonal_values) {
		super(Shape.matrix(diagonal_values.length, diagonal_values.length));

		assert diagonal_values != null && diagonal_values.length != 0;

		diagonal = new ArrayList<>();
		shape.rows = diagonal_values.length;
		shape.columns = diagonal_values.length;

		for (double diagonal_value : diagonal_values) {
			diagonal.add(diagonal_value);
		}
	}

	@Override
	public double normOne() {
		return diagonal.stream().reduce(0., (acc, b) -> acc > abs(b) ? acc : abs(b));
	}

	@Override
	public double normInfinity() {
		return normOne();
	}

	@Override
	public double frobeniusNorm() {
		return sqrt(diagonal.stream().reduce(0., (acc, b) -> acc + b*b));
	}
}
