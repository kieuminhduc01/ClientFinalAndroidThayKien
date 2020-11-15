package com.example.prmfinal.Client.model;

public class Exercise {
    private String id;
    private String name;
    private float caloriesPerRep;
    private String muscleFocus;
    private String mucDichTap;
    private String equipment;
    private String level;
    private String typeOfExercise;
    private String videoUrl;
    private String imgUrl;

    public Exercise() {
    }

    public Exercise(String name, float caloriesPerRep, String muscleFocus, String mucDichTap, String equipment, String level, String typeOfExercise, String videoUrl, String imgUrl) {
        this.name = name;
        this.caloriesPerRep = caloriesPerRep;
        this.muscleFocus = muscleFocus;
        this.mucDichTap = mucDichTap;
        this.equipment = equipment;
        this.level = level;
        this.typeOfExercise = typeOfExercise;
        this.videoUrl = videoUrl;
        this.imgUrl = imgUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCaloriesPerRep() {
        return caloriesPerRep;
    }

    public void setCaloriesPerRep(float caloriesPerRep) {
        this.caloriesPerRep = caloriesPerRep;
    }

    public String getMuscleFocus() {
        return muscleFocus;
    }

    public void setMuscleFocus(String muscleFocus) {
        this.muscleFocus = muscleFocus;
    }

    public String getMucDichTap() {
        return mucDichTap;
    }

    public void setMucDichTap(String mucDichTap) {
        this.mucDichTap = mucDichTap;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTypeOfExercise() {
        return typeOfExercise;
    }

    public void setTypeOfExercise(String typeOfExercise) {
        this.typeOfExercise = typeOfExercise;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
