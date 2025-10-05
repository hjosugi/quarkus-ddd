package org.acme.presentation.dto;

public record UserResponse(String id, String name) {
  public static UserResponse from(UserData user) {
    return new UserResponse(user.id(), user.name());
  }
}