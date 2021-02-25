import java.util.Comparator;

public class StringSorter {

	static class StringComparator implements Comparator<String> {

		@Override
		public int compare(String arg1, String arg2) {
			return arg1.compareTo(arg2);
		}
	}

	public static void mergeSort(String[] strings, int n) {
		MergeSort<String> mgSort = new MergeSort<String>(strings, n);
		mgSort.mergesort(new StringComparator());
	}

	public static void radixSort(String[] strings, int n) { // complete this method
	}

	private static void countSortOnLowerCaseCharacters(String[] strings, int n, int[] digits) { // complete this method
	}
}
