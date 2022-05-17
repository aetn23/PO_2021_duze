package pl.edu.mimuw.matrix;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static java.lang.Math.abs;

public class DiagonalMatrix extends MatrixWithDiagonal {

	public DiagonalMatrix(double... diagonal_values) {
		super(diagonal_values);
	}

	public DiagonalMatrix(ArrayList<Double> diagonal) {
		assert diagonal != null;

		this.diagonal = diagonal;
	}

	@Override
	public IDoubleMatrix times(double scalar) {
		return new DiagonalMatrix(diagonal.stream().map(value -> value * scalar)
						.collect(Collectors.toCollection(ArrayList::new)));
	}

	@Override
	public IDoubleMatrix plus(double scalar) {
		return new DiagonalMatrix(diagonal.stream().map(value -> value + scalar)
						.collect(Collectors.toCollection(ArrayList::new)));
	}

	@Override
	public double get(int row, int column) {
		checkIfIndexInbounds(row, column);
		return row == column ? diagonal.get(row) : 0.;
	}
}
