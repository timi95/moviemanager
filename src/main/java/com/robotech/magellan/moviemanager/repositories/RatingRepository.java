package com.robotech.magellan.moviemanager.repositories;

import com.robotech.magellan.moviemanager.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long>, JpaSpecificationExecutor<Rating> {

    List<Rating> findByRatingValueGreaterThan(Long val);
}