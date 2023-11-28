package com.sniff.springvote18th.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;


@Entity
@Getter
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidate_id")
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Part part;

    @ColumnDefault("0")
    private int count;
}
