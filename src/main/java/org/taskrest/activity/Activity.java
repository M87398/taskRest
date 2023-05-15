package org.taskrest.activity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "activitysequence")
    @SequenceGenerator(name = "activitysequence")
    private Long id;
    private String login;//todo index na tej kolumnie

    @Column(name="REQUEST_COUNT",nullable = false)
    @ColumnDefault("0")
    private Long requestCount;
}
