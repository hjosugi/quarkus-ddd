package org.acme.application.command;

public record UserPostCommand(
    String id,
    String name) {
}