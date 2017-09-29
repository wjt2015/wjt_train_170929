package wjt.model;

/**
 * Created by linux2014 on 17-6-22.
 */
public class User {
    private Integer id;
    private String name;
    private Short age;
    private String nickName;
    private String password;



    public User() {
    }

    public User(Integer id, String name, Short age, String nickName) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.nickName = nickName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getAge() {
        return age;
    }

    public void setAge(Short age) {
        this.age = age;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String toString(){
        return "{" + id + "," + name + "," + age + "," + nickName + "}";
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
