package pl.edu.mimuw.matrix;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class AntiDiagonalMatrix extends GeneralMatrix {
	private ArrayList<Double> diagonal;

	public AntiDiagonalMatrix(double... diagonal_values) {
		super(Shape.matrix(diagonal_values.length, diagonal_values.length));

		diagonal = new ArrayList<>();

		for (var value : diagonal_values) {
			diagonal.add(value);
		}
	}

	public AntiDiagonalMatrix(ArrayList<Double> diagonal, Shape shape) {
		super(shape);
		assert diagonal != null;

		this.diagonal = diagonal;
		this.shape = shape;
	}

	@Override
	public IDoubleMatrix times(double scalar) {
		return new AntiDiagonalMatrix(diagonal.stream().map(value -> value * scalar)
						.collect(Collectors.toCollection(ArrayList::new)), shape);
	}

	@Override
	public IDoubleMatrix plus(double scalar) {
		return new AntiDiagonalMatrix(diagonal.stream().map(value -> value + scalar)
						.collect(Collectors.toCollection(ArrayList::new)), shape);
	}

	@Override
	public double get(int row, int column) {
		checkIfIndexInbounds(row, column);
		return row + column == shape.rows - 1 ? diagonal.get(column) : 0.;
	}

}
