package com.zixuanz.mysecretdiary.DataStructures.Home;

import java.util.Date;

/**
 * Created by Zixuan Zhao on 2/19/17.
 */

public class Diary {
    private Date date;
    private String weather;
    private String emotion;
    private String diaryText;
    private int imgID;

    public Diary(Date date, String weather, String emotion, String diaryText) {
        this.date = date;
        this.weather = weather;
        this.emotion = emotion;
        this.diaryText = diaryText;
        this.imgID = -1;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public String getDiaryText() {
        return diaryText;
    }

    public void setDiaryText(String diaryText) {
        this.diaryText = diaryText;
    }

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }
}
