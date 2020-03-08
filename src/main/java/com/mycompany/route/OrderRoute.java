package com.mycompany.route;

import akka.http.javadsl.marshallers.jackson.*;
import akka.http.javadsl.server.*;
import com.mycompany.dto.*;
import org.seasar.doma.jdbc.tx.*;

public class OrderRoute extends AllDirectives {
  private TransactionManager transactionManager;

  public OrderRoute(TransactionManager transactionManager) {
    this.transactionManager = transactionManager;
  }

  public Route route () {
   return pathPrefix("orders", () ->
      pathEndOrSingleSlash(() ->
        entity(Jackson.unmarshaller(OrderPutRequest.class), req ->
          complete( "Put request received: ticket_id = " + req.getTicketId() + ", user_id = " + req.getUserId())
        )
      )
    );
  }
}
