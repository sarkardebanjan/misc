package open.generic.code.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Sorter {

    private static final Logger LOGGER = LoggerFactory.getLogger(Sorter.class);

    public <T, V> List<T> sort(List<T> listOfObjects, String sortingFieldGetterMethodName, boolean isAscendingSort) {

        //Check if list is empty
        if (CollectionUtils.isEmpty(listOfObjects)) {
            LOGGER.info("Supplied List is empty. Returning null.");
            return null;
        }

        //Check if sortingFieldGetterMethodName is provided
        if (StringUtils.isBlank(sortingFieldGetterMethodName)) {
            LOGGER.info("Argument sortingFieldGetterMethodName is mandatory. Please provide a non blank String value. Returning list as received.");
            return listOfObjects;
        }

        //Check if the object class has the provided getter method
        Class objectClass = listOfObjects.get(0).getClass();
        final Method getterMethod;
        try {
            getterMethod = objectClass.getDeclaredMethod(sortingFieldGetterMethodName, null);
        } catch (NoSuchMethodException nsme) {
            LOGGER.info("There is no method named: {} in the class: {}. Returning list as received.", sortingFieldGetterMethodName, objectClass.getSimpleName());
            nsme.printStackTrace();
            return listOfObjects;
        }

        //Check if the sorting field class contains a compareTo method
        Class sortingFieldClass = getterMethod.getReturnType();
        final Method compareToMethod;
        try {
            compareToMethod = sortingFieldClass.getDeclaredMethod("compareTo", sortingFieldClass);
        } catch (NoSuchMethodException nsme) {
            LOGGER.info("Class: {} does NOT contain a compareTo method. Returning list as received.", sortingFieldClass.getName());
            nsme.printStackTrace();
            return listOfObjects;
        }


        List<T> listOfObjectWithNullSortingFieldValue = new ArrayList<>();

        listOfObjects.sort((T object1, T object2) -> {

            V sortingFieldValue1 = null;
            V sortingFieldValue2 = null;

            try {
                sortingFieldValue1 = (V) getterMethod.invoke(object1);
                sortingFieldValue2 = (V) getterMethod.invoke(object2);
            } catch (IllegalAccessException iae) {
                iae.printStackTrace();
            } catch (InvocationTargetException ite) {
                ite.printStackTrace();
            } catch (ClassCastException cce) {
                cce.printStackTrace();
            }

            //Handle null values
            if (Objects.isNull(sortingFieldValue1) || Objects.isNull(sortingFieldValue2)) {
                if (sortingFieldValue1 == sortingFieldValue2) {
                    listOfObjectWithNullSortingFieldValue.add(object1);
                    listOfObjectWithNullSortingFieldValue.add(object2);
                    return 0;
                } else {
                    if (Objects.isNull(sortingFieldValue1)) {
                        listOfObjectWithNullSortingFieldValue.add(object1);
                        return isAscendingSort ? 1 : -1;
                    } else {
                        listOfObjectWithNullSortingFieldValue.add(object2);
                        return isAscendingSort ? -1 : 1;
                    }
                }
            }

            //TODO Handle actual values
            try {
                Integer comparisonResult = (Integer) compareToMethod.invoke(sortingFieldValue1, sortingFieldValue2);
                return isAscendingSort ? comparisonResult : (-1 * comparisonResult);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            return 0;

        });

        return listOfObjects;
    }
}
