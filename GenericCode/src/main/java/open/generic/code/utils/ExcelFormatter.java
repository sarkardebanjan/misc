package open.generic.code.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Stream;

public class ExcelFormatter implements Formatter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelFormatter.class);

    /*
    @Override
    public <T, R> Stream<R> format(Stream<T> inputStream, Class<T> inputStreamType, Class<R> outputStreamType) {
        File outputXlsxFile = new File("C:\\Users\\deban\\Desktop\\formattedFile.txt");
        return Stream.of(outputStreamType.cast(outputXlsxFile));
    }
    */

    @Override
    public <T> Stream<Byte> format(List<List<T>> rowList) {
        if (CollectionUtils.isEmpty(rowList)) {
            LOGGER.error("Supplied row list is empty");
            return Stream.empty();
        }
        return null;
    }
}
