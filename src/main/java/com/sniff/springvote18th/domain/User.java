package com.sniff.springvote18th.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotNull
    private String loginId;

    @NotNull
    private String password;

    @NotNull
    private String email;

    @NotNull
    private String name;

    @Enumerated(EnumType.STRING)
    @NotNull
    private TeamName teamName;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Part part;

    @Column(columnDefinition = "TINYINT(0) default 0")
    private boolean isCandidateVoted;

    @Column(columnDefinition = "TINYINT(0) default 0")
    private boolean isTeamVoted;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("\"USER\"")
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auth = new ArrayList<>();
        auth.add(new SimpleGrantedAuthority(this.role.name()));
        return auth;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.loginId;
    }

    @Override
    public boolean isAccountNonExpired() {
        //계정이 만료됐는지 리턴 -> true는 완료되지 않음 의미
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        //계정이 잠겨있는지 리턴 -> true는 잠기지 않음
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        //비밀번호가 만료됐는지 리턴 -> true는 만료X 의미
        return true;
    }

    @Override
    public boolean isEnabled() {
        //계정이 활성화돼 있는지 리턴 -> true는 활성화 상태 의미
        return true;
    }
}
