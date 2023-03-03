package HW13module.Tasks;

public class User {

    private int id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private String website;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String toString() {
        return "\n{" +
                "\nid: " + id +
                "\nname: " + name +
                "\nusername: " + username +
                "\nemail: " + email +
                "\nphone: " + phone +
                "\nwebsite: " + website +
                "\n}" +
                "\n";
    }
}

