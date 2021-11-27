package com.robotech.magellan.moviemanager.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private Long name;

    @Column
    private Integer age;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "director")
    private List<Movie> moviesDirected = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
