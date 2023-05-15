package org.taskrest.remotedataprovider;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.taskrest.user.UserDao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DataProviderServiceTest {

    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private DataProviderService dataProviderService;

    @Test
    void shouldGetNullWhenUserDataNotFound() {
        //given
        String login = "notExistingUser";

        //when
        UserDao user = dataProviderService.getUserData(login);

        //then
        assertThat(user).isNull();
    }

    @Test
    void shouldGetNullWhenExceptionIsThrown() {
        //given
        String login = "user";
        when(restTemplate.getForObject(anyString(), any())).thenThrow(new RestClientException("message"));

        //when
        UserDao user = dataProviderService.getUserData(login);

        //then
        assertThat(user).isNull();
    }

    @Test
    void shouldGetUserData() {
        //given
        String login = "userName";
        String name = "name";
        String type = "type";
        String avatarUrl = "/avatar";
        String createdAt = "2011-01-25T18:44:36Z";
        int followers = 4;
        int publicRepos = 10;
        float calcuation = 18.0f;

        RemoteUserData remoteUserData = RemoteUserData.builder()
                .id(1l)
                .login(login)
                .name(name)
                .type(type)
                .avatarUrl(avatarUrl)
                .createdAt(createdAt)
                .followers(followers)
                .publicRepos(publicRepos)
                .build();

        UserDao expected = UserDao.builder()
                .id(1l)
                .login(login)
                .name(name)
                .type(type)
                .avatarUrl(avatarUrl)
                .createdAt(createdAt)
                .calculations(calcuation)
                .build();
        when(restTemplate.getForObject(anyString(), any())).thenReturn(remoteUserData);

        //when
        UserDao user = dataProviderService.getUserData(login);

        //then
        assertThat(user).isEqualTo(expected);
    }
}