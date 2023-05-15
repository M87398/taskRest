package org.taskrest.activity;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ActivityService implements ActivityLogger {
    private final ActivityRepository activityRepository;

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void logActivity(String login) {
        Activity activity = activityRepository.findByLogin(login);
        if (activity == null) {
            activity = Activity.builder()
                    .login(login)
                    .requestCount(1l)
                    .build();
            activityRepository.save(activity);
        } else {
            activityRepository.incrementCounter(login);
        }
    }
}
