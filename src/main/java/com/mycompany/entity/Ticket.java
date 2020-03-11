package com.mycompany.entity;

import org.seasar.doma.*;

import java.time.*;

@Entity
@Table(name = "tickets")
public class Ticket {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;

  @Column(name = "event_id")
  Integer eventId;

  @Column(name = "price")
  Integer price;

  @Column(name = "name")
  String name;

  @Column(name = "quantity")
  int quantity;

  @Column(name = "start_at")
  LocalDateTime startAt;

  @Column(name = "opening_at")
  LocalDateTime openingAt;

  public void setName(String name) {
    this.name = name;
  }

  public int getQuantity() {
    return this.quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}
