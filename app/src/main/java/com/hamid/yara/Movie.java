package com.hamid.yara;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable
{


    private String Poster;
    private String imdbID;
    private String Year;
    private String Title;
    private String Type;
    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }
    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public static Creator<Movie> getCREATOR() {
        return CREATOR;
    }




    public Movie()
    {

    }

    protected Movie(Parcel in)
    {
        Poster = in.readString();
        imdbID = in.readString();
        Type = in.readString();
        Year = in.readString();
        Title = in.readString();

    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>()
    {
        @Override
        public Movie createFromParcel(Parcel in)
        {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size)
        {
            return new Movie[size];
        }
    };


    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i)
    {

        parcel.writeString(Poster);
        parcel.writeString(imdbID);
        parcel.writeString(Type);
        parcel.writeString(Year);
        parcel.writeString(Title);

    }
}
