package com.mycompany.spring_mvc_project_final.entities;


import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;


    @Column
    private String movieName;


    @Column
    private String movie;


    @Column
    private String photo;


    @Column
    private String nation;


    @Column
    private String describe;


    @Column
    private String producer;


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


    public String getMovie() {
        return movie;
    }


    public void setMovie(String movie) {
        this.movie = movie;
    }


    public String getPhoto() {
        return photo;
    }


    public void setPhoto(String photo) {
        this.photo = photo;
    }


    public String getNation() {
        return nation;
    }


    public void setNation(String nation) {
        this.nation = nation;
    }


    public String getDescribe() {
        return describe;
    }


    public void setDescribe(String describe) {
        this.describe = describe;
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
