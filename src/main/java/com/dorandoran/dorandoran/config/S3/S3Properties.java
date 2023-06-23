package com.dorandoran.dorandoran.config.S3;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@ConfigurationProperties(prefix = "cloud.aws.s3")
public class S3Properties {
    private final String bucket;

    public S3Properties(String bucket) {
        this.bucket = bucket;
    }
}
