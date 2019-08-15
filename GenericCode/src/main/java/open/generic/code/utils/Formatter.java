package open.generic.code.utils;

import java.util.List;
import java.util.stream.Stream;

@FunctionalInterface
public interface Formatter {

    //<T, R> Stream<R> format(Stream<T> inputStream, Class<T> inputStreamType, Class<R> outputStreamType);
    <T> Stream<Byte> format(List<List<T>> rowList);

}
