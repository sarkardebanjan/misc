package open.generic.code.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonMapInterconversion {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) {
        Map<String, Object> inputMap = new HashMap<>();
        inputMap.put("key1", "value1");
        inputMap.put("key2", "value2");
        inputMap.put("key3", "value3");
        inputMap.put("key4", "value4");
        inputMap.put("key5", 10);
        inputMap.put("key6", "value6");

        String json = null;
        try {
            json = objectMapper.writeValueAsString(inputMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println("JSON String: \n" + json + "\n\n");

        try {
            Map<String, Object> outputMap = objectMapper.readValue(json, new TypeReference<Map<String, Object>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Output Map Entries: \n" + json);
    }


}
