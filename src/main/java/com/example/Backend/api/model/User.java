package com.example.Backend.api.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("User")
public class User implements UserDetails {

    @Id
    private String id;
    private String fname;

    private String lname;

    private Address address;
    private String email;
    private String password;

    // List of authentication tokens associated with the user
    private List<Token> tokens;

    /**
     * Get the unique identifier for the user.
     *
     * @return User's identifier
     */
    public String getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /**
     * Get the user's password.
     *
     * @return User's password
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Get the username (email) used for authentication.
     *
     * @return User's email
     */
    @Override
    public String getUsername() {
        return email;
    }

    /**
     * Check if the user's account has not expired.
     *
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Check if the user's account is not locked.
     *
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Check if the user's credentials (password) are not expired.
     *
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Check if the user is enabled.
     *
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}


