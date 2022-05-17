package pl.edu.mimuw.matrix;

import java.util.ArrayList;

public class FullMatrix extends GeneralMatrix {
	private ArrayList<ArrayList<Double>> values = new ArrayList<>();


	public FullMatrix(double[][] values) {
		super(Shape.matrix(values.length, values.length > 0 ? values[0].length : -1));

		for (double[] row : values) {
			assert row != null;
			assert row.length == shape.columns;

			var to_insert = new ArrayList<Double>();
			for (double number : row) {
				to_insert.add(number);
			}

			this.values.add(to_insert);
		}
	}

	public FullMatrix(ArrayList<ArrayList<Double>> matrix) {
		super(Shape.matrix(matrix.size(), matrix.get(0).size()));
		this.values = matrix;
	}

	@Override
	public double get(int row, int column) {
		checkIfIndexInbounds(row, column);
		return values.get(row).get(column);
	}
}