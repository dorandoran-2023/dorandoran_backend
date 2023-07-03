package com.dorandoran.dorandoran.core.image.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dorandoran.dorandoran.core.image.domain.Image;


public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByProfile_Id(Long profileId);
}
