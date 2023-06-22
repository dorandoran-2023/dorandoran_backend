package com.dorandoran.dorandoran.core.image.domain;

public enum ImageType {
    BACKGROUND(Values.BACKGROUND),
    PROFILE(Values.PROFILE);

    private final String value;

    ImageType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static class Values {
        public static final String BACKGROUND = "BACKGROUND";
        public static final String PROFILE = "PROFILE";
    }
}
