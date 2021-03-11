class subsetCount {
    public static void main(String[] args) {
        char[] X = {'G', 'A', 'X', 'T', 'Y', 'A', 'W', 'B', 'G', 'T', 'A', 'U', 'G', 'B', 'T', 'A', 'B', 'G', 'R', 'G', 'T', 'A', 'X', 'B'};
        char[] Y = {'A', 'X'};
        int n = X.length;
        int m = Y.length;
        int target = 0;
        int targetIndex, subCount;

        for(int i = 0; i < n; i++) {
            targetIndex = target % m; 
            if(X[i] == Y[targetIndex])
                target++;
        }

        subCount = target / m;
        System.out.println("Subcount is: " + subCount);
    }
}