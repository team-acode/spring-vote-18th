package com.sniff.springvote18th.repository;

import com.sniff.springvote18th.domain.CandidateVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateVoteRepository extends JpaRepository<CandidateVote, Long> {
}
