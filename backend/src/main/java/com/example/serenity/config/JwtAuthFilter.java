package com.example.serenity.config;

import com.example.serenity.entity.User;
import com.example.serenity.repository.UserRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import java.io.IOException;

@Component
public class JwtAuthFilter extends GenericFilter {
  private final JwtUtil jwt;
  private final UserRepository users;

  public JwtAuthFilter(JwtUtil jwt, UserRepository users) {
    this.jwt = jwt;
    this.users = users;
  }

  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) req;
    String header = request.getHeader("Authorization");
    if (StringUtils.hasText(header) && header.startsWith("Bearer ")) {
      String token = header.substring(7);
      try {
        String email = jwt.getSubject(token);
        users.findByEmail(email).ifPresent(u -> {
          var auth = new UsernamePasswordAuthenticationToken(email, null, java.util.List.of());
          SecurityContextHolder.getContext().setAuthentication(auth);
        });
      } catch (Exception ignored) {
      }
    }
    chain.doFilter(req, res);
  }
}
