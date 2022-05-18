package pl.edu.mimuw.matrix;

import java.util.ArrayList;
import java.util.stream.Collectors;


public class DiagonalMatrix extends GeneralMatrix {
	private ArrayList<Double> diagonal;

	public DiagonalMatrix(double... diagonal_values) {
		super(Shape.matrix(diagonal_values.length, diagonal_values.length));

		diagonal = new ArrayList<>();

		for (var value : diagonal_values) {
			diagonal.add(value);
		}
	}

	public DiagonalMatrix(ArrayList<Double> diagonal, Shape shape) {
		super(shape);
		assert diagonal != null;

		this.diagonal = diagonal;
	}

	@Override
	public IDoubleMatrix times(double scalar) {
		return new DiagonalMatrix(diagonal.stream().map(value -> value * scalar)
						.collect(Collectors.toCollection(ArrayList::new)), shape);
	}

	@Override
	public IDoubleMatrix plus(double scalar) {
		return new DiagonalMatrix(diagonal.stream().map(value -> value + scalar)
						.collect(Collectors.toCollection(ArrayList::new)), shape);
	}

	@Override
	public double get(int row, int column) {
		checkIfIndexInbounds(row, column);
		return row == column ? diagonal.get(row) : 0.;
	}
}
