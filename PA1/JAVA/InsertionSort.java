public class InsertionSort {

	public static void insertionSort(int[] array, int left, int right) {
		for (int i = left + 1; i <= right; i++) {
			int j = i, temp = array[j];
			while (j > left && temp < array[j - 1]) {
				array[j] = array[j - 1];
				j--;
			}
			array[j] = temp;
		}
	}
}
