package org.trials.kafka.kafkasample.util;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.kafka.common.serialization.Deserializer;

public class GenericKafkaDeserializer implements Deserializer<Object> {

    @Override
    public Object deserialize(String topic, byte[] data) {
        return SerializationUtils.deserialize(data);
    }
}
