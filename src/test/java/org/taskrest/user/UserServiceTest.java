package org.taskrest.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.taskrest.activity.ActivityLogger;
import org.taskrest.remotedataprovider.DataProvider;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    ActivityLogger activityLogger;
    @Mock
    DataProvider dataProvider;
    @InjectMocks
    UserService userService;

    @Test
    void shouldGetUser() {
        //given
        String login = "user";
        UserDao userDao = UserDao.builder().build();
        when(dataProvider.getUserData(login)).thenReturn(userDao);

        //when
        UserDao user = userService.getUser(login);

        //then
        assertThat(user).isEqualTo(userDao);
        verify(activityLogger, times(1)).logActivity(login);
    }
}