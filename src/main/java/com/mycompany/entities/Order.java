package com.mycompany.entities;

import org.seasar.doma.*;

@Entity
@Table(name = "orders")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;

  String name;

  public void setName(String name) {
    this.name = name;
  }
}
