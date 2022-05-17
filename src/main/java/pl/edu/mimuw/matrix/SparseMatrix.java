package pl.edu.mimuw.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

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

	// row1 size is bigger than row2 size
	private void sum_two_rows(ArrayList<MatrixCellValue> row1, ArrayList<MatrixCellValue> row2,
														ArrayList<MatrixCellValue> result) {
		if (row1.size() < row2.size())
			sum_two_rows(row2, row1, result);

		for (int i = 0; i < row1.size(); i++) {
			if (row2.size() > i) {
				if (row2.get(i).column == row1.get(i).column) {
					result.add(MatrixCellValue.cell(row1.get(i).row, row1.get(i).column,
									row1.get(i).value + row2.get(i).value));
				} else {
					result.add(row1.get(i));
					result.add(row2.get(i));
				}
			} else {
				result.add(row1.get(i));
			}
		}
	}

	@Override
	public IDoubleMatrix plus(IDoubleMatrix other) {
		if (other.getClass() == this.getClass()) {
			var other_sparse = (SparseMatrix) other;
			System.out.println("ADDing");
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
			return super.times(other);
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

	@Override
	public double get(int row, int column) {
		checkIfIndexInbounds(row, column);

		for (var row_of_cell : values_rows) {
			for (var cell : row_of_cell) {
				if (cell.row == row && cell.column == column)
					return cell.value;
				if (cell.row > row || cell.column > column)
					return 0.;
			}
		}
		return 0.;
	}
}
