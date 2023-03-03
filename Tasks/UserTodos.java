package HW13module.Tasks;

public class UserTodos {

    private int userId;
    int id;
    String title;
    private boolean isCompleted;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public String toString() {
        return "\n{" +
                "\nuserId: " + userId +
                "\nid: " + id +
                "\ntitle: " + title +
                "\ncompleted: " + isCompleted +
                "\n}";
    }
}
