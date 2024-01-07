package com.alibou.security.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A data class representing the response after successful authentication.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

  /**
   * The access token generated after successful authentication.
   */
  @JsonProperty("access_token")
  private String accessToken;

  /**
   * The refresh token generated after successful authentication.
   */
  @JsonProperty("refresh_token")
  private String refreshToken;
}
