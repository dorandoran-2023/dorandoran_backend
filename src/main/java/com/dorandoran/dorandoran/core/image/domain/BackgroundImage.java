package com.dorandoran.dorandoran.core.image.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(ImageType.Values.BACKGROUND)
public class BackgroundImage extends Image {

}
