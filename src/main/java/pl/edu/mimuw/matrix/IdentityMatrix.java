package pl.edu.mimuw.matrix;

import java.util.ArrayList;

public class IdentityMatrix extends GeneralMatrix {
	public IdentityMatrix(int size) {
		super(Shape.matrix(size, size));
		shape.columns = size;
		shape.rows = size;
	}

	@Override
	public IDoubleMatrix times(IDoubleMatrix other) {
		return other;
	}

	@Override
	public IDoubleMatrix times(double scalar) {
		var result_as_array = new ArrayList<Double>();

		for(int i =0; i < shape.rows; i++) {
			result_as_array.add(scalar);
		}

		return new DiagonalMatrix(result_as_array);
	}

	@Override
	public IDoubleMatrix plus(double scalar) {
		var result_as_array = new ArrayList<Double>();

		for(int i =0; i < shape.rows; i++) {
			result_as_array.add(scalar + 1.);
		}

		return new DiagonalMatrix(result_as_array);
	}

	@Override
	public double normOne() {
		return 1.;
	}

	@Override
	public double normInfinity() {
		return 1.;
	}

	@Override
	public double frobeniusNorm() {
		return 1.;
	}

	@Override
	public double get(int row, int column) {
		return row == column ? 1. : 0.;
	}
}
