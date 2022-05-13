package pl.edu.mimuw.matrix;


public abstract class GeneralMatrix implements IDoubleMatrix {

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Matrix is: ").append(this.shape().rows).append("x").append(this.shape().columns).append(".\n The matrix looks like:\n");
        for (int i = 0; i < this.shape().rows; i++) {
            for (int j = 0; j < this.shape().columns; j++)
                result.append(" ").append(this.get(i, j)).append("; ");
            result.append("\n");
        }

        return result.toString();
    }
}