package com.sniff.springvote18th.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class CandidateVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidate_vote_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;
}
