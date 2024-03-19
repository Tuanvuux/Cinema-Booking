package com.mycompany.spring_mvc_project_final.entities;


import javax.persistence.*;
import java.sql.Time;
import java.util.List;


@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;


    @Column
    private String movieName;


    @Lob
    private byte[] photo;


    @Column
    private String nation;


    @Column
    private String describeMovie;

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Column
    private String director;
    @Column
    private String producer;

    public Time getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(Time timeSlot) {
        this.timeSlot = timeSlot;
    }

    @Column
    private Time timeSlot;
    @Column
    private String actor;


    @Column
    private String trailer;


    @ManyToOne
    @JoinColumn(name = "cateId")
    private Category category;


    @OneToMany(mappedBy = "movie")
    private List<Post> post;


    @OneToMany(mappedBy = "movie")
    private List<Ticket> ticket;


    @OneToMany(mappedBy = "movie")
    private List<MovieShowtime> movieShowtime;


    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getDescribeMovie() {
        return describeMovie;
    }

    public void setDescribeMovie(String describeMovie) {
        this.describeMovie = describeMovie;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Post> getPost() {
        return post;
    }

    public void setPost(List<Post> post) {
        this.post = post;
    }

    public List<Ticket> getTicket() {
        return ticket;
    }

    public void setTicket(List<Ticket> ticket) {
        this.ticket = ticket;
    }

    public List<MovieShowtime> getMovieShowtime() {
        return movieShowtime;
    }

    public void setMovieShowtime(List<MovieShowtime> movieShowtime) {
        this.movieShowtime = movieShowtime;
    }
}
