package org.taskrest.remotedataprovider;

import org.taskrest.user.UserDao;

public interface DataProvider {
    UserDao getUserData(String login);
}
