package open.generic.code.tests;

import open.generic.code.tests.model.Student;
import open.generic.code.utils.Comparer;
import open.generic.code.utils.Sorter;

public class TestCompareMain {

    public static void main(String[] args) {
        new TestCompareMain().doWork();
    }

    private void doWork() {
        Comparer comparer = new Comparer();
        Student oldObject = new Student("Donnie", 24, 'M');
        Student newObject = new Student("Donnie", 42, 'M');
        comparer.compare(oldObject, newObject);
    }
}
