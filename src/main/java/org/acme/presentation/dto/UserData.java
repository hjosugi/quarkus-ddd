package org.acme.presentation.dto;

import org.acme.domain.User;

public record UserData(
    String id,
    String name) {
  public static UserData from(User u) {
    return new UserData(
        u.getId().getValue(),
        u.getName().getValue());
  }
}