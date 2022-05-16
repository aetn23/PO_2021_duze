package pl.edu.mimuw.matrix;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class AntiDiagonalMatrix extends MatrixWithDiagonal {

	public AntiDiagonalMatrix(double... diagonal_valeues) {
		super(diagonal_valeues);
	}

	public AntiDiagonalMatrix(ArrayList<Double> diagonal) {
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
		return row + column == shape.rows + 1 ? diagonal.get(column) : 0.;
	}
}
