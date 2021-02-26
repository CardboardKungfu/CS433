import java.util.Comparator;

import javax.lang.model.util.ElementScanner6;

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
		int max = -1;
		for (String str : strings)
			if(str.length() > max)
				max = str.length();
		int[] digits = new int[n];
		for(int e = max - 1; e >= 0; e--) {
			for(int i = 0; i < n; i++) {
				if(e >= strings[i].length())
					digits[i] = 0;
				else
					digits[i] = (strings[i].charAt(e) - 96);
			}
			countSortOnLowerCaseCharacters(strings, n, digits);
		}
	}

	private static void countSortOnLowerCaseCharacters(String[] strings, int n, int[] digits) { // complete this method
		int[] count = new int[27];
		String[] temp = new String[n];
		for(int i = 0; i < n; i++)
			count[digits[i]]++;
		for(int i = 1; i <= 26; i++)
			count[i] = count[i - 1] + count[i];
		for(int i = n - 1; i >= 0; i--) {
			temp[count[digits[i]] - 1] = strings[i];
			count[digits[i]]--;
		}
		for(int i = 0; i < n; i++)
			strings[i] = temp[i];
	}
}
