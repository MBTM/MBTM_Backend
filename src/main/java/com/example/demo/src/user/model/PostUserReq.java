package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostUserReq {
    private String id;
    private String password;
    private String passwordForCheck;
    private String name;
    private String nickName;
    private String phone;
    private String email;
}
