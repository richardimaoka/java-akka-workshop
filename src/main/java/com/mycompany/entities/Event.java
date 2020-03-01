package com.mycompany.entities;

import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;

@Entity
public class Event {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  Integer id;

  String name;
}
