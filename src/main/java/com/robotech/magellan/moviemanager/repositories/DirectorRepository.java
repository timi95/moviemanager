package com.robotech.magellan.moviemanager.repositories;

import com.robotech.magellan.moviemanager.models.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long>, JpaSpecificationExecutor<Director> {
    List<Director> findByName(String name);
}