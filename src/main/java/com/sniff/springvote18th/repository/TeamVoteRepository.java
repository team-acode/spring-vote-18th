package com.sniff.springvote18th.repository;

import com.sniff.springvote18th.domain.TeamVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamVoteRepository extends JpaRepository<TeamVote, Long> {
}