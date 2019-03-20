package org.inov.assignment;

import org.inov.assignment.gnl.SvcHelpers;
import org.inov.assignment.hbn.ent.Customer;
import org.inov.assignment.hbn.utils.HbnUtils;

import java.util.Arrays;
import java.util.List;

public class SvcTest {

  public static void main(String[] args) {
    List<Customer> customers2Add = Arrays.asList(
      new Customer("customer1", "John Smith", "M"),
      new Customer("customer2", "Erik Lövquist", "M"),
      new Customer("customer31", "やまた", "M"),
      new Customer("customer33", "やまた", "F"),
      new Customer("customer4", "张晓", "F"),
      new Customer("customer4", "张晓", "M")
    );

    String svcEndpoint = "https://localhost:8443/webapi";

    // test adding customers via PUT
    customers2Add.forEach(c -> {
      String j = HbnUtils.toJson(c);
      boolean success = SvcHelpers.newCustomer(svcEndpoint, j);
      System.out.println(
        success ? String.format("Customer [%s] added", c.getUid())
          : String.format("Failed to add customer: [%s]", c.getUid())
      );
    });

    traceResultJson(
      "======= All customers ==========",
      SvcHelpers.getAllCustomers(svcEndpoint)
    );
    traceResultJson(
      "======= by name 'John Smith' ==========",
      SvcHelpers.customerByName(svcEndpoint, "John Smith")
    );
    traceResultJson(
      "======= by name 'やまた' ==========",
      SvcHelpers.customerByName(svcEndpoint, "やまた")
    );
  }

  private static void traceResultJson(String preamble, String resultJson) {
    List<Customer> resultCustomers = HbnUtils.listFromJson(resultJson);
    System.out.println(preamble);
    resultCustomers.forEach(System.out::println);
  }
}
