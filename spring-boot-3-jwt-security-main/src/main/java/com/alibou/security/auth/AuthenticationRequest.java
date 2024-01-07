package com.alibou.security.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A data class representing the request for user authentication.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

  /**
   * The email address of the user for authentication.
   */
  private String email;

  /**
   * The password of the user for authentication.
   */
  private String password;
}
