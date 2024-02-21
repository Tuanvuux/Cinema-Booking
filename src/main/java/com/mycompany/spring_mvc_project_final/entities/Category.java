package com.mycompany.spring_mvc_project_final.entities;


import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;


    @Column
    private String cateName;


    @OneToMany(mappedBy = "category")
    private List<Movie> movie;


    public Long getCategoryId() {
        return categoryId;
    }


    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }


    public String getCateName() {
        return cateName;
    }


    public void setCateName(String cateName) {
        this.cateName = cateName;
    }


    public List<Movie> getMovie() {
        return movie;
    }


    public void setMovie(List<Movie> movie) {
        this.movie = movie;
    }
}
