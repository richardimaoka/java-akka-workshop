package com.mycompany.route;

import akka.http.javadsl.server.*;

import static akka.http.javadsl.server.PathMatchers.integerSegment;
import static akka.http.javadsl.server.PathMatchers.segment;

public class EventRoute extends AllDirectives {

  public Route route () {
    return path(segment("events").slash(integerSegment()), id ->
      get(() ->
        complete("get events = " + id)
      )
    );
  }
}
