package org.taskrest.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDao {
    private Long id;
    private String login;
    private String name;
    private String type;
    private String avatarUrl;
    private String createdAt;
    private Float calculations;

}
