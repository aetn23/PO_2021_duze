package pl.edu.mimuw.matrix;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static java.lang.Math.abs;

public class DiagonalMatrix extends GeneralMatrix {
	private final ArrayList<Double> diagonal;

	public DiagonalMatrix(double... diagonal_values) {
		assert diagonal_values.length != 0;

		diagonal = new ArrayList<>();
		shape.rows = diagonal_values.length;
		shape.columns = diagonal_values.length;

		for (double diagonal_value : diagonal_values) {
			diagonal.add(diagonal_value);
		}
	}

	public DiagonalMatrix(ArrayList<Double> diagonal) {
		this.diagonal = diagonal;
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
		return diagonal.stream().reduce(0., (acc, b) -> acc + b*b);
	}

	@Override
	public IDoubleMatrix times(double scalar) {
		return new DiagonalMatrix(diagonal.stream().map(value -> value*scalar).collect(Collectors.toCollection(ArrayList::new)));
	}

	@Override
	public IDoubleMatrix plus(double scalar) {
		return new DiagonalMatrix(diagonal.stream().map(value -> value+scalar).collect(Collectors.toCollection(ArrayList::new)));
	}

	@Override
	public double get(int row, int column) {
		return row == column ? diagonal.get(row) : 0.;
	}
}
