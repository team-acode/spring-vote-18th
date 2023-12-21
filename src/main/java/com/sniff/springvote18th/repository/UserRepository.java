package com.sniff.springvote18th.repository;

import com.sniff.springvote18th.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLoginId(String loginId);

    Optional<User> findByEmail(String email);

    @Modifying
    @Query("UPDATE User u SET u.isTeamVoted = true WHERE u.id = :userId")
    void updateIsTeamVoted(@Param("userId") Long userId);

    @Modifying
    @Query("UPDATE User u SET u.isCandidateVoted = true WHERE u.id = :userId")
    void updateIsCandidateVoted(@Param("userId") Long userId);
}
