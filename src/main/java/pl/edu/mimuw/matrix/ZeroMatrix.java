package pl.edu.mimuw.matrix;

public class ZeroMatrix extends ConstantMatrix {

	public ZeroMatrix(Shape shape) {
		super(shape, 0.);
	}

	@Override
	public IDoubleMatrix times(IDoubleMatrix other) {
		return new ZeroMatrix(shape);
	}

	@Override
	public IDoubleMatrix times(double scalar) {
		return new ZeroMatrix(shape);
	}

	@Override
	public IDoubleMatrix plus(IDoubleMatrix other) {
		return other;
	}

	@Override
	public IDoubleMatrix plus(double scalar) {
		return new ConstantMatrix(shape, scalar);
	}

	@Override
	public double normOne() {
		return 0.;
	}
	@Override
	public double normInfinity() {
		return 0.;
	}

	@Override
	public double frobeniusNorm() {
		return 0.;
	}

}
