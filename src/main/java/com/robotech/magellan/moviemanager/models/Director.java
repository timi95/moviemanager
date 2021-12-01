package com.robotech.magellan.moviemanager.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String name;

    @Column
    private Long age;

    @JsonIgnoreProperties({"director"})
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "director")
    private Set<Movie> moviesDirected = new HashSet<>();

    public Director(){}
    public Director(String name, Long age){
        this.name = name;
        this.age = age;
    }
    public Director(Long id, String name, Long age, Set<Movie> moviesDirected) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.moviesDirected = moviesDirected;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Set<Movie> getMoviesDirected() {
        return moviesDirected;
    }

    public void setMoviesDirected(Set<Movie> moviesDirected) {
        this.moviesDirected = moviesDirected;
    }

    public void appendMovieToMoviesDirected(Movie movie){
        this.moviesDirected.add(movie);
    }

    @Override
    public String toString() {
        return "Director{" +
                "id=" + id +
                ", name=" + name +
                ", age=" + age +
//                ", moviesDirected=" + moviesDirected.toString() +
                '}';
    }
}
