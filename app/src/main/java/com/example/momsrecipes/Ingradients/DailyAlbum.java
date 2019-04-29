package com.example.momsrecipes.Ingradients;

public class DailyAlbum {
    private String name;
    private int thumbnail;

    public DailyAlbum() {
    }

    public DailyAlbum(String name, int thumbnail) {
        this.name = name;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}