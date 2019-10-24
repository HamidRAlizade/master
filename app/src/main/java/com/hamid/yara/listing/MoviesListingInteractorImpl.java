package com.hamid.yara.listing;

import com.hamid.yara.Movie;
import com.hamid.yara.MoviesWraper;
import com.hamid.yara.favorites.FavoritesInteractor;
import com.hamid.yara.listing.sorting.SortType;
import com.hamid.yara.listing.sorting.SortingOptionStore;
import com.hamid.yara.network.TmdbWebService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import io.reactivex.Observable;

/**
 * @author arun
 */
class MoviesListingInteractorImpl implements MoviesListingInteractor {
    private FavoritesInteractor favoritesInteractor;
    private TmdbWebService tmdbWebService;
    private SortingOptionStore sortingOptionStore;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final int NEWEST_MIN_VOTE_COUNT = 50;

    MoviesListingInteractorImpl(FavoritesInteractor favoritesInteractor,
                                TmdbWebService tmdbWebService, SortingOptionStore store) {
        this.favoritesInteractor = favoritesInteractor;
        this.tmdbWebService = tmdbWebService;
        sortingOptionStore = store;
    }


    @Override
    public boolean isPaginationSupported() {
        int selectedOption = sortingOptionStore.getSelectedOption();
        return selectedOption != SortType.FAVORITES.getValue();
    }

    @Override
    public Observable<List<Movie>> fetchMovies() {
        return tmdbWebService.popularMovies("batman").map(MoviesWraper::getMovieList);
    }



}
