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

        Student student1 = new Student("David", 52, 'M');
        Student student2 = new Student("Leo", 13, 'M');
        Student student3 = new Student("Debra", 67, 'F');
        Student student4 = new Student("Martha", 91, 'F');
        Student student5 = new Student("Morris", 22, 'M');
        Student student6 = new Student("Toby", 47, 'M');
        Student student7 = new Student("Macy", 29, 'F');
        Student student8 = new Student("Nate", 56, 'M');
        Student student9 = new Student("Nancy", 73, 'F');
        Student student10 = new Student("Romina", 31, 'F');

        List<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        studentList.add(student4);
        studentList.add(student5);
        studentList.add(student6);
        studentList.add(student7);
        studentList.add(student8);
        studentList.add(student9);
        studentList.add(student10);

        Grouper grouper = new Grouper();
        Map<Character, List<Student>> map = grouper.group(studentList, "getGender");

        map.keySet().forEach(gender -> {
            //Collections.sort(map.get(gender));
            Sorter sorter = new Sorter();
            sorter.sort((ArrayList)map.get(gender), "getAge", true);
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