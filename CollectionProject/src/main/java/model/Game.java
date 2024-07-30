/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author evelinsteiger
 */
public class Game {
    
    private enum gameStatus {
        PENDING,
        PLAYING_NOW,
        FINISHED,
        NOT_INTERESTED;
    }
    
    private int id;
    private String gender;
    private String name;
    private Float rating;
    private String release_dates;
    private boolean favorite;
    private gameStatus status;
   

    public Game() {
    }

    public Game(int id, String gender, String name, Float rating, String release_dates, boolean favorite, gameStatus status) {
        this.id = id;
        this.gender = gender;
        this.name = name;
        this.rating = rating;
        this.release_dates = release_dates;
        this.favorite = favorite;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getRelease_dates() {
        return release_dates;
    }

    public void setRelease_dates(String release_dates) {
        this.release_dates = release_dates;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public gameStatus getStatus() {
        return status;
    }

    public void setStatus(gameStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Game{" + "id=" + id + ", gender=" + gender + ", name=" + name + ", rating=" + rating + ", release_dates=" + release_dates + ", favorite=" + favorite + '}';
    }
}
