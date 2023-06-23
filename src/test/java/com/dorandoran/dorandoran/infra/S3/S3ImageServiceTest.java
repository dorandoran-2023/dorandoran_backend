package com.dorandoran.dorandoran.infra.S3;

import java.io.IOException;
import java.nio.file.Files;

import com.dorandoran.dorandoran.core.image.application.ImageService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.dorandoran.dorandoran.core.image.domain.ImageType;
import com.dorandoran.dorandoran.core.image.dto.UploadImageDTO;

@SpringBootTest
class S3ImageServiceTest {

    @Autowired
    private ImageService imageService;
    @Autowired
    private ResourceLoader resourceLoader;

    @Test
    void 업로드_성공() throws IOException {
        // given
        String name = "image.png";
        String originalFileName = "image.png";
        String contentType = "image/png";

        Resource resource = resourceLoader.getResource("classpath:store/images/image.png");
        byte[] content = Files.readAllBytes(resource.getFile().toPath());
        MultipartFile multipartFile = new MockMultipartFile(name, originalFileName, contentType, content);

        UploadImageDTO image = new UploadImageDTO(multipartFile, ImageType.PROFILE);

        // when
        String url = imageService.upload(image);

        // then
        Assertions.assertThat(url).contains("dorandoran-s3.s3.ap-northeast-2.amazonaws.com");
    }
}