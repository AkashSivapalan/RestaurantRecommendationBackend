package com.alibou.security.config;

import com.alibou.security.token.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

/**
 * Service class for handling user logout and token invalidation.
 */
@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

  private final TokenRepository tokenRepository;

  /**
   * Logout method to handle user logout and token invalidation.
   *
   * @param request        The HTTP request.
   * @param response       The HTTP response.
   * @param authentication The authentication object representing the current user.
   */
  @Override
  public void logout(
          HttpServletRequest request,
          HttpServletResponse response,
          Authentication authentication
  ) {
    final String authHeader = request.getHeader("Authorization");
    final String jwt;

    // Check if the Authorization header is present and starts with "Bearer ".
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      return;
    }

    // Extract the JWT token from the Authorization header.
    jwt = authHeader.substring(7);

    // Retrieve the stored token from the repository.
    var storedToken = tokenRepository.findByToken(jwt).orElse(null);

    // If the stored token is found, mark it as expired and revoked.
    if (storedToken != null) {
      storedToken.setExpired(true);
      storedToken.setRevoked(true);

      // Save the updated token in the repository.
      tokenRepository.save(storedToken);

      // Clear the security context, effectively logging the user out.
      SecurityContextHolder.clearContext();
    }
  }
}
