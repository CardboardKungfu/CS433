public static void quickSort(int A[], int left, int right){
	if (left < right){
		int pivot = generatePivot(A, left, right);
		int[] partitionIndex = new int[2];//to hold both indexes
		partitionIndex = partition(A, left, right, pivot);
		quicksort(A, left, partitionIndex[0] - partitionIndex[1]);
		quicksort(A, partitionIndex[0] + 1, right);
	}
}
public static int partition(int A[], int left, int right, pivot){
	int pivotIndex = left;
	int partitionIndex = left-1
	int count = 0 //Used to find number equal to pivot
	for (int k=left;k<=right;k++){
		if(A[k]==pivot){
			pivotIndex = k;
			count++; //Add to count every time pivot is found
		}
		if(A[k]<= pivot){
			pivotIndex++;
		}
	}
	int temp = A[pivotIndex];
	A[pivotIndex] = A[partitionIndex];
	A[partitionIndex] = temp;
	int i = left;
	int j = right;
	while (i<j){
		if(i<=partitionIndex && A[i] <= pivot){
			i++;
		}
		if(j>partitionIndex && A[j]>pivot){
			j--;                                                                                                                                                                                                                                 
		}
		if (j<j){
			temp = A[i];
			A[i] = A[j];
			A[j] = temp
			i++;
			j--
		}
	}
	int l = pivotIndex
	Boolean found = false
	for (int k = left; k<l;k++){
		if (A[k]<pivot){
			found = false;
			while (l>k && found == false){ //Equal would not see a flip
				if (A[l] == pivot){
					//Swap values
					temp = A[l];
					A[l] = A[k];
					A[k] = temp;
					found = true;
				}
				l--;
			}
		}
	}
	int values[] = new int[partitionIndex, count] 
	return values; //return both indexes to omit multiple values
	}