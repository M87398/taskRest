package org.taskrest;

import org.taskrest.user.UserDao;

public class UserDaoGenerator {
    public static UserDao simpleUserDao(){
        return UserDao.builder()
                .id(1l)
                .login("login")
                .name("name")
                .type("type")
                .avatarUrl("/avatarUrl")
                .createdAt("2011-01-25T18:44:36Z")
                .calculations(1.0f)
                .build();
    }
}
