package com.sniff.springvote18th.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;


@Entity
@Getter
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private TeamName teamName;

    @ColumnDefault("0")
    private int count;

    private String description;
}
