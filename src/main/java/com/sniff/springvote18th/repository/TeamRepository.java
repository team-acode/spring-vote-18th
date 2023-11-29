package com.sniff.springvote18th.repository;

import com.sniff.springvote18th.domain.Team;
import com.sniff.springvote18th.domain.TeamName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    @Query("SELECT t FROM Team t ORDER BY t.count DESC")
    List<Team> getTeamList();

    @Modifying
    @Query("UPDATE Team t SET t.count = t.count + 1 WHERE t.teamName = :teamName")
    void updateVoteCount(@Param("teamName") TeamName teamName);

    Optional<Team> findByTeamName(TeamName teamName);
}
