package com.robotech.magellan.moviemanager.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String name;

    @Column
    private Integer age;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "director")
    private List<Movie> moviesDirected = new ArrayList<>();

    public Director(){}
    public Director(Long id, String name, Integer age, List<Movie> moviesDirected) {
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Movie> getMoviesDirected() {
        return moviesDirected;
    }

    public void setMoviesDirected(List<Movie> moviesDirected) {
        this.moviesDirected = moviesDirected;
    }

    @Override
    public String toString() {
        return "Director{" +
                "id=" + id +
                ", name=" + name +
                ", age=" + age +
                ", moviesDirected=" + moviesDirected +
                '}';
    }
}
