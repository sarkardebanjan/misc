package org.trials.kafka.kafkasample.util;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.kafka.common.serialization.Serializer;

import java.io.Serializable;

public class GenericKafkaSerializer implements Serializer<Object> {

    @Override
    public byte[] serialize(String topic, Object data) {
        return SerializationUtils.serialize((Serializable) data);
    }
}
