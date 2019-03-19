package org.inov.assignment.hbn.ent;

import javax.persistence.*;

@Entity
@Table(name="customers", uniqueConstraints = {@UniqueConstraint(columnNames = {"idcard_no"})})
public class Customer {
  @Id
  @Column(name="uid", length = 255, nullable = false)
  private String uid;

  @Column(name="name", length = 31, nullable = false)
  private String name;

  public Customer() { }

  public Customer(
    String uid,
    String name
  ) {
    this.uid = uid;
    this.name = name;
  }


  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
