package com.hamid.yara.listing;

import android.util.Log;

import com.hamid.yara.Movie;
import com.hamid.yara.util.EspressoIdlingResource;
import com.hamid.yara.util.RxUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author arun
 */
class MoviesListingPresenterImpl implements MoviesListingPresenter {
    private MoviesListingView view;
    private MoviesListingInteractor moviesInteractor;
    private Disposable fetchSubscription;
    private Disposable movieSearchSubscription;
    private int currentPage = 1;
    private List<Movie> loadedMovies = new ArrayList<>();
    private boolean showingSearchResult = false;

    MoviesListingPresenterImpl(MoviesListingInteractor interactor) {
        moviesInteractor = interactor;
    }

    @Override
    public void setView(MoviesListingView view) {
        this.view = view;
        if(!showingSearchResult){
            displayMovies();
        }

    }

    @Override
    public void destroy() {
        view = null;
        RxUtils.unsubscribe(fetchSubscription, movieSearchSubscription);
    }

    private void displayMovies() {
        EspressoIdlingResource.increment();
        showLoading();
        fetchSubscription = moviesInteractor.fetchMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                        EspressoIdlingResource.decrement(); // Set app as idle.
                    }
                })
                .subscribe(this::onMovieFetchSuccess, this::onMovieFetchFailed);
    }



    @Override
    public void firstPage() {
        currentPage = 1;
        loadedMovies.clear();
        displayMovies();
    }

    @Override
    public void nextPage() {
        if(showingSearchResult)
            return;
        if (moviesInteractor.isPaginationSupported()) {
            currentPage++;
            displayMovies();
        }
    }



    @Override
    public void searchMovieBackPressed() {
        if(showingSearchResult) {
            showingSearchResult = false;
            loadedMovies.clear();
            displayMovies();
        }
    }

    private void showLoading() {
        if (isViewAttached()) {
            view.loadingStarted();
        }
    }

    private void onMovieFetchSuccess(List<Movie> movies) {
        if (moviesInteractor.isPaginationSupported()) {
            loadedMovies.addAll(movies);
        } else {
            loadedMovies = new ArrayList<>(movies);
        }
        if (isViewAttached()) {
            view.showMovies(loadedMovies);
        }
    }

    private void onMovieFetchFailed(Throwable e) {
        Log.e("SIIIIIIIIIZ",e.getMessage()+"");

        view.loadingFailed(e.getMessage());
    }

    private void onMovieSearchSuccess(List<Movie> movies) {
        Log.e("SIIIIIIIIIZ",movies.size()+"");

        loadedMovies.clear();
        loadedMovies = new ArrayList<>(movies);
        if (isViewAttached()) {
            view.showMovies(loadedMovies);
        }
    }

    private void onMovieSearchFailed(Throwable e) {
        view.loadingFailed(e.getMessage());
    }

    private boolean isViewAttached() {
        return view != null;
    }
}
