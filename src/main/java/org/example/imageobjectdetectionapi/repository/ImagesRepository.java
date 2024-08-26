package org.example.imageobjectdetectionapi.repository;

import org.example.imageobjectdetectionapi.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagesRepository extends JpaRepository<Image, Long> {

	@Query(nativeQuery = true,
			value = "select * from images where POSITION(ARRAY_TO_STRING(:objects, ',') in tags) > 0")
	List<Image> findAllByTags(@Param("objects") String[] objects);

}
