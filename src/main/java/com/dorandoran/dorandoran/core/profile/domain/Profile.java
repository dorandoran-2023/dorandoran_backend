package com.dorandoran.dorandoran.core.profile.domain;

import static com.dorandoran.dorandoran.global.util.Preconditions.checkNotNull;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.*;

import com.dorandoran.dorandoran.core.common.BaseEntity;
import com.dorandoran.dorandoran.core.user.domain.User;

import com.sun.xml.bind.v2.TODO;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Profile extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(updatable = false)
    private Long id;

    @JoinColumn(name = "user_id")
    @OneToOne
    private User user;

    @Embedded
    private Nickname nickname;

    @Column(nullable = true)
    private String bio;

    public Profile(User user, Nickname nickname, String bio) {
        checkNotNull(nickname, "Profile.nickname cannot be null");
        this.user = user;
        this.nickname = nickname;
        this.bio = bio;
    }
}
