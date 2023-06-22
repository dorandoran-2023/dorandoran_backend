package com.dorandoran.dorandoran.core.image.dto;

import com.dorandoran.dorandoran.core.image.domain.ImageType;
import com.dorandoran.dorandoran.core.profile.domain.Profile;
import lombok.Getter;
import org.apache.commons.io.FilenameUtils;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static com.dorandoran.dorandoran.global.util.ImageUtil.checkAndGetImageType;
import static com.dorandoran.dorandoran.global.util.ImageUtil.checkExtension;

@Getter
public abstract class UploadImageDTO {

    private static final List<String> ACCEPTED_FILE_EXTENSIONS
            = Arrays.asList("png", "webp", "jpg", "jpeg", "gif", "bmp", "svg");

    private final InputStream inputStream;
    private final String filename;
    private final String extension;
    private final ImageType imageType;

    // TODO: Profile 엔티티 구현 후 로직 일부 수정
    public UploadImageDTO(Profile profile, InputStream inputStream, String originalFilename, String type) {
        this.inputStream = inputStream;
        this.filename = profile.getUserId();
        this.extension = checkAndGetExtension(originalFilename);
        this.imageType = checkAndGetImageType(type);
    }

    private String checkAndGetExtension(String filename) {
        String extension = FilenameUtils.getExtension(filename);
        checkExtension(extension, ACCEPTED_FILE_EXTENSIONS);
        return extension;
    }
}

