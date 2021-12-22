package prep.interv.arrays;

public class TransposeMatrix {

    public static void main(String[] args) {
        int matrix[][] = new int[4][4];
        int n = matrix.length;
        int value = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = value;
                value++;
            }
        }

        int[][] result = transpose(matrix);
        printArray(result);
    }

    private static int[][] transpose(int[][] matrix) {
        int size = matrix.length;
        int transposedMatrix[][] = new int[size][size];
        System.out.println("Size: " + size);
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                transposedMatrix[j][i] = matrix[i][j];
            }
        }
        return transposedMatrix;
    }

    private static void printArray(int[][] matrix) {
        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
