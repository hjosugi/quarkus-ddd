package org.acme.application.command;

public record UserUpdateCommand(
    String id,
    String name) {
}