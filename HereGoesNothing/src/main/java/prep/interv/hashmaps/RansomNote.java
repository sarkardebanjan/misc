package prep.interv.hashmaps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'checkMagazine' function below.
     *
     * The function accepts following parameters:
     *  1. STRING_ARRAY magazine
     *  2. STRING_ARRAY note
     */

    public static void checkMagazine(List<String> magazine, List<String> note) {
        Map<String, Integer> magazineWords = new HashMap<>();
        for (String word : magazine) {
            if (null == magazineWords.get(word)) {
                magazineWords.put(word, 1);
            } else {
                int value = magazineWords.get(word);
                value++;
                magazineWords.put(word, value);
            }
        }

        boolean isWordMismatch = false;
        for (String word : note) {
            if (null == magazineWords.get(word)) {
                System.out.println("No");
                isWordMismatch = true;
                return;
            } else {
                int value = magazineWords.get(word);
                value--;
                if (value == 0) {
                    magazineWords.remove(word);
                } else {
                    magazineWords.put(word, value);
                }
            }
        }

        if (!isWordMismatch) {
            System.out.println("Yes");
        }
    }

}

public class RansomNote {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int m = Integer.parseInt(firstMultipleInput[0]);

        int n = Integer.parseInt(firstMultipleInput[1]);

        List<String> magazine = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .collect(toList());

        List<String> note = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .collect(toList());

        Result.checkMagazine(magazine, note);

        bufferedReader.close();
    }
}

