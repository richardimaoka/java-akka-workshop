package com.mycompany.route;

import akka.http.javadsl.server.*;

import static akka.http.javadsl.server.PathMatchers.*;

public class AllRoute extends AllDirectives {

  public Route createRoute() {
    return concat(
      path(segment("events").slash(integerSegment()), id ->
        get(() ->
          complete("get events = " + id)
        )
      ),
      pathPrefix("orders", () ->
        pathEndOrSingleSlash(() ->
          post(() -> complete("orders post received"))
        )
      )
    );
  }
}
