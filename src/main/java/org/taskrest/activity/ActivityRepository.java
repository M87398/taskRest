package org.taskrest.activity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ActivityRepository extends JpaRepository<Activity,Long> {
    @Query("update Activity SET requestCount=requestCount + 1 where login = :login")
    @Modifying
    void incrementCounter(@Param("login") String login);

    Activity findByLogin(String login);
}
