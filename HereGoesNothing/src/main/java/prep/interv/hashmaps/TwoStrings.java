package prep.interv.hashmaps;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class ResultTS {

    /*
     * Complete the 'twoStrings' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. STRING s1
     *  2. STRING s2
     */

    public static String twoStrings(String s1, String s2) {
        int[] alphabetsMarker = new int[26];
        String smaller, longer;
        if (s1.length() < s2.length()) {
            smaller = s1;
            longer = s2;
        } else {
            smaller = s2;
            longer = s1;
        }

        for (int i=0; i<smaller.length(); i++) {
            int pos = (int) smaller.charAt(i) - 97;
            if (pos < 26) {
                alphabetsMarker[pos]=1;
            }
        }

        for (int i=0; i<longer.length(); i++) {
            int pos = (int) longer.charAt(i) - 97;
            if (pos < 26 && alphabetsMarker[pos] == 1) {
                return "YES";
            }
        }
        return "NO";
    }

}

public class TwoStrings {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String s1 = bufferedReader.readLine();

                String s2 = bufferedReader.readLine();

                String result = ResultTS.twoStrings(s1, s2);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
