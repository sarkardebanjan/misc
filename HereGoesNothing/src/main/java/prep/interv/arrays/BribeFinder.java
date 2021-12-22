package prep.interv.arrays;

import java.util.Arrays;
import java.util.List;

public class BribeFinder {

    public static void main(String[] args) {
        (new BribeFinder()).minimumBribes(Arrays.asList(2, 1, 5, 3, 4));
    }

    public static void minimumBribes(List<Integer> q) {
        // Write your code here
        if (null != q && q.size() > 0) {
            //Populate array for swaping
            int arr[] = new int[q.size()];
            int index = 0;
            for (int val : q) {
                arr[index] = val;
                index++;
            }

            //Actual logic
            int noOfBribes = 0;
            for (int i=arr.length-1; i>=0; i--) {
                if (arr[i] != i+1) {
                    if (arr[i-1] == (i+1)) {
                        noOfBribes += 1;
                        swap(arr, i - 1, i);
                    } else if (arr[i-2] == (i+1)) {
                        noOfBribes += 2;
                        swap(arr, i - 2, i - 1);
                        swap(arr, i - 1, i);
                    } else {
                        System.out.println("Too chaotic");
                        return;
                    }
                }
            }
            System.out.println(noOfBribes);
        }
    }

    public static void swap(int[] array, int pos1, int pos2) {
        int temp = array[pos1];
        array[pos1] = array[pos2];
        array[pos2] = temp;

        System.out.print("After swap: ");
        for (int i=0; i<array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
