package org.example.imageobjectdetectionapi.repository;

import org.example.imageobjectdetectionapi.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagesRepository extends JpaRepository<Image, Long> {

	@Query(nativeQuery = true,
			value = "select i.* from images as i INNER JOIN image_tags as it ON i.id = it.tags_fkey where it.tag = ANY(:objects)")
	List<Image> findAllByTags(@Param("objects") String[] objects);

}
