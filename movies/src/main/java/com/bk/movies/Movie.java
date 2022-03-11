package com.bk.movies;

import javax.persistence.*;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column
    private String title;

    @Column
    private String director;

    @Column
    private String distributor;

    @Column
    private int rating;

    @Column
    private int numberOfVotes;

    public Movie() {
        //for Persistence
    }

    public Movie(int id, String title, String director, String distributor, int rating, int numberOfVotes) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.distributor = distributor;
        this.rating = rating;
        this.numberOfVotes = numberOfVotes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDistributor() {
        return distributor;
    }

    public void setDistributor(String distributor) {
        this.distributor = distributor;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setNumberOfVotes(int numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }
}
