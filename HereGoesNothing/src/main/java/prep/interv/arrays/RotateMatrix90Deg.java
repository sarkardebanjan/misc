package prep.interv.arrays;

public class RotateMatrix90Deg {

    public static void main(String[] args) {
        (new RotateMatrix90Deg()).rotate();
    }

    private void rotate() {
        int n = 4;
        int matrix[][] = new int[n][n];
        fillData(matrix);
        System.out.println("Input: ");
        printData(matrix);

        int size = matrix.length - 1; // this is really the last index

        for (int layer = 0; layer < (matrix.length / 2); layer++) {
            for (int i = layer; i < size - layer; i++) {
                int topFence = matrix[layer][i];                  // starts at top left of layer
                int rightFence = matrix[i][size - layer];         // starts at top right of layer
                int bottomFence = matrix[size - layer][size - i]; // starts at bottom right of layer
                int leftFence = matrix[size - i][layer];          // starts at bottom left of layer

                // rotate 90 degrees clockwise element by element
                matrix[layer][i] = leftFence;                     // set value walking top fence
                matrix[i][size - layer] = topFence;               // set value walking right fence
                matrix[size - layer][size - i] = rightFence;      // set value walking bottom fence
                matrix[size - i][layer] = bottomFence;            // set value walking left fence
            }
        }

        System.out.println("\n\n90 Deg Rotated Matrix: ");
        printData(matrix);
    }

    private void printData(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void fillData(int matrix[][]) {
        int value = 1;
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix.length; j++) {
                matrix[i][j] = value;
                value++;
            }
        }
    }

}
