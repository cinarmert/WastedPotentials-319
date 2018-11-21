package kubitz.client.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import kubitz.client.storage.LeaderboardUser;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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

  public static <T> List<T> fromListOfJson(String json, Class<T> c) throws IOException, ClassNotFoundException {
    Class<T[]> arrayClass = (Class<T[]>) Class.forName("[L" + c.getName() + ";");
    T[] objects = objectMapper.readValue(json, arrayClass);
    return Arrays.asList(objects);
  }
}