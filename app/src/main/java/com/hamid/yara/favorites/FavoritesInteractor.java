package com.hamid.yara.favorites;

import com.hamid.yara.Movie;

import java.util.List;

/**
 * @author arun
 */
public interface FavoritesInteractor
{
    void setFavorite(Movie movie);
    boolean isFavorite(String id);
    List<Movie> getFavorites();
    void unFavorite(String id);
}
