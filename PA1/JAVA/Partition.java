

import java.util.Random;

public class Partition {

	final int array[];
	final int n;

	static Random rand;

	public Partition(int[] array, int n) {
		this.array = array;
		this.n = n;
		rand = new Random(System.currentTimeMillis());
	}

	protected void swap(int x, int y) {
		int temp = array[x];
		array[x] = array[y];
		array[y] = temp;
	}

	protected int generateRandomPivot(int left, int right) {
		int pivotIndex = left + rand.nextInt(right - left + 1);
		return array[pivotIndex];
	}

	protected int generateMedianOf3Pivot(int left, int right) {
		int mid = (left + right) / 2;

		if (array[left] > array[mid])
			swap(left, mid);

		if (array[left] > array[right])
			swap(left, right);

		if (array[mid] > array[right])
			swap(mid, right);

		return array[mid];
	}

	public int partition(int left, int right, int pivot) { // complete this function
		int pivotIndex = left, partitionIndex = left - 1;
		for(int k = left; k <= right; k++) {
			if(array[k] == pivot)
				pivotIndex = k;
			if(array[k] <= pivot)
				partitionIndex++;
		}
		swap(pivotIndex, partitionIndex);
		int i = left, j = right;
		while(i < j) {
			while(i <= partitionIndex && array[i] <= pivot)
				i++;
			while(j > partitionIndex && array[j] > pivot)
				j--;
			if(i < j) {
				swap(i, j);
				i++;
				j--;
			}
		}
		return partitionIndex;
	}
}
