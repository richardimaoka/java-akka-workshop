package com.mycompany.route;

import akka.http.javadsl.server.*;

public class AllRoute extends AllDirectives {

  private EventRoute eventRoute = new EventRoute();
  private OrderRoute orderRoute = new OrderRoute();

  public Route route() {
    return concat(
      eventRoute.route(),
      orderRoute.route()
    );
  }
}
