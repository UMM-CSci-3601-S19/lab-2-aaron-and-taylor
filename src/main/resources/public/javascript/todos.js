/**
 * Function to get all the users!
 */
function getAllToDo() {
  console.log("Getting all the todos.");

  var HttpThingy = new HttpClient();
  HttpThingy.get("/api/todo", function (returned_json) {
    document.getElementById('jsonDump').innerHTML = returned_json;
  });
}

function getAllToDoByStatus() {
  console.log("Getting all the todo.");

  var HttpThingy = new HttpClient();
  HttpThingy.get("/api/todo?status=" + document.getElementById("status").value, function (returned_json) {
    document.getElementById('jsonDump').innerHTML = returned_json;
  });
}

function getAllToDoContains() {
  console.log("Getting all the todos with the searched word.");

  var HttpThingy = new HttpClient();
  HttpThingy.get("/api/todo?contains=" + document.getElementById("contains").value, function (returned_json) {
    document.getElementById('jsonDump').innerHTML = returned_json;
  });
}

function getAlllimitTodo() {
  console.log("Limiting list size of todos");

  var HttpThingy = new HttpClient();
  HttpThingy.get("/api/todo?limit=" + document.getElementById("limit").value, function (returned_json) {
    document.getElementById('jsonDump').innerHTML = returned_json;
  });
}

function getAllToDoByOwner() {
  console.log("Getting all todos by owner");

  var HttpThingy = new HttpClient();
  HttpThingy.get("/api/todo?owner=" + document.getElementById("owner").value, function (returned_json) {
    document.getElementById('jsonDump').innerHTML = returned_json;
  });
}

function getAllToDoByCategory() {
  console.log("Getting all todos by category");

  var HttpThingy = new HttpClient();
  HttpThingy.get("/api/todo?category=" + document.getElementById("category").value, function (returned_json) {
    document.getElementById('jsonDump').innerHTML = returned_json;
  });
}

/**
 * Wrapper to make generating http requests easier. Should maybe be moved
 * somewhere else in the future!.
 *
 * Based on: http://stackoverflow.com/a/22076667
 * Now with more comments!
 */
function HttpClient() {
  // We'll take a URL string, and a callback function.
  this.get = function (aUrl, aCallback) {
    var anHttpRequest = new XMLHttpRequest();

    // Set a callback to be called when the ready state of our request changes.
    anHttpRequest.onreadystatechange = function () {

      /**
       * Only call our 'aCallback' function if the ready state is 'DONE' and
       * the request status is 200 ('OK')
       *
       * See https://httpstatuses.com/ for HTTP status codes
       * See https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest/readyState
       *  for XMLHttpRequest ready state documentation.
       *
       */
      if (anHttpRequest.readyState === 4 && anHttpRequest.status === 200)
        aCallback(anHttpRequest.responseText);
    };

    anHttpRequest.open("GET", aUrl, true);
    anHttpRequest.send(null);
  }
}
