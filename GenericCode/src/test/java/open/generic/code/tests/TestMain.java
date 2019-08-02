package open.generic.code.tests;

import open.generic.code.tests.model.Student;
import open.generic.code.utils.Grouper;
import open.generic.code.utils.Sorter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TestMain {
    
    public static void main(String[] args) {
        new TestMain().doWork();    
    }

    private void doWork() {

        Student student1 = new Student("Debo", 30, 'M');
        Student student2 = new Student("Rishi", 45, 'M');
        Student student3 = new Student("Krishu", 26, 'F');

        List<Student> studentList = new ArrayList<>();
        studentList.add(student2);
        studentList.add(student1);
        studentList.add(student3);

        Grouper grouper = new Grouper();
        Map<Character, List<Student>> map = grouper.group(studentList, "getGender");

        map.keySet().forEach(gender -> {
            Collections.sort(map.get(gender));
        });

        map.keySet().forEach(key -> {
            System.out.println();
            System.out.println();
            System.out.println("List Start");
            System.out.println(map.get(key));
            System.out.println("List End");
            System.out.println();
            System.out.println();
        });

    }

}