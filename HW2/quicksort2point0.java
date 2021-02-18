public class quicksort2point0 {
    private void quicksort(int left, int right) {
        if(left < right) {
            int pivot = generatePivot(left, right);
            int partitionIndex = partition(left, right, pivot);
            quicksort(left, partitionIndex - 1);
            quicksort(partitionIndex + 1, right);
        }
    }

    public int partition(int[] array, int left, int right, int pivot) {
        int pivotIndex = left, partitionIndex = left - 1, pivotCount = 0;
        for(int k = left; k <= right; k++) {
            if(array[k] == pivot) {
                //This will end at the pivot that is farthest right. We will therefore pass all values equal to pivot
                pivotIndex = k;
                //Increment number for pivot. We need to know how many are equal.
                pivotCount++;
            }
            if(array[k] <= pivot)
                partitionIndex++;
        }
        //We now need to adjust to account for the number of numbers equal to pivot. We must adjust the partitionIndex by subtracting 
        //half of pivotCount.
        partitionIndex = partitionIndex - (pivotCount / 2);
        swap(pivotIndex, partitionIndex);
        int i = left, j = right, p = 0, q = 0;
        while(i < j) {
            while(i <= partitionIndex && array[i] <= pivot)
                i++;
            //If count is odd, this will account for it, with one more value going on the left
            while(i <= partitionIndex && array[i] == pivot && p < (pivotCount - (pivotCount / 2))) { 
                i++;
                p++;
            }
            //Adding the appropriate amount of median on the right side
            while(j > partitionIndex && array[j] == pivot && q < (pivotCount / 2)) { 
                j--;
                q++;
            }
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
