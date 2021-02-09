

public class InversionCounting {

	private final int mergedArray[];
	private final int array[];
	private final int n;
	private int totalInversions = 0;

	public InversionCounting(int[] array, int n) {
		this.array = array;
		this.mergedArray = new int[n];
		this.n = n;
	}

	public int bruteForce() {
		int count = 0;
		for (int i = 0; i < n; i++)
			for (int j = i + 1; j < n; j++)
				if (array[i] > array[j])
					count++;
		return count;
	}

	public int countInversions() {
		return countInversions(0, n - 1);
	}

	private int countInversions(int left, int right) { // complete this function
		if(left == right)
			return 0;
		int mid = (right + left) / 2;
		countInversions(left, mid);
		countInversions(mid + 1, right);
		int[] temp = new int[right - left + 1];
		int i = left, j = mid + 1, k = 0;
		while(i <= mid && j <= right) {
			if (array[i] < array[j]) //already sorted
				temp[k++] = array[i++];
			else if(array[i] > array[j]) { //flip.
				temp[k++] = array[j++];
				totalInversions += (mid - i + 1);
			}
			else
				temp[k++] = array[i++]; 
		}
		while(i <= mid) //first half longer
			temp[k++] = array[i++];
		while(j <= right) //second half longer
			temp[k++] = array[j++];
		int m = 0;
		for (int l = left; l <= right; l++)
			array[l] = temp[m++];
		return totalInversions;
	}
}
