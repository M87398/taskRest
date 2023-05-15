package org.taskrest.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.taskrest.activity.ActivityLogger;
import org.taskrest.remotedataprovider.DataProvider;

@Service
@RequiredArgsConstructor
public class UserService {
    private final ActivityLogger activityLogger;
    private final DataProvider dataProvider;

    public UserDao getUser(String login) {
        activityLogger.logActivity(login);
        return dataProvider.getUserData(login);
    }
}
