
public class smallestMissingInt {

    private static int missInt = -1;
    private static int[] A = {0, 1, 2, 3, 5, 6, 7, 8, 9, 11, 12, 13, 14};

    public static void missingInt(int left, int right) {
        /*
            Left greater than right means we've reached the smallest section without finding
            a match. Thus, the smallest missing number lies one to the right of, well, right.
        */
        if(left > right) {
            missInt = right + 1;
            return;
        }
        /*
            Since we're dealing with a sorted array of positive integers, 
            if the leftmost value isn't the same as the leftmost index, 
            then we've skipped a value and so it must be left
        */
        if(left != A[left]) {
            missInt = left;
            return;
        }
        int mid = (left + right) / 2;
        /*
            When the middle index is the same as the value at mid, we know
            that the left side is sorted and goes from 0, 1, 2, 3, ... , mid for example
            thus, we know to search on the right side. However, if it doesn't equal mid, 
            we know to search on the left.
        */
        if(mid == A[mid])
            missingInt(mid + 1, right);
        else
            missingInt(left, mid);
    }

    public static void main(String[] args) {
        int n = A.length;
        int left = 0;
        int right = n - 1;
        /*
            Since we're dealing with a sorted array of positive integers, 
            if zero isn't in the first spot, then it won't show up 
            anywhere else, thus, zero would be the smallest missing integer 
        */
        if(A[0] != 0) {
            missInt = 0;
            System.out.println(missInt);
            return;
        }
        /*
            If the last value == last index, 
            then missInt = length of array 'n' since we're looking at a sorted
            array where each value is the same as the index. Therefore, the 
            smallest missing value lies beyond the array, thus 'n' 
        */
        if(A[n - 1] == n - 1) {
            missInt = n;
            System.out.println(missInt);
            return;
        }
        missingInt(left, right);
        System.out.println(missInt);
    }
}