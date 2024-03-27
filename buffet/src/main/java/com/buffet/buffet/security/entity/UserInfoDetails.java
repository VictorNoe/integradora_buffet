package com.buffet.buffet.security.entity;

import com.buffet.buffet.model.useraccount.UserAccount;
import com.buffet.buffet.model.usertype.UserType;
import com.buffet.buffet.model.worker.Worker;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserInfoDetails implements UserDetails {
    private String username;
    private String password;
    private boolean blocked;
    private Collection<? extends GrantedAuthority> authorities;

    public UserInfoDetails(String username, String password, boolean blocked, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.blocked = blocked;
        this.authorities = authorities;
    }

    public static UserInfoDetails build(UserAccount user) {
        UserType userType = user.getFkUserInfo().getFkUserType();

        Set<GrantedAuthority> authorities =
                Stream.of(userType)
                        .map(role -> new SimpleGrantedAuthority(role.getTypeName()))
                        .collect(Collectors.toSet());

        return new UserInfoDetails(
                user.getEmail(), user.getUserPassword(),
                user.isLocked(), authorities
        );
    }

    public static UserInfoDetails build(Worker worker) {
        UserType userType = worker.getFkUserInfo().getFkUserType();

        Set<GrantedAuthority> authorities =
                Stream.of(userType)
                        .map(role -> new SimpleGrantedAuthority(role.getTypeName()))
                        .collect(Collectors.toSet());

        return new UserInfoDetails(
                worker.getNumWorker(), worker.getWorkerPassword(),
                worker.isLockedWorker(), authorities
        );
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return blocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
