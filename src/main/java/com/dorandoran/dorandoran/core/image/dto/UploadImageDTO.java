package com.dorandoran.dorandoran.core.image.dto;

import static com.dorandoran.dorandoran.global.util.ImageUtil.checkExtension;
import static com.dorandoran.dorandoran.global.util.ImageUtil.toInputStream;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FilenameUtils;

import org.springframework.web.multipart.MultipartFile;

import com.dorandoran.dorandoran.core.image.domain.ImageType;

import lombok.Getter;

@Getter
public class UploadImageDTO {

    private static final List<String> ACCEPTED_FILE_EXTENSIONS
            = Arrays.asList("png", "webp", "jpg", "jpeg", "gif", "bmp", "svg");

    private final InputStream inputStream;
    private String filename;
    private final String extension;
    private final ImageType imageType;

    public UploadImageDTO(MultipartFile file, ImageType imageType) {
        this.inputStream = toInputStream(file);
        this.filename = file.getOriginalFilename();
        this.extension = checkAndGetExtension(file.getOriginalFilename());
        this.imageType = imageType;
    }

    private String checkAndGetExtension(String filename) {
        String extension = FilenameUtils.getExtension(filename);
        checkExtension(extension, ACCEPTED_FILE_EXTENSIONS);
        return extension;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}

