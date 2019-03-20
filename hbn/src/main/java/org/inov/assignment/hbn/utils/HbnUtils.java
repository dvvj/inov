package org.inov.assignment.hbn.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.inov.assignment.hbn.ent.Customer;

import java.io.File;
import java.util.List;

public class HbnUtils {

  private static SessionFactory buildSessFactory(String cfgFile) {
    try {
      ServiceRegistry svcReg = new StandardServiceRegistryBuilder()
        .configure(new File(cfgFile)).build();

      Metadata md = new MetadataSources(svcReg).getMetadataBuilder().build();
      return md.getSessionFactoryBuilder().build();
    }
    catch (Exception ex) {
      throw new RuntimeException("Error in buildSessFactory()", ex);
    }
  }

  private static SessionFactory createTestSessFactory() {
    try {
      ServiceRegistry svcReg = new StandardServiceRegistryBuilder()
        .configure("hibernate.cfg.xml").build();

      Metadata md = new MetadataSources(svcReg).getMetadataBuilder().build();
      return md.getSessionFactoryBuilder().build();
    }
    catch (Exception ex) {
      throw new RuntimeException("Error in buildSessFactory()", ex);
    }
  }

  public final static SessionFactory testSessFactory = createTestSessFactory();

  public static SessionFactory getSessFactory(String cfgFile) {
    return buildSessFactory(cfgFile);
  }

  public static void shutdown(SessionFactory sessFactory) {
    sessFactory.close();
  }

  public static void shutdownTest() {
    testSessFactory.close();
  }
  private final static ObjectMapper mapper = new ObjectMapper();
  public static <T> String toJson(T t) {
    try {
      return mapper.writeValueAsString(t);
    }
    catch (Exception ex) {
      throw new RuntimeException("Json serialization error", ex);
    }
  }
//  public static String toJson(List<Customer> customers) {
//    try {
//      return mapper.writeValueAsString(customers);
//    }
//    catch (Exception ex) {
//      throw new RuntimeException("Json serialization error", ex);
//    }
//  }

  public static Customer fromJson(String j) {
    try {
      return mapper.readValue(j, Customer.class);
    }
    catch (Exception ex) {
      throw new RuntimeException("Json reading error", ex);
    }
  }

}
