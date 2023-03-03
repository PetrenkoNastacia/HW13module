package HW13module.Tasks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class HttpUtil {

    private static final Gson GSON = new Gson();
    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();

    /* Task1: */
    public static String postUser(User user) throws IOException, InterruptedException, URISyntaxException {
        URI uri = new URI("https://jsonplaceholder.typicode.com/users/");
        String userJson = GSON.toJson(user);
        HttpRequest request = HttpRequest.newBuilder(uri)
                .headers("content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(userJson))
                .build();
        HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("POST status code: " + response.statusCode());
        return response.body();
    }

    public static String updateUser(int id, User user) throws IOException, InterruptedException, URISyntaxException {
        String body = GSON.toJson(user);
        URI uri = new URI("https://jsonplaceholder.typicode.com/users/" + id);
        HttpRequest request = HttpRequest.newBuilder(uri)
                .headers("content-type", "application/json;")
                .PUT(HttpRequest.BodyPublishers.ofString(body))
                .build();
        HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("PUT status code: " + response.statusCode());
        return response.body();
    }

    public static int deleteUser(int id) throws IOException, InterruptedException, URISyntaxException {
        URI uri = new URI("https://jsonplaceholder.typicode.com/users/" + id);
        HttpRequest request = HttpRequest.newBuilder(uri)
                .DELETE()
                .build();
        HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("DELETE status code: " + response.statusCode());
        return id;
    }

    public static String getAllUsers() throws IOException, InterruptedException, URISyntaxException {
        URI uri = new URI("https://jsonplaceholder.typicode.com/users/");
        HttpRequest request = HttpRequest.newBuilder(uri)
                .GET()
                .build();
        HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("GET status code: " + response.statusCode());
        return response.body();
    }

    public static String getUserById(int id) throws IOException, InterruptedException, URISyntaxException {
        URI uri = new URI("https://jsonplaceholder.typicode.com/users/" + id);
        HttpRequest request = HttpRequest
                .newBuilder(uri)
                .GET()
                .build();
        HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("GET by id status code: " + response.statusCode());
        return response.body();
    }

    public static String getUserByUsername(String username) throws IOException, InterruptedException, URISyntaxException {
        URI uri = new URI("https://jsonplaceholder.typicode.com/users" + "?username=" + username);
        HttpRequest request = HttpRequest
                .newBuilder(uri)
                .GET()
                .build();
        HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("GET by username status code: " + response.statusCode());
        return response.body();
    }

    /* Task2: */
    public static int getPostsByUserId(URI uri, int userId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri + "/users/" + userId + "/posts"))
                .GET()
                .build();
        HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();
        int lastPostId = -1;
        String[] split = responseBody.split("\n");
        for (String line : split) {
            String[] array = line.trim().split(",");
            for (String element : array) {
                if (element.contains("\"id\":")) {
                    int postId = Integer.parseInt(element.trim().split(":")[1].trim());
                    if (postId > lastPostId) {
                        lastPostId = postId;
                    }
                    break;
                }
            }
        }
        System.out.println("GET user post status code: " + response.statusCode());
        return lastPostId;
    }

    public static String getLastCommentId(URI uri, int userId, int postId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri + "/posts/" + postId + "/comments?/id" + userId))
                .GET()
                .build();
        HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("GET last comment status code: " + response.statusCode());
        return response.body();
    }

    public static void writeCommentsToJson(String comments, int userId, int postId) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File file = new File("HW13/src/main/java/HW13module//Tasks/"
                + "user-"+ userId + "-post-" + postId + "-comments.json");
        try (FileWriter filewriter = new FileWriter(file)) {
            JsonArray commentsArr = JsonParser.parseString(comments).getAsJsonArray();
            gson.toJson(commentsArr, filewriter);
        }
    }

    /* Task1: */
    public static String findTodos(int userId) throws IOException, InterruptedException, URISyntaxException {
        URI uri = new URI("https://jsonplaceholder.typicode.com");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri + "/users/" + userId + "/todos"))
                .GET()
                .build();
        HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("GET todos status code: " + response.statusCode());
        return response.body();
    }

    public List<UserTodos> getOpenTodos(String json) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        UserTodos[] userTodos = gson.fromJson(json, UserTodos[].class);
        List<UserTodos> allTodos = new ArrayList<>(Arrays.asList(userTodos));
        List<UserTodos> openTodos = new ArrayList<>();
        for (UserTodos element : allTodos) {
            if (!element.isCompleted()) {
                openTodos.add(element);
            }
        }
        return openTodos;
    }

    public void printOpenTodos(int userId) throws IOException, URISyntaxException, InterruptedException {
        String allTodos = findTodos(userId);
        List<UserTodos> allOpenTodos = getOpenTodos(allTodos);
        for (UserTodos element : allOpenTodos) {
            System.out.println(Collections.singletonList(element));
        }
    }
}
