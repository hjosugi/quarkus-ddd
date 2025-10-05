package org.acme.presentation.dto;

import java.util.List;

public record UserIndexResponse(List<UserResponse> users) {
}