import java.util.Random;

public class TestTime {

	final static int[] LARGE_POINT_SIZES = { 10, 50, 100, 500, 1000, 2000, 5000, 10000, 20000, 50000, 100000 };
	final static int[] LARGE_STRING_SIZES = { 1000000, 5000000, 10000000, 15000000, 20000000, 25000000 };

	private static void testClosestPoints() throws Exception {
		System.out.println("****************** Time Test Closest Pair of Points ******************\n");
		Random rand = new Random(System.currentTimeMillis());
		for (int numPoints : LARGE_POINT_SIZES) {

			Point[] points = new Point[numPoints];
			for (int j = 0; j < numPoints; j++)
				points[j] = new Point(rand.nextInt(), rand.nextInt());

			long startTime = System.currentTimeMillis();
			Point[] bruteForce = ClosestPairOfPoints.bruteForce(points);
			long timeBruteForce = System.currentTimeMillis() - startTime;

			startTime = System.currentTimeMillis();
			Point[] nlogn = ClosestPairOfPoints.findClosestPair(points);
			long timenlogn = System.currentTimeMillis() - startTime;

			double distBruteForce = bruteForce[0].distance(bruteForce[1]);
			double distnlogn = nlogn[0].distance(nlogn[1]);
			if (distBruteForce == distnlogn) {
				System.out.println("Time to find closest pair among " + numPoints
						+ " points using brute-force strategy = " + timeBruteForce + " (may vary with each execution)");
				System.out.println("Time to find closest pair among " + numPoints
						+ " points using divide & conquer strategy = " + timenlogn + " (may vary with each execution)");
				System.out.println();
			} else
				throw new Exception("Something is wrong!");
		}
	}

	private static void testStringSorter() throws Exception {
		System.out.println("****************** Time Test String Sorting ******************\n");
		long timeMerge = 0, timeRadix = 0;
		Random rand = new Random(System.currentTimeMillis());
		int maxLength = 25;
		for (int size : LARGE_STRING_SIZES) {
			String[] radixSortStrings = new String[size];
			String[] mergeSortStrings = new String[size];
			for (int i = 0; i < size; i++) {
				int stringLength = 1 + rand.nextInt(maxLength);
				StringBuilder str = new StringBuilder();
				for (int j = 0; j < stringLength; j++)
					str.append((char) (97 + rand.nextInt(26)));

				radixSortStrings[i] = mergeSortStrings[i] = str.toString();
			}

			long startTime = System.currentTimeMillis();
			StringSorter.mergeSort(mergeSortStrings, size);
			timeMerge = System.currentTimeMillis() - startTime;

			startTime = System.currentTimeMillis();
			StringSorter.radixSort(radixSortStrings, size);
			timeRadix = System.currentTimeMillis() - startTime;

			for (int i = 0; i < radixSortStrings.length - 1; i++) {
				if (mergeSortStrings[i].compareTo(mergeSortStrings[i + 1]) > 0)
					throw new Exception("Something wrong with merge sort");
				if (radixSortStrings[i].compareTo(radixSortStrings[i + 1]) > 0)
					throw new Exception("Something wrong with radix sort");
			}

			System.out.println(
					"Merge-sort time for " + size + " strings = " + timeMerge + " (may vary with each execution)");
			System.out.println(
					"Radix-sort time for " + size + " strings = " + timeRadix + " (may vary with each execution)");
			System.out.println();
		}
	}

	public static void main(String[] args) throws Exception {
		testClosestPoints();
		testStringSorter();
	}
}
