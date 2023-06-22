package com.dorandoran.dorandoran.core.image.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(ImageType.Values.PROFILE)
public class ProfileImage extends Image {

}
