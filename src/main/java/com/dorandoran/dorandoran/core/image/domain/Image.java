package com.dorandoran.dorandoran.core.image.domain;

import com.dorandoran.dorandoran.core.profile.domain.Profile;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "image_type", discriminatorType=DiscriminatorType.STRING)
public abstract class Image {

    @Id @GeneratedValue
    private String id;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private String filename;

    public void update(String url) {
        this.url = url;
    }
}
