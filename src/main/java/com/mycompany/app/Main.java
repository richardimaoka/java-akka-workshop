package com.mycompany.app;

import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import com.mycompany.AppConfig;
import com.mycompany.dao.*;
import com.mycompany.route.*;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.tx.TransactionManager;
import static akka.http.javadsl.server.PathMatchers.segment;

import java.util.concurrent.CompletionStage;

public class Main {

  public static void main(String[] args) throws Exception {
    // boot up server using the route as defined below
    ActorSystem system = ActorSystem.create("routes");

    Config config = AppConfig.singleton();
    EventDao eventDao = new EventDaoImpl();
    OrderDao orderDao = new OrderDaoImpl();
    TicketDao ticketDao = new TicketDaoImpl();

    final Http http = Http.get(system);
    final ActorMaterializer materializer = ActorMaterializer.create(system);

    //In order to access all directives we need an instance where the routes are define.
    TransactionManager tm = config.getTransactionManager();
    AllRoute route = new AllRoute(tm, eventDao, orderDao, ticketDao);

    final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = route.route().flow(system, materializer);
    final CompletionStage<ServerBinding> binding = http.bindAndHandle(routeFlow,
      ConnectHttp.toHost("localhost", 8080), materializer);

    System.out.println("Server online at http://localhost:8080/\nPress RETURN to stop...");
    System.in.read(); // let it run until user presses return

    binding
      .thenCompose(ServerBinding::unbind) // trigger unbinding from the port
      .thenAccept(unbound -> system.terminate()); // and shutdown when done
  }
}