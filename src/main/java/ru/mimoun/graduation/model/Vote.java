package ru.mimoun.graduation.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vote",
       indexes = {
               @Index(name = "vote_date_restaurant_id_idx",
                      columnList = "restaurant_id, vote_date"),
       },
       uniqueConstraints = {
               @UniqueConstraint(columnNames = {"user_id", "vote_date"},
                                 name = "unique_vote_user_by_date")
       })
public class Vote extends BaseEntity implements Serializable {
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @NotNull
    @Column(name = "vote_date", nullable = false)
    private LocalDate voteDate;
}
