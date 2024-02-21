package com.mycompany.spring_mvc_project_final.entities;

import javax.persistence.*;

@Entity
@Table
public class MovieShowtime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieShowtimeId;

    @ManyToOne
    @JoinColumn(name = "movieId")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "showTimeId")
    private ShowTime showTime;

    public Long getMovieShowtimeId() {
        return movieShowtimeId;
    }

    public void setMovieShowtimeId(Long movieShowtimeId) {
        this.movieShowtimeId = movieShowtimeId;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public ShowTime getShowTime() {
        return showTime;
    }

    public void setShowTime(ShowTime showTime) {
        this.showTime = showTime;
    }
}
