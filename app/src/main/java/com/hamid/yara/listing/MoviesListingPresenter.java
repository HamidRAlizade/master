package com.hamid.yara.listing;

/**
 * @author arun
 */
public interface MoviesListingPresenter
{
    void firstPage();

    void nextPage();

    void setView(MoviesListingView view);


    void searchMovieBackPressed();

    void destroy();
}
