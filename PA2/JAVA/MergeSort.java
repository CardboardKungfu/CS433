import java.util.Comparator;

public class MergeSort<T> {

	private final T array[];
	private final T mergedArray[];
	private final int n;

	@SuppressWarnings("unchecked")
	public MergeSort(T[] array, int n) {
		this.array = array;
		this.n = n;
		this.mergedArray = (T[]) new Object[n];
	}

	public void mergesort(Comparator<T> comparator) {
		mergesort(0, n - 1, comparator);
	}

	private void mergesort(int left, int right, Comparator<T> comparator) {
		if (left == right)
			return;
		int mid = (left + right) / 2;
		mergesort(left, mid, comparator);
		mergesort(mid + 1, right, comparator);
		int i = left;
		while (i <= right) {
			mergedArray[i] = array[i];
			i++;
		}
		i = left;
		int j = mid + 1, k = left;
		while (i <= mid && j <= right)
			array[k++] = comparator.compare(mergedArray[j], mergedArray[i]) < 0 ? mergedArray[j++] : mergedArray[i++];
		while (i <= mid)
			array[k++] = mergedArray[i++];
	}
}
