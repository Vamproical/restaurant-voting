package ru.javaops.topjava2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "vote",
       indexes = {
               @Index(name = "vote_date_restaurant_id_idx", columnList = "RESTAURANT_ID,VOTE_DATE"),
       },
       uniqueConstraints = {
               @UniqueConstraint(name = "unique_vote_user_by_date", columnNames = {"USER_ID", "VOTE_DATE"})
       })
public class Vote extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @NotNull
    @Column(name = "vote_date", nullable = false)
    private LocalDate voteDate;

    @NotNull
    @Column(name = "vote_time", nullable = false)
    private LocalTime voteTime;
}
