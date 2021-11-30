package com.robotech.magellan.moviemanager.DTOs;

import com.robotech.magellan.moviemanager.models.Director;
import com.robotech.magellan.moviemanager.models.Rating;

import java.util.List;

public class MovieDTO {
    public String name;
    public List<Long> ratingsList;
    public DirectorDTO director;

}
