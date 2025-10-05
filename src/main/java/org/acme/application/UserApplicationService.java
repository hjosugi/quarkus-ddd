package org.acme.application;

import java.util.List;

import org.acme.application.command.UserDeleteCommand;
import org.acme.application.command.UserGetCommand;
import org.acme.application.command.UserUpdateCommand;
import org.acme.domain.User;
import org.acme.domain.UserRepository;
import org.acme.domain.UserService;
import org.acme.domain.exception.CannotRegisterUserException;
import org.acme.domain.exception.UserNotFoundException;
import org.acme.domain.values.UserId;
import org.acme.domain.values.UserName;
import org.acme.application.command.UserRegisterCommand;
import org.acme.presentation.dto.UserData;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

// usecase
@ApplicationScoped
public class UserApplicationService {

  private final UserRepository userRepository;
  private final UserService userService;

  @Inject
  public UserApplicationService(UserRepository userRepository, UserService userService) {
    this.userRepository = userRepository;
    this.userService = userService;
  }

  @Transactional
  public void register(UserRegisterCommand command) {
    var user = new User(new UserName(command.name()));
    if (userService.exists(user))
      throw new CannotRegisterUserException("既に存在します");
    userRepository.save(user);
  }

  public List<UserData> getAll() {
    return userRepository.findAllUserData();
  }

  public UserData get(UserGetCommand command) {
    return userRepository.find(new UserId(command.id()))
        .map(UserData::from)
        .orElseThrow(() -> new UserNotFoundException("not found"));
  }

  @Transactional
  public void update(UserUpdateCommand command) {
    var user = userRepository.find(new UserId(command.id()))
        .orElseThrow(() -> new UserNotFoundException("not found"));
    user.changeName(new UserName(command.name()));
    userRepository.save(user);
  }

  @Transactional
  public void delete(UserDeleteCommand command) {
    userRepository.find(new UserId(command.id()))
        .ifPresent(userRepository::delete);
  }
}