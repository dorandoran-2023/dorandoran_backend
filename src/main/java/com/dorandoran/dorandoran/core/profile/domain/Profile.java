package com.dorandoran.dorandoran.core.profile.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// profile 브랜치에서 구현 예정
@Entity
public class Profile {

    @Id @GeneratedValue
    private Long id;

    public String getUserId() {
        return "userId";
    }
}
