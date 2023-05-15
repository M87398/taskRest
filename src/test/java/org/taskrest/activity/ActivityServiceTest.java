package org.taskrest.activity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ActivityServiceTest {

    private static final String LOGIN ="userAbc";
    @Mock
    private ActivityRepository activityRepository;
    @InjectMocks
    private ActivityService activityService;

    @Test
    void shouldLogActivityForAlreadyExistingLogin() {
        //given
        Activity activity = new Activity();
        when(activityRepository.findByLogin(LOGIN)).thenReturn(activity);

        //when
        activityService.logActivity(LOGIN);

        //then
        verify(activityRepository,times(1)).incrementCounter(LOGIN);
        verify(activityRepository, never()).save(any());
    }
    @Test
    void shouldLogActivityForNotExistingLogin() {
        //given
        when(activityRepository.findByLogin(LOGIN)).thenReturn(null);

        //when
        activityService.logActivity(LOGIN);

        //then
        verify(activityRepository,never()).incrementCounter(LOGIN);
        verify(activityRepository, times(1)).save(any());
    }
}