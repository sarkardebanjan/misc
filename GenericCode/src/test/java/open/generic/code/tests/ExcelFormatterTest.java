package open.generic.code.tests;

import open.generic.code.utils.ExcelFormatter;
import open.generic.code.utils.Formatter;

import java.util.ArrayList;
import java.util.List;

public class ExcelFormatterTest {

    public static void main(String[] args) {
        new ExcelFormatterTest().doWork();
    }

    private void doWork() {

        Formatter excelFormatter = new ExcelFormatter();
        final List<String> strList = new ArrayList<>();
        strList.add("Line 1: Header and Design");
        strList.add("Line 2: Subject");
        strList.add("Line 3: Body");
        strList.add("Line 4: Footer and Signature");

        /*
        byte[] xlsxFile = excelFormatter.format(strList.stream(), String.class, File.class);

        try (FileWriter fileWriter = new FileWriter(xlsxFile)) {
            fileWriter.
                    fileWriter.write("This is ");
            fileWriter.write("a test");
            fileWriter.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        */

    }

}

