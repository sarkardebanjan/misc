package open.generic.code.utils;

import jdk.internal.vm.compiler.collections.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Objects;

public class Comparer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Comparer.class);

    public <T, V> Map<String, Pair<V, V>> compare (T oldObject, T newObject) {
        Class objectClass = oldObject.getClass();
        if (!objectClass.getName().equalsIgnoreCase(newObject.getClass().getName())) {
            LOGGER.error("The class types of object1 and object2 differ. Returning null map. Object 1 Class Name: {}, Object 2 Class Name: {}", objectClass.getName(), newObject.getClass().getName());
            return null;
        }

        //Handle null objects
        if (Objects.isNull(oldObject) || Objects.isNull(newObject)) {
            if (oldObject == newObject) {
                LOGGER.error("Both old and new objects are null. Returning null.");
                return null;
            } else {
                if (Objects.isNull(oldObject)) {
                    LOGGER.error("Old Object is null. Returning null.");
                    return null;
                } else {
                    LOGGER.error("New Object is null. Returning null.");
                    return null;
                }
            }
        }




    }


}
