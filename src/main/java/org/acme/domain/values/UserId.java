package org.acme.domain.values;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserId {
    @Column(name = "user_id", nullable = false, length = 36, unique = true)
    private String value;

    public UserId(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("valueがnullまたは空文字です");
        }
        this.value = value;
    }
}
