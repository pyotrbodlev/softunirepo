package memes_api;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.MultiMap;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MainVerticle extends AbstractVerticle {
  private static final Integer PORT = 8080;
  private MongoClient client;

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    this.prepareDb();
    Router router = Router.router(vertx);
    router.route("/memes*").handler(BodyHandler.create());

    router.get("/memes").handler(this::getAllMemes);
    router.post("/memes").handler(this::persistMemeHandler);
    router.put("/memes/:id").handler(this::updateMemeHandler);
    router.delete("/memes/:id").handler(this::deleteMemeHandler);

    vertx.createHttpServer().requestHandler(router).listen(PORT, http -> {
      if (http.succeeded()) {
        startPromise.complete();
        System.out.println("HTTP server started on port " + PORT);
      } else {
        startPromise.fail(http.cause());
      }
    });
  }

  private void deleteMemeHandler(RoutingContext context) {
    MultiMap params = context.request().params();
    String id = params.get("id");

    JsonObject query = new JsonObject().put("_id", id);

    client.findOneAndDelete("memes", query, res -> {
      if (res.succeeded()) {
        context.response().setStatusCode(200).end();
      } else {
        context.response().setStatusCode(400).end();
      }
    });

  }

  private void updateMemeHandler(RoutingContext context) {
    MultiMap params = context.request().params();
    String id = params.get("id");
    JsonObject newItem = new JsonObject().put("$set", context.getBodyAsJson());

    if (isJSONValid(context.getBody().toString())) {
      JsonObject query = new JsonObject().put("_id", id);

      client.find("memes", query, res -> {
        if (res.failed()) {
          context.response().setStatusCode(404).end();
        } else {
          JsonObject oldItem = res.result().get(0);

          client.updateCollection("memes", oldItem, newItem, (res1) -> {
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

  private void persistMemeHandler(RoutingContext context) {
    String body = context.getBody().toString();

    if (isJSONValid(body)) {
      JsonObject jsonObject = new JsonObject(body);
      if (!jsonObject.containsKey("creationDate")) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");
        jsonObject.put("creationDate", LocalDate.now().format(dateTimeFormatter));
      }

      client.save("memes", jsonObject, res -> {
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

  private void getAllMemes(RoutingContext context) {
    context.response()
      .putHeader("content-type", "application/json");

    JsonObject query = new JsonObject();

    client.find("memes", query, res -> {
      if (res.succeeded()) {
        context.response().end(res.result().toString());
      } else {
        context.response().setStatusCode(404).end(res.cause().getMessage());
      }
    });

  }

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new MainVerticle());
  }

  private void prepareDb() {
    String uri = "mongodb://localhost:27017";

    String db = "memes";

    JsonObject mongoconfig = new JsonObject()
      .put("connection_string", uri)
      .put("db_name", db);

    this.client = MongoClient.createShared(vertx, mongoconfig);
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
