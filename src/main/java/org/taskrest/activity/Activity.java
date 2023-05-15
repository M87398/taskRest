package org.taskrest.activity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import jakarta.persistence.Index;

@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(indexes = @Index(name = "idx_login", unique = true, columnList = "login"))
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activitysequence")
    @SequenceGenerator(name = "activitysequence")
    private Long id;
    private String login;

    @Column(name = "REQUEST_COUNT", nullable = false)
    @ColumnDefault("0")
    private Long requestCount;
}
