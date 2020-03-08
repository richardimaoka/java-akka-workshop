package com.mycompany.route;

import akka.http.javadsl.server.*;
import org.seasar.doma.jdbc.tx.*;

public class OrderRoute extends AllDirectives {
  private TransactionManager transactionManager;

  public OrderRoute(TransactionManager transactionManager) {
    this.transactionManager = transactionManager;
  }

  public Route route () {
   return pathPrefix("orders", () ->
      pathEndOrSingleSlash(() ->
        post(() -> complete("orders post received"))
      )
    );
  }
}
