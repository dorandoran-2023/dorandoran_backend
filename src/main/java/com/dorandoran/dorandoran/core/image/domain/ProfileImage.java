package com.dorandoran.dorandoran.core.image.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(ImageType.Values.PROFILE)
public class ProfileImage extends Image {

    public static final String DEFAULT_PROFILE_IMAGE = "https://dorandoran-s3.s3.ap-northeast-2.amazonaws.com/PROFILE/default.png";
}
