package com.ll.basic1.boundedContext.member.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
//@Data //getter, setter, tostring 등등 다 들어간 것
@Getter
@ToString
public class Member {
    private static long lastId;
    private final long id;
    private final String username;
    private final String password;


    static {
        lastId = 0;
    }

    public Member(String username, String password) {
        this(++lastId, username, password);
    }
}