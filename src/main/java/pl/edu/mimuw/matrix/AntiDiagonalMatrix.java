package pl.edu.mimuw.matrix;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class AntiDiagonalMatrix extends MatrixWithDiagonal {

	public AntiDiagonalMatrix(double... diagonal_values) {
		super(diagonal_values);
	}

	public AntiDiagonalMatrix(ArrayList<Double> diagonal) {
		assert diagonal != null;
		this.diagonal = diagonal;
	}

	@Override
	public IDoubleMatrix times(double scalar) {
		return new AntiDiagonalMatrix(diagonal.stream().map(value -> value * scalar)
						.collect(Collectors.toCollection(ArrayList::new)));
	}

	@Override
	public IDoubleMatrix plus(double scalar) {
		return new AntiDiagonalMatrix(diagonal.stream().map(value -> value + scalar)
						.collect(Collectors.toCollection(ArrayList::new)));
	}

	@Override
	public double get(int row, int column) {
		checkIfIndexInbounds(row, column);
		return row + column == shape.rows + 1 ? diagonal.get(column) : 0.;
	}
}
