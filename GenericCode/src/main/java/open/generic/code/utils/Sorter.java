package open.generic.code.utils;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.List;

public class Sorter {

    public <T extends Comparable> List<T> sort(List<T> listOfObjects) {

        if (CollectionUtils.isNotEmpty(listOfObjects))
            Collections.sort(listOfObjects);
        else
            System.out.println("Supplied List is empty. Returning list as received.");

        return listOfObjects;
    }
}
