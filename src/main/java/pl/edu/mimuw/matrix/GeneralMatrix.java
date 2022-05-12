package pl.edu.mimuw.matrix;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class GeneralMatrix implements IDoubleMatrix {
    private int rows, columns;
    private ArrayList<ArrayList<Double>> values = new ArrayList<ArrayList<Double>>();


    public GeneralMatrix(double[][] values) {
        assert values.length > 0 && values[0].length > 0;
        rows = values.length;
        columns = values[0].length;

        for (double[] row : values) {
            assert row.length == rows;

            var to_insert = new ArrayList<Double>();
            for (double number : row) {
                to_insert.add(number);
            }

            this.values.add(to_insert);
        }
    }

    public GeneralMatrix(ArrayList<ArrayList<Double>> matrix) {
        this.columns = matrix.size();
        this.rows = matrix.get(0).size();
        this.values = matrix;
    }


    @Override
    public IDoubleMatrix times(IDoubleMatrix other) {
        return null;
    }

    @Override
    public IDoubleMatrix times(double scalar) {
        var multiplication_result = this.values.stream().map(row -> (row.stream().map(value -> value * scalar))
                .collect(Collectors.toCollection(ArrayList::new))).collect(Collectors.toCollection(ArrayList::new));
        return new GeneralMatrix(multiplication_result);
    }

    @Override
    public IDoubleMatrix plus(IDoubleMatrix other) {
        return null;
    }

    @Override
    public IDoubleMatrix plus(double scalar) {
        var sum_result = this.values.stream().map(row -> (row.stream().map(value -> value + scalar))
                .collect(Collectors.toCollection(ArrayList::new))).collect(Collectors.toCollection(ArrayList::new));
        return new GeneralMatrix(sum_result);
    }

    @Override
    public IDoubleMatrix minus(IDoubleMatrix other) {
        return null;
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
        return Arrays.stream(values.toArray()).toArray();
    }

    @Override
    public double normOne() {
        return 0;
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
        return null;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Matrix is: ").append(rows).append("x").append(columns).append(".\n The matrix looks like:\n");
        for (var row : values) {
            for (var value : row)
                result.append(" ").append(value).append("; ");
            result.append("\n");
        }

        return result.toString();
    }
}
