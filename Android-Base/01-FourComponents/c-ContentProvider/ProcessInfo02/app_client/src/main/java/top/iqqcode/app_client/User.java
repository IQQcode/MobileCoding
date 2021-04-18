package top.iqqcode.app_client;

/**
 * @Author: iqqcode
 * @Date: 2021-04-16 18:54
 * @Description:
 */
public class User {
    private Integer id;
    private String name;

    public User() {
    };

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
