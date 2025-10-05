package org.acme.domain;

import java.util.List;
import java.util.Optional;

import org.acme.domain.values.UserId;
import org.acme.domain.values.UserName;
import org.acme.presentation.dto.UserData;

import io.quarkus.hibernate.orm.panache.PanacheQuery;

public interface UserRepository {
  PanacheQuery<User> findAll();

  List<UserData> findAllUserData();

  Optional<User> find(UserId id);

  Optional<User> find(UserName name);

  void save(User user);

  void delete(User user);
}
