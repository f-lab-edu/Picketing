package com.picketing.www.application.filter.encoding;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.picketing.www.application.filter.encoding.password.HttpRequestWrapper;
import com.picketing.www.application.filter.encoding.password.PasswordEncoder;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@WebFilter(urlPatterns = {"/api/users"})
@RequiredArgsConstructor
public class PasswordEncodingFilter implements Filter {

  private final PasswordEncoder passwordEncoder;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpRequestWrapper requestWrapper = new HttpRequestWrapper((HttpServletRequest) request);
    String body = requestWrapper.getBody();

    String resolveJson = changePassword(body);

    requestWrapper.setBody(resolveJson);

    chain.doFilter(requestWrapper, response);
  }

  private String changePassword(String json)
      throws JsonProcessingException {
    final String FIELD_NAME = "password";
    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode rootNode = objectMapper.readTree(json);
    if (rootNode.isObject()) {
      ObjectNode objectNode = (ObjectNode) rootNode;
      if (objectNode.has(FIELD_NAME)) {
        String plainTextPassword = String.valueOf(objectNode.get("password"));
        String encodedPassword = passwordEncoder.encode(plainTextPassword);
        objectNode.put(FIELD_NAME, encodedPassword);
      }
    }
    return objectMapper.writeValueAsString(rootNode);
  }
}
