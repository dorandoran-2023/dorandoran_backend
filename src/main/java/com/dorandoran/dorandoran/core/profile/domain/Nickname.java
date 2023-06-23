package com.dorandoran.dorandoran.core.profile.domain;

import static com.dorandoran.dorandoran.global.util.Preconditions.checkPatternMatches;
import static lombok.AccessLevel.*;

import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = PROTECTED)
@EqualsAndHashCode
@Embeddable
public class Nickname {

    private static final Pattern NICKNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9가-힣]+$");

    @Column(name = "nickname", unique = true, nullable = false)
    private String value;

    public Nickname(String value) {
        checkPatternMatches(NICKNAME_PATTERN, value);
        this.value = value;
    }

    public static Nickname of(String value) {
        return new Nickname(value);
    }
}
