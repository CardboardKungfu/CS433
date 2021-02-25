import java.util.Comparator;

public class Point {

	static class PointComparator implements Comparator<Point> {
		@Override
		public int compare(Point arg1, Point arg2) {
			return arg1.x <= arg2.x ? -1 : 1;
		}
	}

	public double x, y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public final double distance(Point arg) {
		double diffX = arg.x - x;
		double diffY = arg.y - y;
		double dist = Math.sqrt(diffX * diffX + diffY * diffY);
		return dist;
	}

	public final String toString() {
		return String.format("(%d,%d)", x, y);
	}
}
