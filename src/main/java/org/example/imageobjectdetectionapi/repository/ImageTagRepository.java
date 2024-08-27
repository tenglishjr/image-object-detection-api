package org.example.imageobjectdetectionapi.repository;

import org.example.imageobjectdetectionapi.model.ImageTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageTagRepository extends JpaRepository<ImageTag, Long> {
}
