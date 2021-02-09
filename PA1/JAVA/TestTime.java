

import java.util.Random;

public class TestTime {

	static Random rand;

	private static void testIfSorted(int A[], int len, char s) throws Exception {
		for (int i = 0; i < len - 1; i++)
			if (A[i] > A[i + 1]) {
				if (s == 'M')
					throw new Exception("MergeSort code is incorrect");
				else if (s == '3')
					throw new Exception("QuickSort (median of 3) code is incorrect");
				else if (s == 'Q')
					throw new Exception("QuickSort (randomized) code is incorrect");
				else if (s == 'R')
					throw new Exception("RadixSort code is incorrect");
			}
	}

	private static int getRandom() {
		return rand.nextInt() / 3;
	}

	private static void compareSorting() throws Exception {
		System.out.println("Length, MergeSort, QuickSort (median of 3), QuickSort (randomized), RadixSort");
		double mergeAvg = 0, quickMedianOf3Avg = 0, quickRandomAvg = 0, radixRandomAvg = 0;
		int numExec = 0;
		for (int num = 500000; num <= 50000000; num *= 1.3) {
			int[] array = new int[num];
			int[] temp = new int[num];
			for (int i = 0; i < num; i++)
				array[i] = getRandom();

			double timeStart, timeEnd;

			for (int i = 0; i < num; i++)
				temp[i] = array[i];
			timeStart = System.currentTimeMillis();
			new MergeSort(temp, num).mergesort();
			timeEnd = System.currentTimeMillis();
			testIfSorted(temp, num, 'M');
			mergeAvg += (timeEnd - timeStart);
			System.out.print(num + ", " + (timeEnd - timeStart));

			for (int i = 0; i < num; i++)
				temp[i] = array[i];
			timeStart = System.currentTimeMillis();
			new QuickSort(temp, num).quicksortMedianOf3();
			timeEnd = System.currentTimeMillis();
			testIfSorted(temp, num, '3');
			quickMedianOf3Avg += (timeEnd - timeStart);
			System.out.print(", " + (timeEnd - timeStart));

			for (int i = 0; i < num; i++)
				temp[i] = array[i];
			timeStart = System.currentTimeMillis();
			new QuickSort(temp, num).quicksortRandom();
			timeEnd = System.currentTimeMillis();
			testIfSorted(temp, num, 'Q');
			quickRandomAvg += (timeEnd - timeStart);
			System.out.print(", " + (timeEnd - timeStart));

			for (int i = 0; i < num; i++)
				temp[i] = array[i];
			timeStart = System.currentTimeMillis();
			new RadixSort(temp, num).radixSort();
			timeEnd = System.currentTimeMillis();
			testIfSorted(temp, num, 'R');
			radixRandomAvg += (timeEnd - timeStart);
			System.out.println(", " + (timeEnd - timeStart));

			numExec++;
		}
		System.out.printf("\nMergeSort average time is: %.2f millisecs%n", mergeAvg / numExec);
		System.out.printf("QuickSort (median of 3) average time is: %.2f millisecs%n", quickMedianOf3Avg / numExec);
		System.out.printf("QuickSort (randomized) average time is: %.2f millisecs%n", quickRandomAvg / numExec);
		System.out.printf("RadixSort average time is: %.2f millisecs%n", radixRandomAvg / numExec);
	}

	private static void compareSelection() throws Exception {
		double randomAvg = 0, radixSortedAvg = 0;
		int numExec = 0;
		System.out.println("Length, Selection via RadixSort, Randomized Selection");
		for (int num = 500000; num <= 50000000; num *= 1.3) {
			int[] array = new int[num];
			int[] radix = new int[num];
			for (int i = 0; i < num; i++)
				radix[i] = array[i] = getRandom();

			double timeStart, timeEnd;
			int K[] = new int[(int) Math.log(num)];
			int lenK = K.length;
			for (int i = 0; i < lenK; i++)
				K[i] = rand.nextInt(num);

			timeStart = System.currentTimeMillis();
			new RadixSort(radix, num).radixSort();
			timeEnd = System.currentTimeMillis();
			radixSortedAvg += (timeEnd - timeStart) * lenK;
			System.out.print(num + ", " + (timeEnd - timeStart));

			double selTime = 0;
			int selArray[] = new int[num];
			for (int i = 0; i < lenK; i++) {
				for (int j = 0; j < num; j++)
					selArray[j] = array[j];
				
				timeStart = System.currentTimeMillis();
				Selection selection = new Selection(selArray, num);
				int answer = selection.select(K[i]);
				timeEnd = System.currentTimeMillis();
				
				selTime += (timeEnd - timeStart);
				if (answer != radix[K[i] - 1])
					throw new Exception("Selection code is incorrect");
			}
			randomAvg += selTime;
			numExec += lenK;
			System.out.printf(", %.2f%n", selTime / lenK);
		}
		System.out.printf("\nSelection using RadixSort average time is: %.2f millisecs%n", radixSortedAvg / numExec);
		System.out.printf("Selection using random pivot average time is: %.2f millisecs%n", randomAvg / numExec);
	}

	private static void compareInversion() throws Exception {
		double mergeSortAvg = 0, bruteForceAvg = 0;
		int numExec = 0;
		System.out.println("Length, BruteForce Inversion, MergeSort Inversion");
		for (int num = 10000; num <= 300000; num *= 1.3) {
			int array[] = new int[num];
			for (int i = 0; i < num; i++)
				array[i] = getRandom();

			double timeStart, timeEnd;
			InversionCounting invCount = new InversionCounting(array, num);

			timeStart = System.currentTimeMillis();
			int count = invCount.bruteForce();
			timeEnd = System.currentTimeMillis();
			bruteForceAvg += (timeEnd - timeStart);
			System.out.print(num + ", " + (timeEnd - timeStart));

			timeStart = System.currentTimeMillis();
			if (count != invCount.countInversions())
				throw new Exception("Inversion Counting code is incorrect");
			timeEnd = System.currentTimeMillis();
			mergeSortAvg += (timeEnd - timeStart);
			System.out.println(", " + (timeEnd - timeStart));
			numExec++;
		}
		System.out.printf("\nBruteForce average time is: %.2f millisecs%n", bruteForceAvg / numExec);
		System.out.printf("MergeSort Inversion average time is: %.2f millisecs%n", mergeSortAvg / numExec);
	}

	public static void main(String args[]) throws Exception {
		rand = new Random(System.currentTimeMillis());
		System.out.println("*** Time Test Sorting ***\n");
		compareSorting();
		System.out.println("\n*** Time Test Selection ***\n");
		compareSelection();
		System.out.println("\n*** Time Test Inversion ***\n");
		compareInversion();
	}
}
