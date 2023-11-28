package com.sniff.springvote18th.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String loginId;
    private String password;
    private String email;
    private String name;

    @Enumerated(EnumType.STRING)
    private TeamName teamName;

    @Enumerated(EnumType.STRING)
    private Part part;

    @Column(columnDefinition = "TINYINT(0) default 0")
    private boolean isCandidateVoted;

    @Column(columnDefinition = "TINYINT(0) default 0")
    private boolean isTeamVoted;

    @Enumerated(EnumType.STRING)
    private Role role;
}
