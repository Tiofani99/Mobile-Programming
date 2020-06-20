package com.tiooooo.mymovie.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Movie implements Parcelable {

    @SerializedName("id")
    @PrimaryKey
    @NonNull
    private int id;

    @SerializedName("title")
    @ColumnInfo(name = "title")
    private String title;

    @SerializedName("poster_path")
    @ColumnInfo(name = "poster_path")
    private String img;

    @SerializedName("vote_count")
    @ColumnInfo(name = "vote_count")
    private String vote_count;

    @SerializedName("vote_average")
    @ColumnInfo(name = "vote_average")
    private Double vote_avg;

    @SerializedName("overview")
    @ColumnInfo(name = "overview")
    private String desc;

    @SerializedName("release_date")
    @ColumnInfo(name = "release_date")
    private String release_date;

    @SerializedName("popularity")
    @ColumnInfo(name = "popularity")
    private Double popularity;


    @SerializedName("results")
    private ArrayList<Movie> list;

    protected Movie(Parcel in) {
        id = in.readInt();
        title = in.readString();
        img = in.readString();
        vote_count = in.readString();
        if (in.readByte() == 0) {
            vote_avg = null;
        } else {
            vote_avg = in.readDouble();
        }
        desc = in.readString();
        release_date = in.readString();
        if (in.readByte() == 0) {
            popularity = null;
        } else {
            popularity = in.readDouble();
        }
        list = in.createTypedArrayList(Movie.CREATOR);
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public ArrayList<Movie> getList() {
        return list;
    }

    public void setList(ArrayList<Movie> list) {
        this.list = list;
    }

    public Movie(int id, String title, String img, String vote_count, Double vote_avg, String desc, String release_date, Double popularity) {
        this.id = id;
        this.title = title;
        this.img = img;
        this.vote_count = vote_count;
        this.vote_avg = vote_avg;
        this.desc = desc;
        this.release_date = release_date;
        this.popularity = popularity;
    }

    public Movie() {
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getVote_count() {
        return vote_count;
    }

    public void setVote_count(String vote_count) {
        this.vote_count = vote_count;
    }

    public Double getVote_avg() {
        return vote_avg;
    }

    public void setVote_avg(Double vote_avg) {
        this.vote_avg = vote_avg;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(img);
        parcel.writeString(vote_count);
        if (vote_avg == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(vote_avg);
        }
        parcel.writeString(desc);
        parcel.writeString(release_date);
        if (popularity == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(popularity);
        }
        parcel.writeTypedList(list);
    }
}
