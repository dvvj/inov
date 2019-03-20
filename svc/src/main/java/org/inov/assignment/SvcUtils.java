package org.inov.assignment;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.inov.assignment.hbn.IDbOps;
import org.inov.assignment.hbn.ent.Customer;

import javax.ws.rs.core.MediaType;
import java.util.List;

public class SvcUtils {

  private final static ObjectMapper ObjMapper = new ObjectMapper();

  public static <T> T readObj(String json, Class<T> clz) {
    try {
      return ObjMapper.readValue(json, clz);
    }
    catch (Exception ex) {
      throw new RuntimeException("Error deserializing json", ex);
    }

  }

  final static String MediaType_TXT_UTF8 = MediaType.TEXT_PLAIN + ";charset=utf-8";

  public static IDbOps getDbOps() {
    return SvcContextListener.getDbOps();
  }


}
