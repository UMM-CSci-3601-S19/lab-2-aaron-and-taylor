package umm3601.todo;

  import com.google.gson.Gson;
  import com.google.gson.JsonObject;
  import spark.Request;
  import spark.Response;

  import static umm3601.Util.*;

public class TodoController {
  private final Gson gson;
  private TodoDatabase TodoDatabase;

  /**
   * Construct a controller for users.
   * <p>
   * This loads the "database" of user info from a JSON file and
   * stores that internally so that (subsets of) users can be returned
   * in response to requests.
   *
   * @param database the database containing user data
   */
  public TodoController(TodoDatabase database) {
    gson = new Gson();
    this.TodoDatabase = database;
  }

  /**
   * Get the single user specified by the `id` parameter in the request.
   *
   * @param req the HTTP request
   * @param res the HTTP response
   * @return a success JSON object if the user with that ID is found, a fail
   * JSON object if no user with that ID is found
   */
  public JsonObject getToDo(Request req, Response res) {
    res.type("application/json");
    String id = req.params("id");
    ToDo todo = TodoDatabase.getToDo(id);
    if (todo != null) {
      return buildSuccessJsonResponse("todo", gson.toJsonTree(todo));
    } else {
      String message = "Todo with ID " + id + " wasn't found.";
      return buildFailJsonResponse("id", message);
    }
  }

  /**
   * Get a JSON response with a list of all the users in the "database".
   *
   * @param req the HTTP request
   * @param res the HTTP response
   * @return a success JSON object containing all the users
   */
  public JsonObject getToDos(Request req, Response res) {
    res.type("application/json");
    ToDo[] todo = TodoDatabase.listTodos(req.queryMap().toMap()); //////////////////////////
    return buildSuccessJsonResponse("todo", gson.toJsonTree(todo));
  }

}

