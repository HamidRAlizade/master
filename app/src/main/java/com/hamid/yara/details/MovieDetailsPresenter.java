package com.hamid.yara.details;

import com.hamid.yara.Movie;

/**
 * @author arun
 */
public interface MovieDetailsPresenter
{

    void showDetails(Movie movie);

    void showTrailers(Movie movie);

    void showFavoriteButton(Movie movie);

    void onFavoriteClick(Movie movie);

    void setView(MovieDetailsView view);

    void destroy();
}
