package pl.edu.mimuw.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FullMatrix extends GeneralMatrix {
	private Shape shape;
	private ArrayList<ArrayList<Double>> values = new ArrayList<>();


	public FullMatrix(double[][] values) {
		assert values.length > 0 && values[0].length > 0;
		shape = Shape.matrix(values.length, values[0].length);


		for (double[] row : values) {
			assert row.length == shape.rows;

			var to_insert = new ArrayList<Double>();
			for (double number : row) {
				to_insert.add(number);
			}

			this.values.add(to_insert);
		}
	}

	public FullMatrix(ArrayList<ArrayList<Double>> matrix) {
		shape = Shape.matrix(matrix.size(), matrix.get(0).size());
		this.values = matrix;
	}


	@Override
	public IDoubleMatrix times(IDoubleMatrix other) {
		return null;
	}

	@Override
	public IDoubleMatrix times(double scalar) {
		var multiplication_result = this.values.stream().map(row -> (row.stream().map(value -> value * scalar)).collect(Collectors.toCollection(ArrayList::new))).collect(Collectors.toCollection(ArrayList::new));
		return new FullMatrix(multiplication_result);
	}

	@Override
	public IDoubleMatrix plus(IDoubleMatrix other) {
		assert other.shape().hashCode() == this.shape.hashCode();
		ArrayList<ArrayList<Double>> result = new ArrayList<>();
		var other_data = other.data();

		for (int i = 0; i < shape.rows; i++) {
			result.add(new ArrayList<Double>());
			for (int j = 0; i < shape.columns; j++)
				result.get(i).add(other_data[i][j] + values.get(i).get(j));
		}

		return new FullMatrix(result);
	}

	@Override
	public IDoubleMatrix plus(double scalar) {
		var sum_result = this.values.stream().map(row -> (row.stream().map(value -> value + scalar)).collect(Collectors.toCollection(ArrayList::new))).collect(Collectors.toCollection(ArrayList::new));
		return new FullMatrix(sum_result);
	}

	@Override
	public IDoubleMatrix minus(IDoubleMatrix other) {
		return plus(other.times(-1));
	}

	@Override
	public IDoubleMatrix minus(double scalar) {
		return this.plus(-scalar);
	}

	@Override
	public double get(int row, int column) {
		return values.get(row).get(column);
	}

	@Override
	public double[][] data() {
		var result = new ArrayList<double[]>();
		values.stream().forEach(array -> {
			result.add(array.stream().mapToDouble(Double::doubleValue).toArray());
		});

		return result.toArray(new double[shape.rows][shape.columns]);
	}

	@Override
	// todo this sums rows, not columns.
	public double normOne() {
		return values.stream().map(column -> column.stream().reduce(0., Double::sum))
						.collect(Collectors.toCollection(ArrayList::new)).stream().reduce(0., (a, b) -> a > b ? a : b);
	}

	@Override
	public double normInfinity() {
		return 0;
	}

	@Override
	public double frobeniusNorm() {
		return 0;
	}

	@Override
	public Shape shape() {
		return shape;
	}


}