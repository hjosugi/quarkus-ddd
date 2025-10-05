package org.acme.presentation.dto;

import org.acme.domain.User;
import org.acme.domain.values.UserId;
import org.acme.domain.values.UserName;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "cdi", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {

  @Mapping(target = "id", source = "userId")
  @Mapping(target = "name", source = "name")
  UserData toDto(User user);

  default User toEntity(UserData dto) {
    return new User(
        new UserId(dto.id()),
        new UserName(dto.name()));
  }

  default String mapId(UserId v) {
    return v == null ? null : v.getValue();
  }

  default String mapName(UserName v) {
    return v == null ? null : v.getValue();
  }
}
