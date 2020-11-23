package example.bean;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.io.IOException;

/**
 * @author yu.wenhua
 * @desc
 * @date 2020/11/12 11:39
 */
public class User {
    private String uid;
    @Size(max = 1,message = "用户名超长")
    private String userName;

    public User(String uid, String userName) {
        this.uid = uid;
        this.userName = userName;
    }
    public User(String json) throws IOException {
        User param = new ObjectMapper().readValue(json, User.class);
       uid = param.getUid();
       userName=param.getUserName();
    }
    public User() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
