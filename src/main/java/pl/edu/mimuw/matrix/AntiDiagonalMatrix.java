package pl.edu.mimuw.matrix;

public class AntiDiagonalMatrix extends MatrixWithDiagonal{
	@Override
	public double get(int row, int column) {
		return 0;
	}
}
