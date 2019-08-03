package open.generic.code.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Sorter {

    public <T, V> List<T> sort(List<T> listOfObjects, String sortingFieldGetterMethodName) {

        //Check if list is empty
        if (CollectionUtils.isEmpty(listOfObjects)) {
            System.out.println("Supplied List is empty. Returning null.");
            return null;
        }

        //Check if sortingFieldGetterMethodName is provided
        if (StringUtils.isBlank(sortingFieldGetterMethodName)) {
            System.out.println("Argument sortingFieldGetterMethodName is mandatory. Please provide a non blank String value. Returning list as received.");
            return listOfObjects;
        }

        //Check if the object class has the provided getter method
        Class objectClass = listOfObjects.get(0).getClass();
        final Method getterMethod;
        try {
            getterMethod = objectClass.getDeclaredMethod(sortingFieldGetterMethodName, null);
        } catch (NoSuchMethodException nsme) {
            System.out.println("There is no method named: " + sortingFieldGetterMethodName + " in the class: " + objectClass.getSimpleName() + ". Returning list as received.");
            nsme.printStackTrace();
            return listOfObjects;
        }

        //Check if the sorting field class contains a compareTo method
        Class sortingFieldClass = getterMethod.getReturnType();
        final Method compareToMethod;
        try {
            compareToMethod = sortingFieldClass.getDeclaredMethod("compareTo", sortingFieldClass);
        } catch (NoSuchMethodException nsme) {
            System.out.println("Class: " + sortingFieldClass.getName() + " does NOT contain a compareTo method. Returning list as received.");
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
                        return 1;
                    } else {
                        listOfObjectWithNullSortingFieldValue.add(object2);
                        return -1;
                    }
                }
            }

            //TODO Handle actual values
            try {
                return (Integer) compareToMethod.invoke(sortingFieldValue1, sortingFieldValue2);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            return 0;

        });

        return listOfObjects;
    }
}
