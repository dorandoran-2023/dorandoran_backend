package com.dorandoran.dorandoran.core.image.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(ImageType.Values.BACKGROUND)
public class BackgroundImage extends Image {

    public static final String DEFAULT_BACKGROUND_IMAGE = "https://dorandoran-s3.s3.ap-northeast-2.amazonaws.com/BACKGROUND/default.png";
}