package open.generic.code.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grouper {

    private static final Logger LOGGER = LoggerFactory.getLogger(Grouper.class);

    public <T, V> Map<V, List<T>> group(List<T> listOfObjects, String groupingFieldGetterMethodName) {

        Map<V, List<T>> groupedMap = null;

        if (StringUtils.isBlank(groupingFieldGetterMethodName)) {
            LOGGER.info("Argument groupingFieldGetterMethodName is mandatory. Please provide a non blank String value. Returning null map.");
            return null;
        }

        if (CollectionUtils.isNotEmpty(listOfObjects)) {
            groupedMap = new HashMap<V, List<T>>();
            Class objectClass = listOfObjects.get(0).getClass();
            final Method getterMethod;
            try {
                getterMethod = objectClass.getDeclaredMethod(groupingFieldGetterMethodName, null);
            } catch (NoSuchMethodException nsme) {
                LOGGER.info("There is no method named: {} in the class: {}. Returning null map.", groupingFieldGetterMethodName, objectClass.getSimpleName());
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
                    LOGGER.info("IllegalAccessException encountered while processing the supplied list. Returning null map.");
                    e.printStackTrace();
                    return null;
                } catch (InvocationTargetException e) {
                    LOGGER.info("InvocationTargetException encountered while processing the supplied list. Returning null map.");
                    e.printStackTrace();
                    return null;
                }
            }

        } else {
            LOGGER.info("Supplied List is empty. Returning null map.");
            return null;
        }

        return groupedMap;
    }
}
