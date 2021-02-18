public class q4 {
	public static void quickSort(int A[], int left, int right){
		if(left < right) {
			int pivot = generatePivot(A, left, right);
			int partitionIndex = partition(A, left, right, pivot);
			quicksort(A, left, partitionIndex-1);
			quicksort(A, partitionIndex + 1, right);
		}
	}
	public static int partition(int A[], int left, int right, int pivot) {
		int pivotIndex = left, partitionIndex = (left - 1);
		int pivotCount = 0;//We need this to correctly distribute the pivot
		for (int k = left; k <= right; k++){
			if(A[k] == pivot){
				pivotIndex = k; //This will end at the pivot that is farthest right. We will therefore pass all values equal to pivot
				pivotCount++; //Increment number for pivot. We need to know how many are equal.

			}
			if(A[k]<=pivot){
				partitionIndex++; //we leave this as it is, though we will adjust this at the end to account for number equal to pivot
			}
		}
		//We now need to adjust to account for the number of numbers equal to pivot. We must adjust the partitionIndex by subtracting 
		//half of pivotCount.
		partitionIndex = partitionIndex - (pivotCount / 2);
		int temp = A[pivotIndex];
		A[pivotIndex] = A[partitionIndex];
		A[partitionIndex] = temp;
		int i = left, j = right;
		int p = 0;
		int q = 0; //p and q keep track of pivots on each side
		while (i<j){
			
			while (i<=partitionIndex && A[i]<pivot){ //Notice that this does not include values equal to pivot. We need to split them evenly.
				i++;
			}
			while (i<=partitionIndex && A[i]==pivot && p<(pivotCount-(pivotCount/2))) { //If count is odd, this will account for it, with one more value going on the left
				i++;
				p++;

			}
			while (j>partitionIndex && A[j]==pivot && q<(pivotCount/2){ 
			j--;
			q++;

			}
			while(j>partitionIndex && A[j]>pivot){
				j--;
			}
			if (i<j){
				//It should never happen where both are equal to pivot, since the pivot count would account for this
				int temp2 = A[i];
				A[i] = A[j];
				A[j] = temp2;
				i++;
				j--;
			}
		}
		return partitionIndex;
	}

}