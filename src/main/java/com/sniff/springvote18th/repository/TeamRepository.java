package com.sniff.springvote18th.repository;

import com.sniff.springvote18th.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    @Query("SELECT t.id FROM Team t ORDER BY t.count DESC")
    List<Team> getTeamList();

    @Modifying
    @Query("UPDATE Team t SET t.count = t.count + 1 WHERE t.id = :teamId")
    void updateVoteCount(@Param("teamId") Long teamId);
}
