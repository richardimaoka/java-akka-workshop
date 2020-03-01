package com.mycompany.app;

import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import com.mycompany.AppConfig;
import com.mycompany.dao.EventDao;
import com.mycompany.dao.EventDaoImpl;
import com.mycompany.entities.Event;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.tx.TransactionManager;

import java.util.concurrent.CompletionStage;

public class Main extends AllDirectives {

  public static void main(String[] args) throws Exception {
    // boot up server using the route as defined below
    ActorSystem system = ActorSystem.create("routes");

    Config config = AppConfig.singleton();
    EventDao dao = new EventDaoImpl();
    TransactionManager tm = config.getTransactionManager();

    tm.required(() -> {
      Event event = new Event();
      event.setName("new name 1");
      dao.insert(event);
    });

    final Http http = Http.get(system);
    final ActorMaterializer materializer = ActorMaterializer.create(system);

    //In order to access all directives we need an instance where the routes are define.
    Main app = new Main();

    final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = app.createRoute().flow(system, materializer);
    final CompletionStage<ServerBinding> binding = http.bindAndHandle(routeFlow,
      ConnectHttp.toHost("localhost", 8080), materializer);

    System.out.println("Server online at http://localhost:8080/\nPress RETURN to stop...");
    System.in.read(); // let it run until user presses return

    binding
      .thenCompose(ServerBinding::unbind) // trigger unbinding from the port
      .thenAccept(unbound -> system.terminate()); // and shutdown when done
  }

  private Route createRoute() {
    return concat(
      path("hello", () ->
        get(() ->
          complete("<h1>Say hello to akka-http</h1>"))));
  }
}