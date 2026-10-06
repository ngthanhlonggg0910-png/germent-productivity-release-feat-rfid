package vn.nvsoft.service.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonMapper {
    private static final Logger log = LoggerFactory.getLogger(JsonMapper.class.getName());

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    public static <T> T convertToObject(String json, Class<T> clazz) {
        try {
            if (Strings.isNullOrEmpty(json)) {
                return null;
            }
            return OBJECT_MAPPER.readValue(json, clazz);
        } catch (Exception e) {
            log.warn("Can not convert json value {} to class {}", json, clazz.getName(), e);
        }
        return null;
    }

    public static String convertToJsonString(Object object) {
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.warn("Can not convert object to json string", e);
        }
        return null;
    }
}
