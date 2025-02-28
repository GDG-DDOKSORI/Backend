package com.bucketNote.bucketNote.app.dto;


import com.bucketNote.bucketNote.domain.entity.User;
import com.bucketNote.bucketNote.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UserCustomDto implements UserDetails {

    private final User user;
    public UserCustomDto(User user) {this.user = user;}
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserSearchDto {
        private Long id;
        private String name;
        private String email;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){

        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return null;
            }
        });

        return collection;
    }

    @Override
    public String getPassword() { return user.getEmail(); }

    @Override
    public String getUsername() { return user.getEmail(); }

    public Long getUserId(){
        return user.getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
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
