package pl.edu.mimuw.matrix;


import java.util.ArrayList;
import java.util.stream.Collectors;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public abstract class GeneralMatrix implements IDoubleMatrix {

	protected Shape shape;

	public IDoubleMatrix times(IDoubleMatrix other) {
		assert this.shape().columns == other.shape().rows;

		var result_as_array = new ArrayList<ArrayList<Double>>();
		for (int i = 0; i < this.shape().rows; i++) {
			result_as_array.add(new ArrayList<Double>());
			for (int j = 0; j < other.shape().columns; j++) {
				double sum = 0.;
				for (int k = 0; k < this.shape().columns; k++)
					sum += get(i, k) * other.get(k, j);

				result_as_array.get(i).add(sum);
			}
		}

		return new FullMatrix(result_as_array);
	}

	@Override
	public IDoubleMatrix times(double scalar) {
		var result_as_array = new ArrayList<ArrayList<Double>>();
		for (int i = 0; i < shape.rows; i++) {
			result_as_array.add(new ArrayList<Double>());
			for (int j = 0; j < shape.columns; j++) {
				result_as_array.get(i).add(get(i, j) * scalar);
			}
		}
		return new FullMatrix(result_as_array);
	}

	public IDoubleMatrix plus(double scalar) {
		var result_as_array = new ArrayList<ArrayList<Double>>();
		for (int i = 0; i < shape.rows; i++) {
			result_as_array.add(new ArrayList<Double>());
			for (int j = 0; j < shape.columns; j++) {
				result_as_array.get(i).add(get(i, j) + scalar);
			}
		}
		return new FullMatrix(result_as_array);
	}

	@Override
	public IDoubleMatrix plus(IDoubleMatrix other) {
		assert other.shape().hashCode() == this.shape().hashCode();
		ArrayList<ArrayList<Double>> result = new ArrayList<>();
		var other_data = other.data();

		for (int i = 0; i < this.shape().rows; i++) {
			result.add(new ArrayList<Double>());
			for (int j = 0; i < this.shape().columns; j++)
				result.get(i).add(other_data[i][j] + get(i, j));
		}

		return new FullMatrix(result);
	}

	@Override
	public IDoubleMatrix minus(IDoubleMatrix other) {
		return plus(other.times(-1));
	}

	@Override
	public IDoubleMatrix minus(double scalar) {
		return this.plus(-scalar);
	}

	public double normOne() {
		var result = new ArrayList<Double>();
		for (int i = 0; i < shape().columns; i++) {
			double sum_column = 0.;
			for (int j = 0; j < shape().rows; j++) {
				sum_column += abs(get(j, i));
			}
			result.add(sum_column);
		}
		return result.stream().reduce(0., (a, b) -> a > b ? a : b);
	}

	@Override
	public double normInfinity() {
		var result = new ArrayList<Double>();
		for (int i = 0; i < shape().rows; i++) {
			double sum_column = 0.;
			for (int j = 0; j < shape().columns; j++) {
				sum_column += abs(get(i, j));
			}
			result.add(sum_column);
		}
		return result.stream().reduce(0., (a, b) -> a > b ? a : b);
	}

	@Override
	public double frobeniusNorm() {
		double sum_of_ever_cell_squared = 0.;
		for (int i = 0; i < shape().rows; i++) {
			for (int j = 0; j < shape().columns; j++) {
				sum_of_ever_cell_squared += Math.pow(get(i, j), 2);
			}
		}
		return sqrt(sum_of_ever_cell_squared);
	}


	public Shape shape() {
		return shape;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("Matrix is: ").append(this.shape().rows).append("x").append(this.shape().columns)
						.append(".\n The matrix looks like:\n");
		for (int i = 0; i < this.shape().rows; i++) {
			for (int j = 0; j < this.shape().columns; j++)
				result.append(" ").append(this.get(i, j)).append("; ");
			result.append("\n");
		}

		return result.toString();
	}

	@Override
	public double[][] data() {
		var result = new double[shape.rows][shape.rows];
		for (int i = 0; i < shape.rows; i++) {
			for (int j = 0; i < shape.columns; j++) {
				result[i][j] = get(i, j);
			}
		}
		return result;
	}


}