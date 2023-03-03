package HW13module.Tasks;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static HW13module.Tasks.HttpUtil.*;

public class HttpMain {
    public static final String URL = "https://jsonplaceholder.typicode.com";
    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
//        Task1
        User user1 = new User();
        user1.setId(13);
        user1.setName("Anastasiia");
        user1.setUsername("Nastacia");
        user1.setEmail("NastaciaTest@gmail.com");
        user1.setPhone("123-45-666");
        user1.setWebsite("webTest");
        System.out.println(HttpUtil.postUser(user1));             //create

        System.out.println(updateUser(6,user1));               //updateUser

        System.out.println(deleteUser(4));                     //delete *returns id of deleted user

        System.out.println(getAllUsers());                         //get all users

        System.out.println(getUserById(3));                        //get user by id

        System.out.println(getUserByUsername("Leopoldo_Corkery")); //get user by username

//        //Task2
//        int userId = 1;
        int userId = 3;
        int lastPostId = HttpUtil.getPostsByUserId(URI.create(URL), userId);
        String comments = HttpUtil.getLastCommentId(URI.create(URL), userId, lastPostId);
        HttpUtil.writeCommentsToJson(comments, userId, lastPostId);

//          //Task3
        HttpUtil test = new HttpUtil();
        test.printOpenTodos(6);
    }
}
