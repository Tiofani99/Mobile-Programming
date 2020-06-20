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
public class TvSeries implements Parcelable {

    @PrimaryKey
    @NonNull
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    @ColumnInfo(name = "name")
    private String name;

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

    @SerializedName("first_air_date")
    @ColumnInfo(name = "first_air_date")
    private String first_air_date;

    @SerializedName("popularity")
    @ColumnInfo(name = "popularity")
    private Double popularity;

    @SerializedName("results")
    private ArrayList<TvSeries> tvSeriesList;


    public TvSeries(int id, String name, String img, String vote_count, Double vote_avg, String desc, String first_air_date, Double popularity) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.vote_count = vote_count;
        this.vote_avg = vote_avg;
        this.desc = desc;
        this.first_air_date = first_air_date;
        this.popularity = popularity;
    }

    public TvSeries() {
    }

    protected TvSeries(Parcel in) {
        id = in.readInt();
        name = in.readString();
        img = in.readString();
        vote_count = in.readString();
        if (in.readByte() == 0) {
            vote_avg = null;
        } else {
            vote_avg = in.readDouble();
        }
        desc = in.readString();
        first_air_date = in.readString();
        if (in.readByte() == 0) {
            popularity = null;
        } else {
            popularity = in.readDouble();
        }
        tvSeriesList = in.createTypedArrayList(TvSeries.CREATOR);
    }

    public static final Creator<TvSeries> CREATOR = new Creator<TvSeries>() {
        @Override
        public TvSeries createFromParcel(Parcel in) {
            return new TvSeries(in);
        }

        @Override
        public TvSeries[] newArray(int size) {
            return new TvSeries[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public ArrayList<TvSeries> getTvSeriesList() {
        return tvSeriesList;
    }

    public void setTvSeriesList(ArrayList<TvSeries> tvSeriesList) {
        this.tvSeriesList = tvSeriesList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(img);
        parcel.writeString(vote_count);
        if (vote_avg == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(vote_avg);
        }
        parcel.writeString(desc);
        parcel.writeString(first_air_date);
        if (popularity == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(popularity);
        }
        parcel.writeTypedList(tvSeriesList);
    }
}
