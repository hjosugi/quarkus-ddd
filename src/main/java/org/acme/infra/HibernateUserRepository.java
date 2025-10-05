package org.acme.infra;

import java.util.List;
import java.util.Optional;

import org.acme.domain.User;
import org.acme.domain.UserRepository;
import org.acme.domain.values.UserId;
import org.acme.domain.values.UserName;
import org.acme.presentation.dto.UserData;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class HibernateUserRepository implements UserRepository, PanacheRepository<User> {

  @Override
  public Optional<User> find(UserId id) {
    return find("userId.value", id.getValue()).firstResultOptional();
  }

  @Override
  public Optional<User> find(UserName name) {
    return find("name.value", name.getValue()).firstResultOptional();
  }

  @Override
  public List<UserData> findAllUserData() {
    return getEntityManager().createQuery(
        "select new org.acme.dto.UserData(u.userId.value, u.name.value) from User u",
        UserData.class).getResultList();
  }

  @Override
  @Transactional
  public void save(User user) {
    if (user.isPersistent()) {
      getEntityManager().merge(user);
    } else {
      persist(user);
    }
  }

  @Override
  public PanacheQuery<User> findAll() {
    return findAll();
  }

  @Override
  @Transactional
  public void delete(User user) {
    if (user.isPersistent()) {
      user.delete();
    } else {
      find(user.getId()).ifPresent(User::delete);
    }
  }
}
