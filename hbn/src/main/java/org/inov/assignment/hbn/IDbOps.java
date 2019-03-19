package org.inov.assignment.hbn;

import org.inov.assignment.hbn.ent.Customer;

import java.util.List;

public interface IDbOps {
  String addNewCustomer(String uid, String name, String gender);

  List<Customer> queryByName(String name);
}
