package memes_api;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.MultiMap;
import io.vertx.core.Promise;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;


public class AppVerticle extends AbstractVerticle {
  private static final Integer PORT = 8080;
  private MongoClient client;

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    this.prepareDb();
    Router router = Router.router(vertx);
    router.route("/books*").handler(BodyHandler.create());

    router.get("/books").handler(this::getAllBooks);
    router.post("/books").handler(this::persistBooksHandler);
    router.put("/books/:id").handler(this::updateBooksHandler);
    router.delete("/books/:id").handler(this::deleteBooksHandler);

    vertx.createHttpServer().requestHandler(router).listen(PORT, http -> {
      if (http.succeeded()) {
        startPromise.complete();
        System.out.println("HTTP server started on port " + PORT);
      } else {
        startPromise.fail(http.cause());
      }
    });
  }

  private void deleteBooksHandler(RoutingContext context) {
    MultiMap params = context.request().params();
    String id = params.get("id");

    JsonObject query = new JsonObject().put("_id", id);

    client.findOneAndDelete("books", query, res -> {
      if (res.succeeded()) {
        context.response().setStatusCode(200).end();
      } else {
        context.response().setStatusCode(400).end();
      }
    });

  }

  private void updateBooksHandler(RoutingContext context) {
    MultiMap params = context.request().params();
    String id = params.get("id");
    JsonObject newItem = new JsonObject().put("$set", context.getBodyAsJson());

    if (isJSONValid(context.getBody().toString())) {
      JsonObject query = new JsonObject().put("_id", id);

      client.find("books", query, res -> {
        if (res.failed()) {
          context.response().setStatusCode(404).end();
        } else {
          JsonObject oldItem = res.result().get(0);

          client.updateCollection("books", oldItem, newItem, (res1) -> {
            if (res1.succeeded()) {
              System.out.println("meme updated !");
              context.response().setStatusCode(201).end();
            } else {
              context.response().setStatusCode(400).end();
            }
          });
        }
      });

    } else {
      context.response().setStatusCode(400).end();
    }

  }

  private void persistBooksHandler(RoutingContext context) {
    String body = context.getBody().toString();

    if (isJSONValid(body)) {
      JsonObject jsonObject = new JsonObject(body);


      client.save("books", jsonObject, res -> {
        if (res.succeeded()) {
          context.response().setStatusCode(201).end(res.result());
        } else {
          context.response().setStatusCode(404).end(res.cause().getMessage());
        }
      });
    } else {
      context.response().setStatusCode(404).end();
    }
  }

  private void getAllBooks(RoutingContext context) {
    context.response()
      .putHeader("content-type", "application/json");

    JsonObject query = new JsonObject();

    client.find("books", query, res -> {
      if (res.succeeded()) {
        context.response().end(res.result().toString());
      } else {
        context.response().setStatusCode(404).end(res.cause().getMessage());
      }
    });

  }

  private void prepareDb() {
    String uri = "mongodb://localhost:27017";

    String db = "books";

    JsonObject mongoConfig = new JsonObject()
      .put("connection_string", uri)
      .put("db_name", db);

    this.client = MongoClient.createShared(vertx, mongoConfig);
  }

  private boolean isJSONValid(String test) {
    try {
      new JsonObject(test);
    } catch (DecodeException ex) {
      return false;
    }
    return true;
  }
}
