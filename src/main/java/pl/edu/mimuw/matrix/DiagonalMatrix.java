package pl.edu.mimuw.matrix;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class DiagonalMatrix extends GeneralMatrix {
	private ArrayList<Double> diagonal;

	public DiagonalMatrix(double... diagonal_values) {
		assert diagonal_values.length != 0;

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

	double normInfinity();

	double frobeniusNorm();

	@Override
	public double get(int row, int column) {
		return row == column ? diagonal.get(row) : 0.;
	}
}
