package main.web;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AuthRequest implements Serializable {
    private String userName;
    private String password;
//    private String[] roles;

    public AuthRequest(String userName, String password){
        this.userName = userName;
        this.password = password;
//        this.roles = roles;
    }
}
