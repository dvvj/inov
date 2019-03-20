package org.inov.assignment.hbn;

import org.inov.assignment.hbn.ent.Customer;

import java.util.List;

public interface IDbOps {
  String addNewCustomer(Customer customer);
  List<Customer> queryByName(String name);
  List<Customer> testGetAll();
}
