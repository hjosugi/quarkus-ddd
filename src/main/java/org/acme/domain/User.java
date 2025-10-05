package org.acme.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.UUID;

import org.acme.domain.values.UserId;
import org.acme.domain.values.UserName;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends PanacheEntity {
  @Embedded
  private UserId userId;
  @Embedded
  private UserName name;

  public User(UserName name) {
    this.userId = new UserId(UUID.randomUUID().toString());
    this.name = Objects.requireNonNull(name);
  }

  public User(UserId id, UserName name) {
    this.userId = Objects.requireNonNull(id);
    this.name = Objects.requireNonNull(name);
  }

  public void changeName(UserName name) {
    this.name = Objects.requireNonNull(name);
  }

  public UserId getId() {
    return this.userId;
  }
}
