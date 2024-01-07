package com.alibou.security.user;

import com.alibou.security.token.Token;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User implements UserDetails {
    // MongoDB document identifier
    @Id
    private String id;

    // User's first name
    private String firstname;

    // User's last name
    private String lastname;

    // User's email address (used as username)
    private String email;

    // User's hashed password
    private String password;

    // User's role (e.g., ROLE_USER, ROLE_ADMIN)
    private Role role;

    // List of authentication tokens associated with the user
    private List<Token> tokens;

    /**
     * Get the authorities granted to the user based on their role.
     *
     * @return Collection of authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Convert the user's role into a collection of GrantedAuthority objects
        return role.getAuthorities();
    }

    /**
     * Get the unique identifier for the user.
     *
     * @return User's identifier
     */
    public String getId() {
        return id;
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



