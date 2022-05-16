package pl.edu.mimuw.matrix;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class ConstantMatrix extends GeneralMatrix {
	private final double value;

	public ConstantMatrix(Shape shape, double value) {
		super(shape);
		this.shape = shape;
		this.value = value;
	}

	@Override
	public IDoubleMatrix times(double scalar) {
		return new ConstantMatrix(shape, value * scalar);
	}

	@Override
	public IDoubleMatrix plus(double scalar) {
		return new ConstantMatrix(shape, value + scalar);
	}

	@Override
	public double get(int row, int column) {
		return value;
	}

	@Override
	public double normOne() {
		return abs(value*shape.rows);
	}

	@Override
	public double normInfinity() {
		return abs(value*shape.columns);
	}

	@Override
	public double frobeniusNorm() {
		return sqrt(abs(value*value*shape.columns*shape.rows));
	}

}
