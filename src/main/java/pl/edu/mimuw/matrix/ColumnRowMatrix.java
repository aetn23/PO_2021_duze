package pl.edu.mimuw.matrix;

import java.util.ArrayList;

public abstract class ColumnRowMatrix extends GeneralMatrix {
	protected ArrayList<Double> values;

	public ColumnRowMatrix(ArrayList<Double> values, Shape shape) {
		super(shape);

		assert values != null;

		this.values = values;
	}

	public ColumnRowMatrix(double[] values, Shape shape) {
		super(shape);

		assert values != null;

		this.values = new ArrayList<Double>();
		for (double value : values) this.values.add(value);
	}
}
