

import java.util.Arrays;

public class TestCorrectness {

	private static void testSorting(int array[], int n) throws Exception {
		int temp[] = new int[n];

		System.out.println("Original array:                  " + Arrays.toString(array));

		for (int i = 0; i < n; i++)
			temp[i] = array[i];
		new MergeSort(temp, n).mergesort();
		System.out.println("MergeSorted array:               " + Arrays.toString(temp));

		for (int i = 0; i < n; i++)
			temp[i] = array[i];
		new QuickSort(temp, n).quicksortMedianOf3();
		System.out.println("QuickSorted (median of 3) array: " + Arrays.toString(temp));

		for (int i = 0; i < n; i++)
			temp[i] = array[i];
		new QuickSort(temp, n).quicksortRandom();
		System.out.println("QuickSorted (random) array:      " + Arrays.toString(temp));

		for (int i = 0; i < n; i++)
			temp[i] = array[i];
		new RadixSort(temp, n).radixSort();
		System.out.println("RadixSorted array:               " + Arrays.toString(temp));
	}

	private static void testSelection(int array[], int n) throws Exception {
		int mergeArray[] = new int[n];
		
		for (int i = 0; i < n; i++)
			mergeArray[i] = array[i];
		new MergeSort(mergeArray, n).mergesort();
		
		int selArray[] = new int[n];
		for (int k = 1; k <= n; k++) {
			for (int j = 0; j < n; j++)
				selArray[j] = array[j];
			Selection selection = new Selection(selArray, n);
			int answer = selection.select(k);
			System.out.printf(k + "th smallest: %d", answer);
			if (answer != mergeArray[k - 1])
				System.out.print("; Selection code does not work for k = " + k);
			System.out.println();
		}
	}

	private static void testInversions(int array[], int n) {
		System.out.println("Array is: " + Arrays.toString(array));
		System.out.println("Number of inversions is: " + new InversionCounting(array, n).countInversions());
	}

	public static void main(String args[]) throws Exception {
		System.out.println("*** Correctness Test ***\n");
		int sorting[] = { 19, 1, 12, 100, 7, 8, 4, -10, 14, -1, 97, -1009, 4210 };
		int n = sorting.length;
		int selection[] = new int[n];
		for (int i = 0; i < n; i++)
			selection[i] = sorting[i];
		testSorting(sorting, n);
		System.out.println();
		testSelection(selection, n);
		System.out.println();
		testInversions(sorting, n);
	}
}
