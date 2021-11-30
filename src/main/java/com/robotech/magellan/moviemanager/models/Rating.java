package com.robotech.magellan.moviemanager.models;

import io.swagger.models.auth.In;

import javax.persistence.*;

@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String critic;

    @Column
    private Long ratingValue;

    @Column
    private Long movie_ref;

    public Rating(){
    }
    public Rating(Long ratingValue){
        this.ratingValue = ratingValue;
    }

    public Rating(Long id, String critic, Long ratingValue, Long movie_ref) {
        this.id = id;
        this.critic = critic;
        this.ratingValue = ratingValue;
        this.movie_ref = movie_ref;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCritic() {
        return critic;
    }

    public void setCritic(String critic) {
        this.critic = critic;
    }

    public Long getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(Long ratingValue) {
        this.ratingValue = ratingValue;
    }

    public Long getMovie_ref() {
        return movie_ref;
    }

    public void setMovie_ref(Long movie_ref) {
        this.movie_ref = movie_ref;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", critic='" + critic + '\'' +
                ", ratingValue=" + ratingValue +
                ", movie_ref=" + movie_ref +
                '}';
    }
}
