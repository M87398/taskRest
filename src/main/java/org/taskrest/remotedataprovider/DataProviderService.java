package org.taskrest.remotedataprovider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.taskrest.user.UserDao;

@Service
public class DataProviderService implements DataProvider {
    @Value("${github.url}")
    private String githubUrl;
    private final RestTemplate restTemplate;

    public DataProviderService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDao getUserData(String login) {
        RemoteUserData user = restTemplate.getForObject(githubUrl + login, RemoteUserData.class);
        return user == null ? null : mapToUserDao(user);
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
