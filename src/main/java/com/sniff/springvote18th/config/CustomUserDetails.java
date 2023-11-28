package com.sniff.springvote18th.config;

import com.sniff.springvote18th.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {
    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auth = new ArrayList<>();
        auth.add(new SimpleGrantedAuthority(user.getRole().name()));
        return auth;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLoginId();
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
