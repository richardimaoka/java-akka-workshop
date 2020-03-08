package com.mycompany.route;

import akka.http.javadsl.server.*;

public class OrderRoute extends AllDirectives {
  public Route route () {
   return pathPrefix("orders", () ->
      pathEndOrSingleSlash(() ->
        post(() -> complete("orders post received"))
      )
    );
  }
}
