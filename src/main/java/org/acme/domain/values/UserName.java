package org.acme.domain.values;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPAが再構築に使う
public class UserName {

  @NotBlank
  @Size(min = 3, max = 20)
  @Column(name = "name", nullable = false, length = 20)
  private String value;

  public UserName(String value) {
    if (value == null)
      throw new IllegalArgumentException("value must not be null");
    if (value.length() < 3)
      throw new IllegalArgumentException("ユーザ名は3文字以上です。");
    if (value.length() > 20)
      throw new IllegalArgumentException("ユーザ名は20文字以下です。");
    this.value = value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof UserName other))
      return false;
    return Objects.equals(value, other.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public String toString() {
    return value;
  }
}
