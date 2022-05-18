package pl.edu.mimuw.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class SparseMatrix extends GeneralMatrix {
	ArrayList<ArrayList<MatrixCellValue>> values_rows;
	ArrayList<ArrayList<MatrixCellValue>> values_columns;


	public SparseMatrix(Shape shape, MatrixCellValue... values) {
		super(shape);

		assert values != null && values.length != 0;


		var values_sorted_by_columns = values.clone();
		var values_sorted_by_rows = values;

		Arrays.sort(values_sorted_by_columns, new Comparator<MatrixCellValue>() {

			@Override
			public int compare(MatrixCellValue matrixCellValue1, MatrixCellValue matrixCellValue2) {
				return Integer.compare(matrixCellValue1.column, matrixCellValue2.column) == 0 ?
								Integer.compare(matrixCellValue1.row, matrixCellValue2.row) :
								Integer.compare(matrixCellValue1.column, matrixCellValue2.column);
			}
		});

		Arrays.sort(values_sorted_by_rows, new Comparator<MatrixCellValue>() {

			@Override
			public int compare(MatrixCellValue matrixCellValue1, MatrixCellValue matrixCellValue2) {
				return Integer.compare(matrixCellValue1.row, matrixCellValue2.row) == 0 ?
								Integer.compare(matrixCellValue1.column, matrixCellValue2.column) :
								Integer.compare(matrixCellValue1.row, matrixCellValue2.row);
			}
		});

		values_columns = new ArrayList<>();
		values_columns.add(new ArrayList<>());
		values_rows = new ArrayList<>();
		values_rows.add(new ArrayList<>());

		for (int i = 0; i < values_sorted_by_rows.length; i++) {
			assert values_sorted_by_columns[i].column >= 0 && values_sorted_by_columns[i].row >= 0 &&
							values_sorted_by_columns[i].column < shape.columns &&
							values_sorted_by_columns[i].row < shape.rows;

			if (i != 0 && values_sorted_by_columns[i - 1].column != values_sorted_by_columns[i].column) {
				values_columns.add(new ArrayList<>());
			}

			values_columns.get(values_columns.size() - 1).add(values_sorted_by_columns[i]);

			if (i != 0 && values_sorted_by_rows[i - 1].row != values_sorted_by_rows[i].row) {
				values_rows.add(new ArrayList<>());
			}

			values_rows.get(values_rows.size() - 1).add(values_sorted_by_rows[i]);
		}


	}

	// array1 size is bigger than array2 size
	private void sum_two_rows(ArrayList<MatrixCellValue> array1, ArrayList<MatrixCellValue> array2,
														ArrayList<MatrixCellValue> result) {
		if (array1.size() < array2.size())
			sum_two_rows(array2, array1, result);

		for (int i = 0; i < array1.size(); i++) {
			if (array2.size() > i) {
				if (array2.get(i).column == array1.get(i).column) {
					result.add(MatrixCellValue.cell(array1.get(i).row, array1.get(i).column,
									array1.get(i).value + array2.get(i).value));
				} else {
					result.add(array1.get(i));
					result.add(array2.get(i));
				}
			} else {
				result.add(array1.get(i));
			}
		}
	}

	@Override
	public IDoubleMatrix plus(IDoubleMatrix other) {
		if (other.getClass() == this.getClass()) {
			var other_sparse = (SparseMatrix) other;

			assert other_sparse.shape.columns == shape.columns && other_sparse.shape.rows == shape.rows;

			if (other_sparse.values_rows.size() > values_rows.size())
				return other_sparse.plus(this);

			ArrayList<MatrixCellValue> matrix_cells = new ArrayList<>();
			ArrayList<MatrixCellValue> empty_row = new ArrayList<>();

			for (int i = 0; i < values_rows.size(); i++) {
				if (other_sparse.values_rows.size() > i) {
					if (other_sparse.values_rows.get(i).get(0).row == values_rows.get(i).get(0).row) {
						sum_two_rows(other_sparse.values_rows.get(i), values_rows.get(i), matrix_cells);
					} else {
						sum_two_rows(values_rows.get(i), empty_row, matrix_cells);
						sum_two_rows(other_sparse.values_rows.get(i), empty_row, matrix_cells);
					}
				} else {
					sum_two_rows(values_rows.get(i), empty_row, matrix_cells);
				}
			}

			return new SparseMatrix(shape, matrix_cells.toArray(new MatrixCellValue[0]));
		} else {
			return super.plus(other);
		}
	}

	@Override
	public IDoubleMatrix minus(IDoubleMatrix other) {
		if (other.getClass() == this.getClass()) {
			return this.plus(other.times(-1));
		} else {
			return super.plus(other.times(-1));
		}
	}

	@Override
	public IDoubleMatrix plus(double scalar) {
		return new SparseMatrix(shape, values_rows.stream().flatMap(row -> row.stream()
										.map(cell -> MatrixCellValue.cell(cell.row, cell.column, cell.value + scalar)))
						.toArray(MatrixCellValue[]::new));
	}

	@Override
	public IDoubleMatrix times(double scalar) {
		return new SparseMatrix(shape, values_rows.stream().flatMap(row -> row.stream()
										.map(cell -> MatrixCellValue.cell(cell.row, cell.column, cell.value * scalar)))
						.toArray(MatrixCellValue[]::new));
	}

	private double multiply_row_column(ArrayList<MatrixCellValue> array1,
																		 ArrayList<MatrixCellValue> array2) {
		double sum = 0.;

		for (int i = 0; i < array1.size(); i++) {
			if (array2.size() > i) {
				if (array2.get(i).row == array1.get(i).column) {
					sum += array1.get(i).value * array2.get(i).value;
				}
			}
		}
		return sum;
	}

	@Override
	public IDoubleMatrix times(IDoubleMatrix other) {
		assert shape.columns == other.shape().rows;

		if (other.getClass() == this.getClass()) {
			var other_sparse = (SparseMatrix) other;
			ArrayList<MatrixCellValue> matrix_cells = new ArrayList<>();

			for (int i = 0; i < values_rows.size(); i++) {
				for (int j = 0; j < other_sparse.values_columns.size(); j++) {
					matrix_cells.add(MatrixCellValue.cell(values_rows.get(i).get(0).row,
									other_sparse.values_columns.get(j).get(0).column,
									multiply_row_column(values_rows.get(i), other_sparse.values_columns.get(j))));
				}
			}

			return new SparseMatrix(Shape.matrix(shape.rows, other_sparse.shape.columns), matrix_cells.toArray(new MatrixCellValue[0]));
		} else {
			return super.times(other);
		}

	}

	@Override
	public double get(int row, int column) {
		checkIfIndexInbounds(row, column);

		for (var row_of_cell : values_rows) {
			for (var cell : row_of_cell) {
				if (cell.row == row && cell.column == column)
					return cell.value;
			}
		}
		return 0.;
	}

	public double normOne() {
		return values_columns.stream().map(column -> column.stream().map(cell -> cell.value)
										.reduce(0., (acc, value) -> acc + abs(value)))
						.reduce(0., (acc, value) -> acc > value ? acc : value);
	}

	@Override
	public double normInfinity() {
		return values_rows.stream().map(column -> column.stream().map(cell -> cell.value)
										.reduce(0., (acc, value) -> acc + abs(value)))
						.reduce(0., (acc, value) -> acc > value ? acc : value);
	}

	@Override
	public double frobeniusNorm() {
		return sqrt(values_columns.stream().map(column -> column.stream().map(cell -> cell.value)
						.reduce(0., (acc, value) -> acc + value * value)).reduce(Double::sum).get());
	}

}
