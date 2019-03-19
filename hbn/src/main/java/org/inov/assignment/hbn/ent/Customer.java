package org.inov.assignment.hbn.ent;

import javax.persistence.*;

@Entity
@Table(name="customers")
public class Customer {
  @Id
  @Column(name="uid", length = 255, nullable = false)
  private String uid;

  @Column(name="name", length = 31, nullable = false)
  private String name;

  @Column(name="gender", length = 1, nullable = false)
  private String gender;

  public Customer() { }

  public Customer(
    String uid,
    String name,
    String gender
  ) {
    this.uid = uid;
    this.name = name;
    this.gender = gender;
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

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }
}
