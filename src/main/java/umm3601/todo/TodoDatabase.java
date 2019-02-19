package umm3601.todo;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class TodoDatabase {
  private ToDo[] allTodos;

  public TodoDatabase(String todoDataFile) throws IOException {
    Gson gson = new Gson();
    FileReader reader = new FileReader(todoDataFile);///
    allTodos = gson.fromJson(reader, ToDo[].class);
  }

  /**
   * Get the single user specified by the given ID. Return
   * `null` if there is no user with that ID.
   *
   * @param id the ID of the desired todo
   * @return the user with the given ID, or null if there is no todo
   * with that ID
   */
  public ToDo getToDo(String id) {
    return Arrays.stream(allTodos).filter(x -> x._id.equals(id)).findFirst().orElse(null);
  }

  /**
   * Get an array of all the users satisfying the queries in the params.
   *
   * @param queryParams map of required key-value pairs for the query
   * @return an array of all the users matching the given criteria
   */
  public ToDo[] listTodos(Map<String, String[]> queryParams) {
    ToDo[] filteredtodos = allTodos;

    // Filter status if defined
    if (queryParams.containsKey("status")) {
      String targetStatus = queryParams.get("status")[0];
      filteredtodos = filterTodosByStatus(filteredtodos, targetStatus);
    }

    if (queryParams.containsKey("contains")) {
      String targetBody = queryParams.get("contains")[0];
      filteredtodos = filterTodosByBody(filteredtodos, targetBody);
    }

    if (queryParams.containsKey("limit")) {
      int targetLimit = Integer.parseInt(queryParams.get("limit")[0]);
      filteredtodos = limitTodos(filteredtodos, targetLimit);
    }

    if (queryParams.containsKey("owner")) {
      String targetOwner = queryParams.get("owner")[0];
      filteredtodos = filterTodosByOwner(filteredtodos, targetOwner);
    }

    if (queryParams.containsKey("category")) {
      String targetCategory = queryParams.get("category")[0];
      filteredtodos = filterTodosByCategory(filteredtodos, targetCategory);
    }
    return filteredtodos;

  }

  ///////filtering by status
  public ToDo[] filterTodosByStatus(ToDo[] todos, String targetStatus) {
    if (targetStatus.equals("complete")) {
      return Arrays.stream(todos).filter(x -> x.status).toArray(ToDo[]::new);
    } else if (targetStatus.equals("incomplete")) {
      return Arrays.stream(todos).filter(x -> !x.status).toArray(ToDo[]::new);
    } else {
      return todos;
    }
  }
///////filtering by search string
  public ToDo[] filterTodosByBody(ToDo[] todos, String targetBody) {
      return Arrays.stream(todos).filter(x -> x.body.contains(targetBody)).toArray(ToDo[]::new);
    }

  public ToDo[] limitTodos(ToDo[] tempTodos, int lim) {
    ToDo[] limitTodos;
      limitTodos= Arrays.stream(tempTodos).limit(lim).toArray(ToDo[]::new);
    return limitTodos;
  }
  ///////filtering by owner
  public ToDo[] filterTodosByOwner(ToDo[] todos, String targetOwner) {
    return Arrays.stream(todos).filter(x -> x.owner.contains(targetOwner)).toArray(ToDo[]::new);
  }

  ///////filtering by owner
  public ToDo[] filterTodosByCategory(ToDo[] todos, String targetCategory) {
    return Arrays.stream(todos).filter(x -> x.category.contains(targetCategory)).toArray(ToDo[]::new);
  }

}

