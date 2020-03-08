package com.mycompany.route;

import akka.http.javadsl.server.*;
import org.seasar.doma.jdbc.tx.*;

public class AllRoute extends AllDirectives {
  private TransactionManager transactionManager;
  private EventRoute eventRoute;
  private OrderRoute orderRoute;

  public AllRoute(TransactionManager transactionManager) {
    this.transactionManager = transactionManager;
    this.eventRoute = new EventRoute(this.transactionManager);
    this.orderRoute = new OrderRoute(this.transactionManager);
  }

  public Route route() {
    return concat(
      eventRoute.route(),
      orderRoute.route()
    );
  }
}
