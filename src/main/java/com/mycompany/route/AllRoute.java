package com.mycompany.route;

import akka.http.javadsl.server.*;
import com.mycompany.dao.*;
import org.seasar.doma.jdbc.tx.*;

public class AllRoute extends AllDirectives {
  private EventRoute eventRoute;
  private OrderRoute orderRoute;

  public AllRoute(
    TransactionManager transactionManager,
    EventDao eventDao,
    OrderDao orderDao,
    TicketDao ticketDao
  ) {
    this.eventRoute = new EventRoute(transactionManager);
    this.orderRoute = new OrderRoute(transactionManager, orderDao, ticketDao);
  }

  public Route route() {
    return concat(
      eventRoute.route(),
      orderRoute.route()
    );
  }
}
