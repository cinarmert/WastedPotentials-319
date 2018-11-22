package kubitz.server.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtil {

  private static final String TAG = JsonUtil.class.getSimpleName();

  private static final ObjectMapper objectMapper = new ObjectMapper();

  static {
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  public static String toJson(Object object) {
    try {
      return objectMapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      return "";
    }
  }

  public static <T> T fromJson(String json, Class<T> c)
          throws IOException
  {
    return objectMapper.readValue(json, c);
  }
}