package prep.interv.hashmaps;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class ResultSA {

    /*
     * Complete the 'sherlockAndAnagrams' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     */

    public static int sherlockAndAnagrams(String s) {
        List<String> allPossibleSubstrings = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                allPossibleSubstrings.add(s.substring(i, j));
            }
        }

        int count = 0;

        for (int i = 0; i < allPossibleSubstrings.size(); i++) {
            String s1 = allPossibleSubstrings.get(i);
            String sortedS1 = sort(s1);
            for (int j = i + 1; j < allPossibleSubstrings.size(); j++) {
                String s2 = allPossibleSubstrings.get(j);
                if (s1.length() == s2.length()) {
                    String sortedS2 = sort(s2);
                    if (sortedS1.equalsIgnoreCase(sortedS2)) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    public static String sort(String s) {
        char[] str = s.toCharArray();
        int n = str.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (str[j] > str[j + 1]) {
                    char temp = str[j];
                    str[j] = str[j + 1];
                    str[j + 1] = temp;
                }
            }
        }
        return String.valueOf(str);
    }

}

public class SherlockAndAnagrams {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String s = bufferedReader.readLine();

                int result = ResultSA.sherlockAndAnagrams(s);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
