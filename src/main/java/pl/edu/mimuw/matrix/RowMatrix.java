package pl.edu.mimuw.matrix;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class RowMatrix extends ColumnRowMatrix {

	public RowMatrix(ArrayList<Double> values, Shape shape) {
		super(values, shape);
	}

	public RowMatrix(double[] values, Shape shape) {
		super(values, shape);
	}

	@Override
	public double get(int row, int column) {
		checkIfIndexInbounds(row, column);
		return values.get(row);
	}

	@Override
	public double normOne() {
		return values.stream().reduce(0., (acc, b) -> acc + abs(b));
	}

	@Override
	public double normInfinity() {
		return values.stream().reduce(0., (acc, b) -> acc > abs(b) ? acc : abs(b)) * shape.columns;

	}

	@Override
	public double frobeniusNorm() {
		return sqrt(values.stream().reduce(0., (acc, b) -> acc + b * b * shape.columns));
	}

	@Override
	public IDoubleMatrix times(double scalar) {
		return new RowMatrix(values.stream().map(value -> value * scalar)
						.collect(Collectors.toCollection(ArrayList::new)), this.shape);
	}

	@Override
	public IDoubleMatrix plus(double scalar) {
		return new RowMatrix(values.stream().map(value -> value + scalar)
						.collect(Collectors.toCollection(ArrayList::new)), this.shape);
	}

}
