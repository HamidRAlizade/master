package com.hamid.yara.favorites;

import com.hamid.yara.Movie;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * @author arun
 */
@Singleton
public class FavoritesStore
{
    private Realm realm;

    @Inject
    public FavoritesStore(Realm realm)
    {
        this.realm = realm;
    }

    public void setFavorite(Movie movie)
    {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(new MovieRealmObject(movie));
        realm.commitTransaction();
    }

    public boolean isFavorite(String id)
    {
        MovieRealmObject res =  realm.where(MovieRealmObject.class).equalTo(MovieRealmObject.ID, id).findFirst();
        return res != null;
    }

    public List<Movie> getFavorites()
    {
        RealmResults<MovieRealmObject> res =  realm.where(MovieRealmObject.class).findAll();
        List<Movie> movies = new ArrayList<>();

        for(MovieRealmObject i : res){
            movies.add(movieRealmObjectToMovie(i));
        }

        return movies;
    }

    public void unfavorite(String id)
    {
        realm.beginTransaction();
        MovieRealmObject movie = realm.where(MovieRealmObject.class).equalTo(MovieRealmObject.ID, id).findFirst();
        if(movie != null)
            movie.deleteFromRealm();
        realm.commitTransaction();
    }

    private Movie movieRealmObjectToMovie(MovieRealmObject movieRealmObject){
        Movie movie = new Movie();
        movie.setImdbID(movieRealmObject.getId());
        movie.setPoster(movieRealmObject.getOverview());
        movie.setTitle(movieRealmObject.getReleaseDate());
        movie.setYear(movieRealmObject.getPosterPath());
        movie.setType(movieRealmObject.getBackdropPath());


        return movie;
    }
}
