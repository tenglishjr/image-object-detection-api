package org.example.imageobjectdetectionapi.repository;

import org.example.imageobjectdetectionapi.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagesRepository extends JpaRepository<Image, Long> {

}
