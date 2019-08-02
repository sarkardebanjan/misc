package open.generic.code.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grouper {

    public <T, V> Map<V, List<T>> group(List<T> listOfObjects, String groupingFieldGetterMethodName) {

        Map<V, List<T>> groupedMap = null;

        if (StringUtils.isBlank(groupingFieldGetterMethodName)) {
            System.out.println("Argument groupingFieldGetterMethodName is mandatory. Please provide a non blank String value. Returning null map.");
            return null;
        }

        if (CollectionUtils.isNotEmpty(listOfObjects)) {
            groupedMap = new HashMap<V, List<T>>();
            Class objectClass = listOfObjects.get(0).getClass();
            final Method getterMethod;
            try {
                getterMethod = objectClass.getDeclaredMethod(groupingFieldGetterMethodName, null);
            } catch (NoSuchMethodException nsme) {
                System.out.println("There is no method named: " + groupingFieldGetterMethodName + " in the class: " + objectClass.getSimpleName() + ". Returning null map.");
                nsme.printStackTrace();
                return null;
            }

            for (T object : listOfObjects) {
                try {
                    V groupingFieldValue = (V) getterMethod.invoke(object);
                    if (groupedMap.containsKey(groupingFieldValue)) {
                        groupedMap.get(groupingFieldValue).add(object);
                    } else {
                        List<T> sameObjectGroupList = new ArrayList<>();
                        sameObjectGroupList.add(object);
                        groupedMap.put(groupingFieldValue, sameObjectGroupList);
                    }
                } catch (IllegalAccessException e) {
                    System.out.println("IllegalAccessException encountered while processing the supplied list. Returning null map.");
                    e.printStackTrace();
                    return null;
                } catch (InvocationTargetException e) {
                    System.out.println("InvocationTargetException encountered while processing the supplied list. Returning null map.");
                    e.printStackTrace();
                    return null;
                }
            }

        } else {
            System.out.println("Supplied List is empty. Returning null map.");
            return null;
        }

        return groupedMap;
    }
}
