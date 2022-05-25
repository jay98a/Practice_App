package com.example.practice_api.ui.wallpaper;

public class WallpaperModel {

    private int id;
    private String original, mediumUrl;

    public WallpaperModel() {
    }

    public WallpaperModel(int id, String original, String mediumUrl) {
        this.id = id;
        this.original = original;
        this.mediumUrl = mediumUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getMediumUrl() {
        return mediumUrl;
    }

    public void setMediumUrl(String mediumUrl) {
        this.mediumUrl = mediumUrl;
    }
}
