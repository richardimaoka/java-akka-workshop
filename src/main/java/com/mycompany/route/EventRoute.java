package com.mycompany.route;

import akka.http.javadsl.server.*;
import org.seasar.doma.jdbc.tx.*;

import static akka.http.javadsl.server.PathMatchers.integerSegment;
import static akka.http.javadsl.server.PathMatchers.segment;

public class EventRoute extends AllDirectives {
  private TransactionManager transactionManager;

  public EventRoute(TransactionManager transactionManager) {
    this.transactionManager = transactionManager;
  }

  public Route route () {
    return path(segment("events").slash(integerSegment()), id ->
      get(() ->
        complete("get events = " + id)
      )
    );
  }
}
