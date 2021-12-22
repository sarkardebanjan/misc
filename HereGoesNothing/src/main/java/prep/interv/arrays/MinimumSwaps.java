package prep.interv.arrays;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MinimumSwaps {

    // Complete the minimumSwaps function below.
    static int minimumSwaps(int[] arr) {
        int min = arr[0];
        int minPos = 0;
        int minSwaps = 0;
        
        for (int i=0; i < arr.length; i++) {
            if (min > arr[i]) {
                min = arr[i];
                minPos = i;
            }
        }
        if (minPos != 0) {
            minSwaps++;
            swap(arr, 0, minPos);
        }

        for (int i=1; i<arr.length; i++) {
            int pos = arr[i] - arr[0];
            while(arr[pos] != arr[i]) {
                minSwaps++;
                swap(arr, pos, i);
                pos = arr[i] - arr[0];
            }
        }
        return minSwaps;
    }

    private static void swap(int[] arr, int pos1, int pos2) {
        int temp = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = temp;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int res = minimumSwaps(arr);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

