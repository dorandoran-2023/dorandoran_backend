package com.dorandoran.dorandoran.core.image.application;

import com.dorandoran.dorandoran.core.image.dto.UploadImageDTO;

public interface ImageService {

    public String upload(UploadImageDTO image);

    public void delete(String ImageName, String ImageType);
}
