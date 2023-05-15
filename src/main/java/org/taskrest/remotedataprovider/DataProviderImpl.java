package org.taskrest.remotedataprovider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.taskrest.user.UserDao;

@Component
public class DataProviderImpl implements DataProvider {
    @Value("${github.url}")
    private String githubUrl;
    private final RestTemplate restTemplate;

    public DataProviderImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDao getUserData(String login) {
        RemoteUserData user = restTemplate.getForObject(githubUrl + login, RemoteUserData.class);
        UserDao userDao = mapToUserDao(user);
        return userDao;
    }

    private UserDao mapToUserDao(RemoteUserData user) {
        return UserDao.builder()
                .id(user.getId())
                .login(user.getLogin())
                .name(user.getName())
                .type(user.getType())
                .avatarUrl(user.getAvatarUrl())
                .createdAt(user.getCreatedAt())
                .calculations(user.getCalculations())
                .build();
    }
}
