package com.sniff.springvote18th.repository;

import com.sniff.springvote18th.domain.Candidate;
import com.sniff.springvote18th.domain.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    @Query("SELECT c FROM Candidate c WHERE c.part = :part")
    List<Candidate> getCandidateList(@Param("part") Part part);

    @Query("SELECT c FROM Candidate c WHERE c.part = :part ORDER BY c.count DESC")
    List<Candidate> getCandidateListDesc(@Param("part") Part part);

    @Modifying
    @Query("UPDATE Candidate c SET c.count = c.count + 1 WHERE c.id = :userId")
    void updateVoteCount(@Param("userId") Long userId);
}
