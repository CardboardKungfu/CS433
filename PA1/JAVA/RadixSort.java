

public class RadixSort {

	private final int[] array;
	private final int n;

	public RadixSort(int array[], int length) {
		this.array = array;
		this.n = length;
	}

	private static void countSortOnDigits(int A[], int n, int digits[]) { // complete this function
		int[] count = new int[10];
		int[] temp = new int[n];
		for(int i = 0; i < n; i++)
			count[digits[i]]++;
		for(int i = 1; i <= 9; i++)
			count[i] = count[i - 1] + count[i];
		for(int i = n - 1; i >= 0; i--) {
			temp[count[digits[i]] - 1] = A[i];
			count[digits[i]]--;
		}
		for(int i = 0; i < n; i++)
			A[i] = temp[i];
	}

	private static void radixSortNonNeg(int A[], int n) { // complete this function
		int M = -1;
		for (int val : A)
			if(val > M)
				M = val;
		int[] digits = new int[n];
		int e = 1;
		while(M/e > 0) {
			for(int i = 0; i < n; i++)
				digits[i] = (A[i]/e) % 10;
			countSortOnDigits(A, n, digits);
			e = e * 10;
		}
	}

	public void radixSort() { // complete this function
		int negLen = 0;
		for(int elem : array)
			if(elem < 0)
				negLen++;
		int nonNegLen = n - negLen;
		int[] NEG = new int[negLen];
		int[] nonNEG = new int[nonNegLen];
		int negIndexer = 0;
		int nonNegIndexer = 0;
		for(int elem : array) {
			if(elem < 0)
				NEG[negIndexer++] = elem;
			else
				nonNEG[nonNegIndexer++] = elem;
		}
		radixSortNonNeg(nonNEG, nonNegLen);
		for(int i = 0; i < negLen; i++)
			NEG[i] = NEG[i] * -1;
		radixSortNonNeg(NEG, negLen);
		for(int i = 0; i < negLen; i++)
			NEG[i] = NEG[i] * -1;
		int arrIndexer = 0;
		for(int i = negLen - 1; i >= 0; i--)
			array[arrIndexer++] = NEG[i];
		for(int i = 0; i < nonNegLen; i++)
			array[arrIndexer++] = nonNEG[i];
	}
}
